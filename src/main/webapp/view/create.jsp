<%--
  Created by IntelliJ IDEA.
  User: HAI
  Date: 29/05/2023
  Time: 10:45 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1> Thêm nhân viên </h1>
<form action="home" method="post">
  <input type="hidden" name="choice" value="create">
  <input type="text" name="name" placeholder="name">
  <input type="text" name="email" placeholder="email">
  <input type="text" name="address" placeholder="address">
  <input type="text" name="phone_number" placeholder="phone_number">
  <input type="text" name="salary" placeholder="salary">
  <input type="text" name="department_id" placeholder="1 nếu là nhân viên R&D, 2 nếu là HR">
  <input type="submit" value="Thêm">
</form>
</body>
</html>
