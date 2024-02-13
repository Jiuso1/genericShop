<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/html.html to edit this template
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Log in</h1>
        <form action="login"> 
            <label for="email">email</label> 
            <input type="email" id="email" 
                   name="email" 
                   placeholder="Enter your email" required> 

            <label for="password">password</label> 
            <input type="text" id="password" 
                   name="password" 
                   placeholder="Enter your password" required> 

            <button type="submit">Submit</button>  
        </form> 
        <h1>Dont have account? Register here!</h1>
        <form action="viewRegister">
            <button type="submit">register</button>
        </form>
    </body>
</html>
