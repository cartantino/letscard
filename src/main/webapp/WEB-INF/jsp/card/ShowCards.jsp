<%--
  Created by IntelliJ IDEA.
  User: avogadro
  Date: 09/01/19
  Time: 21.50
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
    <h2>Cards</h2>
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
        <s:iterator value="cards" status="cardsStatus" var="c">
            <tr>
                <th scope="row">
                    <s:property value="#cardsStatus.index"/>
                </th>
                <td>
                    <s:property value="cardNumber"/>
                </td>
                <td>
                    <s:if test="%{#c instanceof com.assignment3.jpa.model.SharableCard}">
                        Sharable card
                    </s:if>
                    <s:else>
                        Standard card
                    </s:else>
                </td>
                <td>
                    <s:property value="quantity"/>
                </td>
                <td>
                    <a href="<s:url namespace="/activity" action="createUpdateActivityPage"><s:param name="activityId" value="%{ #c.getBusinessActivity().getId() }"/></s:url>"><s:property
                            value="%{ #c.getBusinessActivity().getName() }"/></a>
                </td>
                <td>
                    <a href="<s:url action="removeCard"><s:param name="card.id" value="%{id}"/></s:url>">Remove</a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    <span><a href="<s:url namespace="/card" action='removeAllCards'/>">Remove all cards</a></span><br>
    <span><a href="<s:url namespace="/card" action='cardPage'/>">Cards</a></span><br>
    <span><a href="<s:url namespace="/activity" action='showActivities'/>">Activity page</a></span><br>
    <span><a href="<s:url namespace="/" action="index"/>">Go back to home</a></span>
</div>
</body>
</html>