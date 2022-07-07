
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Shopping list </title>
    </head>
    <body>
        <h1> Shopping list </h1>
        <form method="post" action="shoppingList">
            <label> Username: </label>
            <input type="text" name="user" value="">
            <input type="submit" value="Register User">            
            <input type="hidden" name="action" value="Register">  
        </form>
        <p>${message}</p>
    </body>
</html>
