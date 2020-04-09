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
    <h1>Fill form to register a new business activity into the webapp</h1>
    <s:if test="%{getPlaces().isEmpty()}">
        <br>
        <span>There's no places present, so you can't create or update a new business activity until there's at least one you can choose from</span><br>
        <span><a href="<s:url namespace="/place" action='createPlacePage'/>">Create a new place</a></span><br><br>
    </s:if>
    <s:else>
        <s:form namespace="/activity" action="%{ activityId == null ? 'createActivity' : 'updateActivity' }">
            <s:fielderror cssClass="alert alert-danger">
                <s:param>activity.name</s:param>
                <s:param>activity.type</s:param>
            </s:fielderror>
            <div class="form-group">
                <s:label for="name" value="Name"/>
                <s:textfield cssClass="form-control" name="activity.name" value="%{ activity.name }"
                             requiredLabel="true"/>
            </div>
            <div class="form-group">
                <s:label for="type" value="Type"/>
                <s:textfield cssClass="form-control" name="activity.type" value="%{ activity.type }"
                             requiredLabel="true"/>
            </div>
            <div class="form-group">
                <s:label for="places" value="Place"/>
                <s:select cssClass="form-control"
                          list="places"
                          listKey="id"
                          listValue="CAP + ', ' + city"
                          name="placeId"/>
            </div>
            <%--<s:if test="%{ activityId != null }">--%>
                <s:hidden name="activityId" value="%{ activityId }"></s:hidden>
            <%-- </s:if>--%>
            <s:submit cssClass="btn btn-secondary" label="Create activity"/>
        </s:form>
    </s:else>
    <span><a href="<s:url namespace="/activity" action='showActivities'/>">Show all business activities</a></span><br>
    <span><a href="<s:url namespace="/activity" action='activityPage'/>">Activity page</a></span><br>
    <span><a href="<s:url namespace="/" action="index"/>">Go back to home</a></span>
</div>
</body>
</html>

