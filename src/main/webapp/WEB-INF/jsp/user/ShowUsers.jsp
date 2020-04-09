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
    <title>Users</title>
    <%--    <style>
            .container {
                width: 70%;
                margin-left: auto;
                margin-right: auto;
            }
        </style>--%>
</head>
<body>
<div>
    <h2>Users</h2>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
            <th scope="col">Date of birth</th>
            <th scope="col">Email</th>
            <th scope="col">Place</th>
            <th scope="col">Gender</th>
            <th scope="col">Add card</th>
            <th scope="col">Remove</th>
            <th scope="col">Show user cards</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="users" status="usersStatus">
            <tr>
                <td>
                    <s:property value="#usersStatus.index"/>
                </td>
                <td>
                    <s:property value="name"/>
                </td>
                <td>
                    <s:property value="surname"/>
                </td>
                <td>
                    <s:property value="dateOfBirth"/>
                        <%--<s:property value="dateOfBirth"/>--%>
                </td>
                <td>
                    <a href="<s:url namespace="/user" action="createUpdateUserPage"><s:param name="userId" value="%{ id }"/></s:url>"><s:property
                            value="email"/></a>
                </td>
                <td>
                    <s:if test="place == null">
                        N/A
                    </s:if>
                    <s:else>
                        <s:property value="place.CAP + ', ' + place.city"/>
                    </s:else>
                </td>
                <td>
                    <s:if test="sex">
                        Male
                    </s:if>
                    <s:else>
                        Female
                    </s:else>
                </td>
                <td>
                    <a href="<s:url namespace="/user" action='createAddStandardCardPage'><s:param name="userId" value="%{id}"></s:param></s:url>">STANDARD</a>
                    <br>
                    <a href="<s:url namespace="/user" action='createAddSharableCardPage'><s:param name="userId" value="%{id}"></s:param></s:url>">SHARABLE</a>
                </td>
                <td>
                    <a href="<s:url namespace="/user" action='showUserCards'><s:param name="userId" value="%{id}"></s:param></s:url>">VIEW
                        CARDS</a>

                </td>
                <td>
                    <a href="<s:url namespace="/user" action='removeUser'><s:param name="user.id" value="%{id}"/></s:url>">Remove</a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    <span><a href="<s:url namespace="/user" action='removeAllUsers'/>">Remove all users</a></span><br>
    <span><a href="<s:url namespace="/user" action='createUserPage'/>">Create a new user</a></span><br>
    <span><a href="<s:url namespace="/user" action='userPage'/>">User page</a></span><br>
    <span><a href="<s:url namespace="/" action="index"/>">Go back to home</a></span>
</div>
</body>
</html>
