<%--
  Created by IntelliJ IDEA.
  User: co
  Date: 09/01/19
  Time: 14.00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Create place</title>
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
    <h1>Fill form to register a new place into the webapp</h1>
    <s:form action="createPlace">
        <s:fielderror cssClass="alert alert-danger">
            <s:param>place.CAP</s:param>
            <s:param>place.city</s:param>
            <s:param>place.province</s:param>
            <s:param>place.region</s:param>
        </s:fielderror>
        <div class="form-group">
            <s:label for="CAP" value="CAP"/>
            <s:textfield cssClass="form-control" name="place.CAP" value="%{ place.CAP }" requiredLabel="true"/>
        </div>
        <div class="form-group">
            <s:label for="city" value="City"/>
            <s:textfield cssClass="form-control" name="place.city" value="%{ place.city }" requiredLabel="true"/>
        </div>
        <div class="form-group">
            <s:label for="province" value="Province"/>
            <s:textfield cssClass="form-control" name="place.province" value="%{ place.province }"
                         requiredLabel="true"/>
        </div>
        <div class="form-group">
            <s:label for="region" value="Region"/>
            <s:textfield cssClass="form-control" name="place.region" value="%{ place.region }" requiredLabel="true"/>
        </div>
        <s:submit cssClass="btn btn-secondary" label="Create place"/>
    </s:form>
    <span><a href="<s:url namespace="/place" action='showPlaces'/>">Show all places</a></span><br>
    <span><a href="<s:url namespace="/place" action='placePage'/>">Place page</a></span><br>
    <span><a href="<s:url namespace="/" action="index"/>">Go back to home</a></span>
</div>
</body>
</html>

