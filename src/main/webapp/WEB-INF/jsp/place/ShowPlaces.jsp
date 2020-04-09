<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: belerico
  Date: 1/8/19
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--<link rel="stylesheet" href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css"/>--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Places</title>
</head>
<body>
<h2>Places</h2>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">CAP</th>
        <th scope="col">City</th>
        <th scope="col">Province</th>
        <th scope="col">Region</th>
        <th scope="col">Remove</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="places" status="placesStatus">
        <tr>
            <th scope="row">
                <s:property value="#placesStatus.index"/>
                    <%--<s:property value="id"/>--%>
            </th>
            <td>
                <s:property value="CAP"/>
            </td>
            <td>
                <s:property value="city"/>
            </td>
            <td>
                <s:property value="province"/>
            </td>
            <td>
                <s:property value="region"/>
            </td>
            <td>
                <a href="<s:url action='removePlace'><s:param name="place.id" value="%{id}"/></s:url>">Remove</a>
            </td>
        </tr>
    </s:iterator>

    </tbody>
</table>
<span><a href="<s:url namespace="/place" action='removeAllPlaces'/>">Remove all places</a></span><br>
<span><a href="<s:url namespace="/place" action='createPlacePage'/>">Create a new place</a></span><br>
<span><a href="<s:url namespace="/place" action='placePage'/>">Place page</a></span><br>
<span><a href="<s:url namespace="/" action="index"/>">Go back to home</a></span>
</body>
</html>
