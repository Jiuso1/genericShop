<%-- 
    Document   : cart
    Created on : 14 feb 2024, 12:25:00
    Author     : jesus
--%>

<%@page import="Model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            
            System.out.println("BIEN");
            ArrayList<Product> productArray = (ArrayList<Product>) session.getAttribute("cartItems");
            System.out.println("MUY BIEN");
            if(productArray == null) {
                System.out.println("ES NULOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
            } else {
                for (int i = 0; i < productArray.size(); i++) {
                Product product = new Product(productArray.get(i).getId(),productArray.get(i).getName(),productArray.get(i).getDescription(),productArray.get(i).getPrice(),productArray.get(i).getImagePath());
                out.println("<div style=\"height: 200px; width: 150px; border:1px solid black; margin-bottom:100px\">");
                out.println("<h3>" + product.getName() + "</h3>");
                out.println("<p>" + product.getDescription() + "</p>");
                out.println("<p>" + product.getPrice() + " €</p>");
                if (product.getImagePath() == null || !product.getImagePath().contains("localhost")) {
                    out.println("<img src=\"http://localhost:8080/genericShop/images/error.jpg\">");
                } else {
                    out.println("<img src=\"" + product.getImagePath() + "\">");
                }
                out.println("</div>");
            }
            }
            
        %>
    </body>
</html>
