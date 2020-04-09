<%--
  Created by IntelliJ IDEA.
  User: co
  Date: 11/01/19
  Time: 15.14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Subscribe to a standard card</title>
    <style>
        .container {
            width: 60%;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Select a standard card you want to add to your wallet</h1>
    <s:if test="%{getStandardCards().isEmpty()}">
        <br>
        <span>There's no standard cards present</span><br>
        <span><a href="<s:url namespace="/activity" action='showActivities'/>">Create a new card from a business activity</a></span><br>
    </s:if>
    <s:else>
        <s:form action="addSTCard">
            <div class="form-group">
                <s:label for="cards" value="Card"/>
                <s:select cssClass="form-control"
                          list="standardCards"
                          listKey="id"
                          listValue="cardNumber"
                          name="cardId"/>
            </div>
            <s:hidden name="userId" value="%{userId}"></s:hidden>
            <s:submit cssClass="btn btn-secondary" label="Create activity"/>
        </s:form>
    </s:else>
</div>
</body>
</html>
