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
    </head>
    <body>
        <h1>Index</h1>
        <%
            if (session.getAttribute("sessionId") == null) {
                out.println("<form action=\"signIn\"><button type=\"submit\">login</button></form>");
            } else {
                out.println("<form action=\"logout\"><button type=\"submit\">log out</button></form>");
            }
            ArrayList<Product> productArray = (ArrayList<Product>)request.getAttribute("productArray");
            for(int i = 0;i < productArray.size();i++){
                Product product = productArray.get(i);
                out.println("<h3>"+product.getName()+"</h3>");
                out.println("<p>"+product.getDescription()+"</p>");
                out.println("<p>"+product.getPrice()+"</p>");
                out.println("<img src=\""+product.getImagePath()+"\">");
            }
        %>
    </body>
</html>
