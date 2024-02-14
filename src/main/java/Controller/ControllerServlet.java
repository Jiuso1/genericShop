/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jesus
 */
@WebServlet(name = "ControllerServlet", urlPatterns = {"/do/*"})
public class ControllerServlet extends HttpServlet {

    private Connection conn;
    private PreparedStatement ps;

    public void init() {
        String jesusPath = "jdbc:sqlite:C:/Users/jesus/documents/desarrolloWeb/daw/genericShop/database/database.db";
        String manuPath = "jdbc:sqlite:C:/Users/manu_/Desktop/Clase/4-Cuarto/DAW/genericShop/database/database.db";
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(manuPath);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void destroy() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        final String action = request.getPathInfo();
        String view = null;

        switch (action) {
            case "/register": {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                System.out.println("email is " + email + " and password is " + password);

                try {
                    ps = conn.prepareStatement("SELECT * FROM USER WHERE EMAIL=?");
                    ps.setString(1, email);
                    ResultSet consulta = ps.executeQuery();
                    if (consulta.next()) {
                        view = "../WEB-INF/register.jsp";
                    } else {
                        ps = conn.prepareStatement("INSERT INTO USER VALUES(?,?,NULL)");
                        ps.setString(1, email);
                        ps.setString(2, password);
                        ps.executeUpdate();
                        ps.close();
                        view = "../WEB-INF/registered.jsp";//view = "../WEB-INF/jsp/signIn.jsp";
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            }
            case "/login": {
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                try {
                    //We insert in USER table:
                    ps = conn.prepareStatement("SELECT password, id FROM USER WHERE EMAIL=?");
                    ps.setString(1, email);
                    ResultSet rs = ps.executeQuery();
                    String psw = null;
                    String id = null;
                    while (rs.next()) {
                        psw = rs.getString("password");
                        id = rs.getString("id");
                    }
                    ps.close();

                    if (password.equals(psw)) {
                        request.getSession().setAttribute("sessionId", id);
                        view = "../WEB-INF/loged.jsp";
                    } else {
                        view = "../WEB-INF/signIn.jsp";
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

            case "/realIndex": {
                try {
                    ArrayList<Product> productArray = new ArrayList<>();
                    ps = conn.prepareStatement("SELECT * FROM PRODUCT");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String description = rs.getString("description");
                        float price = rs.getFloat("price");
                        String imagePath = rs.getString("imagePath");
                        Product product = new Product(id, name, description, price, imagePath);
                        productArray.add(product);
                    }
                    ps.close();
                    request.setAttribute("productArray", productArray);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                view = "../WEB-INF/index.jsp";
                break;
            }

            case "/logout": {
                request.getSession().setAttribute("sessionId", null);
                try {
                    ArrayList<Product> productArray = new ArrayList<>();
                    ps = conn.prepareStatement("SELECT * FROM PRODUCT");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String description = rs.getString("description");
                        float price = rs.getFloat("price");
                        String imagePath = rs.getString("imagePath");
                        Product product = new Product(id, name, description, price, imagePath);
                        productArray.add(product);
                    }
                    ps.close();
                    request.setAttribute("productArray", productArray);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                view = "../WEB-INF/index.jsp";
                break;
            }

            case "/signIn": {
                view = "../WEB-INF/signIn.jsp";
                break;
            }

            case "/viewRegister": {
                view = "../WEB-INF/register.jsp";
                break;
            }

            case "/removeAccount": {
                if (request.getSession().getAttribute("sessionId") == null) {
                    view = "../WEB-INF/error.jsp";
                } else {
                    try {
                        PreparedStatement ps;
                        ps = conn.prepareStatement("DELETE FROM USER WHERE ID=?");
                        ps.setString(1, (String) request.getSession().getAttribute("sessionId"));
                        ps.executeUpdate();
                        ps.close();

                        request.getSession().setAttribute("sessionId", null);
                        view = "../WEB-INF/accountRemoved.jsp";

                        break;
                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            case "/sendCart": {
                ArrayList<Integer> idsList = new ArrayList<>();
                // Get JS Data:   
                String jsonData = request.getReader().lines().collect(java.util.stream.Collectors.joining());
                System.out.println("JS received: " + jsonData);

                try (JsonReader jsonReader = Json.createReader(new StringReader(jsonData))) {
                    // Leer el JSON como objeto
                    JsonObject jsonObject = jsonReader.readObject();

                    // Obtener el array "cart" del objeto
                    JsonArray cartArray = jsonObject.getJsonArray("cart");

                    // Crear un ArrayList para almacenar los IDs
                    // Iterar sobre los elementos del array y agregarlos al ArrayList
                    for (int i = 0; i < cartArray.size(); i++) {
                        // Convertir el valor del array a entero y agregarlo al ArrayList
                        idsList.add(Integer.parseInt(cartArray.getString(i)));
                    }
                }

                ArrayList<Product> productArray = new ArrayList<>();
                try {
                    for (int i = 0; i < idsList.size(); i++) {
                        ps = conn.prepareStatement("SELECT * FROM PRODUCT WHERE ID=?");
                        ps.setString(1, Integer.toString(idsList.get(i)));
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String name = rs.getString("name");
                            String description = rs.getString("description");
                            float price = rs.getFloat("price");
                            String imagePath = rs.getString("imagePath");
                            Product product = new Product(id, name, description, price, imagePath);
                            productArray.add(product);
                        }
                        ps.close();
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.setAttribute("cartItems", productArray);
                view = "../WEB-INF/cart.jsp";
                break;
            }
            
            case "/viewCart": {
                view = "../WEB-INF/cart.jsp";
                break;
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher(view);
        if (rd == null) {
            System.out.println("Error: rd is null and action values " + action);
        }
        rd.forward(request, response);
    }

    @Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

}
