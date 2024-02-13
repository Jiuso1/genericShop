/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public void init() {
        String jesusPath = "jdbc:sqlite:C:/Users/jesus/documents/desarrolloWeb/daw/genericShop/database/database.db";
        String manuPath = "jdbc:sqlite:C:/Users/manu_/Desktop/Clase/4-Cuarto/DAW/genericShop/database/database.db";
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(jesusPath);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void destroy() {

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

                PreparedStatement ps;

                try {
                    //We insert in USER table:
                    ps = conn.prepareStatement("INSERT INTO USER VALUES(?,?,NULL)");
                    ps.setString(1, email);
                    ps.setString(2, password);
                    ps.executeUpdate();
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                view = "../WEB-INF/registered.jsp";//view = "../WEB-INF/jsp/signIn.jsp";
                break;
            }
            case "/login": {
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                PreparedStatement ps;

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
                view = "../WEB-INF/index.jsp";
                break;
            }

            case "/logout": {
                request.getSession().setAttribute("sessionId", null);
                view = "../WEB-INF/index.jsp";
                break;
            }

            case "/signIn": {
                view = "../WEB-INF/signIn.jsp";
                break;
            }
            
            case "/viewRegister":{
                view = "../WEB-INF/register.jsp";
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
