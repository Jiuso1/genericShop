/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Controller", urlPatterns = {"/do/*"})
public class ControllerServlet extends HttpServlet {

    public void init() {
        
    }
    
    public void destroy(){
        
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        final String action = request.getPathInfo();
        String view = null;

        switch (action) {
            case "/register": {
                view = "../WEB-INF/registered.jsp";//view = "../WEB-INF/jsp/signIn.jsp";
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
