<%--
  Created by IntelliJ IDEA.
  User: co
  Date: 13/01/19
  Time: 2.42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <%--<link rel="stylesheet" href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css"/>--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Cards</title>
</head>
<body>
<div>
    <h3>Standard and sharable cards of <s:property value="user.name + ' ' + user.surname"/> <a
            href="<s:url namespace="/user" action="createUpdateUserPage"><s:param name="userId" value="%{ user.id }"/></s:url>"><s:property
            value="user.email"/></a></h3>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">CardNumber</th>
            <th scope="col">Type</th>
            <th scope="col">Quantity</th>
            <th scope="col">Activity</th>
            <th scope="col">Remove</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="userStandardCards" status="userStandardCardsStatus" var="c">
            <tr>
                <td scope="row">
                    <s:property value="#userStandardCardsStatus.index"/>
                </td>
                <td>
                    <s:property value="%{ #c.getStandardCard().getCardNumber() }"/>
                </td>
                <td>
                    Standard card
                </td>
                <td>
                    <s:property value="%{ #c.getStandardCard().getQuantity() }"/>
                </td>
                <td>
                    <a href="<s:url namespace="/activity" action="createActivityPage"><s:param name="activityId" value="%{ #c.getStandardCard().getBusinessActivity().getId() }"/></s:url>"><s:property
                            value="%{ #c.getStandardCard().getBusinessActivity().getName() }"/></a>
                </td>
                <td>
                    <a href="<s:url namespace="/user" action="removeStandardCard"><s:param name="userId" value="%{ user.id }"/><s:param name="cardId" value="%{ #c.getStandardCard().getId() }"/></s:url>">Remove</a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    <span><a
            href="<s:url namespace="/user" action="removeAllUserStandardCards"><s:param name="userId" value="%{ user.id }"/></s:url>">Remove all <s:property
            value="user.name + ' ' + user.surname"/> standard cards</a></span><br>
    <br>
    <br>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">CardNumber</th>
            <th scope="col">Type</th>
            <th scope="col">Quantity</th>
            <th scope="col">Shared with</th>
            <th scope="col">Activity</th>
            <th scope="col">Remove</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="userSharableCards" status="userSharableCardsStatus" var="c">
            <tr>
                <td scope="row">
                    <s:property value="#userSharableCardsStatus.index"/>
                </td>
                <td>
                    <s:property value="%{ #c.getSharableCard().getCardNumber() }"/>
                </td>
                <td>
                    Sharable card
                </td>
                <td>
                    <s:property value="%{ #c.getSharableCard().getQuantity() }"/>
                </td>
                <td>
                    <s:if test="%{ #c.getUser1().getEmail().equals(user.email) }">
                        <a href="<s:url namespace="/user" action="createUpdateUserPage"><s:param name="userId" value="%{ #c.getUser2().getId() }"/></s:url>"><s:property
                                value="%{ #c.getUser2().getEmail() }"/></a>
                    </s:if>
                    <s:else>
                        <a href="<s:url namespace="/user" action="createUpdateUserPage"><s:param name="userId" value="%{ #c.getUser1().getId() }"/></s:url>"><s:property
                                value="%{ #c.getUser1().getEmail() }"/></a>
                    </s:else>
                </td>
                <td>
                    <a href="<s:url namespace="/activity" action="createActivityPage"><s:param name="activityId" value="%{ #c.getSharableCard().getBusinessActivity().getId() }"/></s:url>"><s:property
                            value="%{ #c.getSharableCard().getBusinessActivity().getName() }"/></a>
                </td>
                <td>
                    <a href="<s:url namespace="/user" action="removeSharableCard">
                                <s:param name="userId" value="%{ #c.getUser1().getId() }"/>
                                <s:param name="otherUserId" value="%{ #c.getUser2().getId() }"/>
                                <s:param name="cardId" value="%{ #c.getSharableCard().getId() }"/>
                            </s:url>">Remove</a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    <span><a
            href="<s:url namespace="/user" action="removeAllUserSharableCards"><s:param name="userId" value="%{ user.id }"/></s:url>">Remove all <s:property
            value="user.name + ' ' + user.surname"/> sharable cards</a></span><br><br><br>
    <span><a
            href="<s:url namespace="/user" action='createAddStandardCardPage'><s:param name="userId" value="%{ user.id }"/></s:url>">Add a standard card</a></span>
    <br>
    <span><a
            href="<s:url namespace="/user" action='createAddSharableCardPage'><s:param name="userId" value="%{ user.id }"/></s:url>">Add a sharable card</a></span><br>
    <span><a
            href="<s:url namespace="/user" action="removeAllUserCards"><s:param name="userId" value="%{ user.id }"/></s:url>">Remove all <s:property
            value="user.name + ' ' + user.surname"/> cards</a></span><br>
    <span><a href="<s:url namespace="/user" action='showUsers'/>">Show users</a></span><br>
    <span><a href="<s:url namespace="/user" action='createUserPage'/>">Create a new user</a></span><br>
    <span><a href="<s:url namespace="/" action="index"/>">Go back to home</a></span>
</div>
</body>
</html>