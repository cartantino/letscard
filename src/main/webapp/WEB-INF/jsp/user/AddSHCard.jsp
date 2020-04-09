<%--
  Created by IntelliJ IDEA.
  User: co
  Date: 11/01/19
  Time: 15.15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="S" uri="/struts-tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Subscribe to a sharable card</title>
    <style>
        .container {
            width: 60%;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<div class="container">
    <body>
    <h1>Select a sharable card you want to add to your wallet</h1>
    <s:if test="%{getSharableCards().isEmpty() && getUsersShare().isEmpty()}">
        <br>
        <span>There's no sharable cards present</span><br>
        <span><a href="<s:url namespace="/activity" action='showActivities'/>">Create a new card from a business activity</a></span><br>
        <br>
        <span>There's no user to share a card with</span><br>
        <span><a
                href="<s:url namespace="/user" action='createUserPage'/>">Create a new user and let's start share</a></span><br><br>
    </s:if>
    <s:elseif test="%{getSharableCards().isEmpty()}">
        <br>
        <span>There's no sharable cards present</span><br>
        <span><a href="<s:url namespace="/activity" action='showActivities'/>">Create a new card from a business activity</a></span><br><br>
    </s:elseif>
    <s:elseif test="%{getUsersShare().isEmpty()}">
        <br>
        <span>There's no user to share a card with</span><br>
        <span><a
                href="<s:url namespace="/user" action='createUserPage'/>">Create a new user and let's start share</a></span><br><br>
    </s:elseif>
    <s:else>
        <s:form action="addSHCard">
            <div class="form-group">
                <s:label for="cards" value="Card"/>
                <s:select cssClass="form-control"
                          list="sharableCards"
                          listKey="id"
                          listValue="cardNumber"
                          name="cardId"/>
            </div>
            <div class="form-group">
                <s:label for="cards" value="Share with user"/>
                <s:select cssClass="form-control"
                          list="usersShare"
                          listKey="id"
                          listValue="name + ' ' + surname + ', ' + email"
                          name="userShareId"/>
            </div>
            <s:hidden name="userId" value="%{userId}"></s:hidden>
            <s:submit cssClass="btn btn-secondary" label="Add sharable card"/>
        </s:form>
    </s:else>
    </body>
</div>
</html>
