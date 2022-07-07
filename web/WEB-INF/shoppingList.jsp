<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Shopping List </title>
    </head>
    <body>
        <h1> Shopping List </h1>
        <h3> Hello ${username}</h3>
        <a href=shoppingList?action=logout> Logout </a>
        
        <form method="post" action="shoppingList">
            <h3> List </h3>            
            <label> Add item</label>
            <input type="text" name="itemToAdd" value="">
            <input type="submit" name="action" value="add">
        </form>
        
        <c:if test="${itemList !=null}">
            
            <form method="post" action="shoppingList">
                <c:forEach var="item" items="${itemList}">
<!--                use the var name as the value and the description-->
                    <input type="radio" name="each_item" value="${item}"> ${item} <br>  
                </c:forEach>
                   <br>
                   <input type="hidden" name="action" value="delete">
                   <input type="submit" value="delete">                
            </form>
           
        </c:if>
    </body>
</html>