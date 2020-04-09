<%--
  Created by IntelliJ IDEA.
  User: avogadro
  Date: 09/01/19
  Time: 18.11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Create business activity</title>
    <style>
        .container {
            width: 60%;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
    <s:head/>
</head>
<body>
<div class="container">
    <h1>Fill form to register a new card into the webapp</h1>
    <s:form action="createCard">
        <s:fielderror cssClass="alert alert-danger">
            <s:param>card.cardNumber</s:param>
            <s:param>card.quantity</s:param>
        </s:fielderror>
        <div class="form-group">
            <s:label for="cardNumber" value="Card number"/>
            <s:textfield cssClass="form-control" name="card.cardNumber" value="%{ card.cardNumber }"
                         requiredLabel="true"/>
        </div>
        <div class="form-group">
            <s:label for="cardType" value="Type"/>
            <s:select cssClass="form-control" name="cardType" list="#{'ST':'Standard card','SH':'Sharable card'}"/>
        </div>
        <div class="form-group">
            <s:label for="quantity" value="Quantity"/>
            <s:textfield cssClass="form-control" name="card.quantity" value="%{ card.quantity }" requiredLabel="true"/>
        </div>
        <s:hidden name="activityId" value="%{ activityId }"></s:hidden>
        <s:submit cssClass="btn btn-secondary" label="Add Card"/>
    </s:form>
</div>
</body>
</html>

