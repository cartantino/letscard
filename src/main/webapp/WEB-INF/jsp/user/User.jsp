<%--
  Created by IntelliJ IDEA.
  User: co
  Date: 07/01/19
  Time: 14.56
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User home page</title>
    <style>
        .list {
            margin: 0 auto;
            display: table;
        }
    </style>
</head>
<body>
<div class="list">
    <h1>User page</h1>
    <p><a href="<s:url namespace="/user" action='showUsers'/>">Show all users</a></p>
    <p><a href="<s:url namespace="/user" action='createUserPage'/>">Create a new user</a></p>
    <p><a href="<s:url namespace="/" action="index"/>">Go back to home</a></p>
</div>
</body>
</html>







