<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 12/14/2022
  Time: 2:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dictionary</title>
</head>
<body>
<h1>Dictionary</h1>
<form action="/lookUp" method="post">
    <p>Input word here: <input type="text" name="word"></p>
    <button type="submit">Look up</button>
</form>

<p>${result}</p>

</body>
</html>
