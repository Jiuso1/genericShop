<%-- 
    Document   : addProduct
    Created on : 14 feb 2024, 23:27:59
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
        <h1>Upload a product</h1>
        <form action="uploadProduct"> 
            <label for="name">name</label> 
            <input type="text" id="name" 
                   name="name" 
                   placeholder="Product name" required> 

            <label for="description">description</label> 
            <input type="text" id="description" 
                   name="description" 
                   placeholder="Product description" required> 

            <label for="price">price</label> 
            <input type="text" id="price" 
                   name="price" 
                   placeholder="Product price" required>
            
            <label for="link">price</label> 
            <input type="text" id="link" 
                   name="link" 
                   placeholder="Upload wherever you want :D (imgur, flicker, instagram...)" required> 

            <button type="submit">Upload</button>  
            
        </form>
    </body>
</html>
