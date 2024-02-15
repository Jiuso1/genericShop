<%-- 
    Document   : index
    Created on : 13 feb 2024, 0:20:09
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
        <link href="../style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Index</h1>
        <button id="cartCounter" type="submit">0</button>
        <%
            String admin = (String) request.getSession().getAttribute("admin");
            
            if (session.getAttribute("sessionId") == null) {
                out.println("<form action=\"signIn\"><button type=\"submit\">login</button></form>");
            } else {
                out.println("<form action=\"logout\"><button type=\"submit\">log out</button></form>");
                out.println("<form action=\"purchases\"><button type=\"submit\">View made purchases</button></form>");
                if (admin != null && admin.equals("1")) {
                    out.println("<form action=\"addProduct\"><button style=\"background-color: DarkCyan;\" type=\"submit\">Upload a product</button></form>");
                }
            }

            ArrayList<Product> productArray = (ArrayList<Product>) request.getAttribute("productArray");
            if (productArray != null) {
                for (int i = 0; i < productArray.size(); i++) {
                    Product product = productArray.get(i);
                    if (product.getSold() == 0) {
                        out.println("<div id=\"productCardContainer\">");
                        out.println("<div class=\"productCard\">");
                        out.println("<h3>" + product.getName() + "</h3>");
                        out.println("<p>" + product.getDescription() + "</p>");
                        out.println("<p>" + product.getPrice() + " €</p>");
                        if (product.getImagePath() == null) {
                            out.println("<img src=\"http://localhost:8080/genericShop/images/error.jpg\">");
                        } else {
                            out.println("<img src=\"" + product.getImagePath() + "\">");
                        }
                        out.println("<br>");
                        out.println("<button class=\"addCart\" value=\"" + product.getId() + "\">Add to cart</button>");
                        if (admin != null && admin.equals("1")) {
                            out.println("<button class=\"removeButton\" style=\"background-color: red;\" type=\"submit\" value=\"" + product.getId() + "\">Remove product</button>");
                        }
                        out.println("</div>");
                        out.println("</div>");
                    }
                }
            }
            out.println("<script src=\"../cart.js\" type=\"text/javascript\"></script>");
        %>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </body>
</html>
