<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/15/2022
  Time: 11:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SandWich</title>
</head>
<body>
<h1>Sandwich Spices</h1>
<form action="/spices" method="post">
    <label>
        <input type="checkbox" name="spices" value="Lettuce">
        <span>Lettuce</span>
    </label>

    <label>
        <input type="checkbox" name="spices" value="Tomato">
        <span>Tomato</span>
    </label>

    <label>
        <input type="checkbox" name="spices" value="Mustard">
        <span>Mustard</span>
    </label>

    <label>
        <input type="checkbox" name="spices" value="Sprouts">
        <span>Sprouts</span>
    </label>
    <hr>
    <button type="submit">Save</button>
</form>
<h1>The number spice is : ${spices}</h1>
<br>
</body>
</html>
