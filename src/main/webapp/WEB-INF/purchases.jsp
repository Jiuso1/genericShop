<%-- 
    Document   : purchases
    Created on : 14 feb 2024, 22:06:21
    Author     : manu_
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Your purchases</h1>
        <%
            ArrayList<Product> purchasesArray = (ArrayList<Product>) request.getAttribute("purchasesArray");

            for (int i = 0; i < purchasesArray.size(); i++) {
                Product product = purchasesArray.get(i);
                out.println("<div style=\"height: 200px; width: 150px; border:1px solid black; margin-bottom:100px\">");
                out.println("<h3>" + product.getName() + "</h3>");
                out.println("<p>" + product.getDescription() + "</p>");
                out.println("<p>" + product.getPrice() + " €</p>");
                if (product.getImagePath() == null || !product.getImagePath().contains("localhost")) {
                    out.println("<img src=\"http://localhost:8080/genericShop/images/error.jpg\">");
                } else {
                    out.println("<img src=\"" + product.getImagePath() + "\">");
                }
            }
            out.println("<form action=\"realIndex\"><button type=\"submit\">Go back to main screen</button></form>");
        %>
    </body>
</html>
