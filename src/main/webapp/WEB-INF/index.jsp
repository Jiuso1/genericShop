<%-- 
    Document   : index
    Created on : 13 feb 2024, 0:20:09
    Author     : manu_
--%>

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
            if (session.getAttribute("id") == null) {
                out.println("<p><a href=\"./signIn.html\">sign in</a></p>");
            } else {
                out.println("<p><a href=\"./signIn.html\">log out</a></p>");
            }
        %>
    </body>
</html>
