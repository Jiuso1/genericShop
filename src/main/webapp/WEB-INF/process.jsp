<%-- 
    Document   : process
    Created on : 14 feb 2024, 17:47:01
    Author     : jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Your purchase has been processed! Use this id for tracking your shipping: <%out.print(request.getAttribute("orderId"));%></h1>
        <form action="realIndex">
            <button type="submit">Go to main menu</button>
        </form>
    </body>
</html>
