<%--
  Created by IntelliJ IDEA.
  User: co
  Date: 09/01/19
  Time: 14.27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <%--<link rel="stylesheet" href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css"/>--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Business Activities</title>
</head>
<body>
<div>
    <h2>Business Activities</h2>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Type</th>
            <th scope="col">Place</th>
            <th scope="col">Remove</th>
            <th scope="col">Add card</th>
            <th scope="col">Show activity cards</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="activities" status="activitiesStatus">
            <tr>
                <th scope="row">
                    <s:property value="#activitiesStatus.index"/>
                </th>
                <td>
                    <a href="<s:url namespace="/activity" action="createUpdateActivityPage"><s:param name="activityId" value="%{ id }"/></s:url>"><s:property
                            value="name"/></a>
                </td>
                <td>
                    <s:property value="type"/>
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
                    <a href="<s:url namespace="/activity" action='removeActivity'><s:param name="activity.id" value="%{ id }"/></s:url>">Remove</a>
                </td>
                <td>
                    <a href="<s:url namespace="/card" action='createCardPage'><s:param name="activityId" value="%{ id }"/></s:url>">Create
                        card</a>
                </td>
                <td>
                    <a href="<s:url namespace="/activity" action='showActivityCards'><s:param name="activityId" value="%{ id }"/></s:url>">Show
                        Cards</a>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
    <span><a
            href="<s:url namespace="/activity"  action='removeAllActivities'/>">Remove all business activities</a></span><br>
    <span><a href="<s:url namespace="/activity" action='createActivityPage'/>">Create a new business activity</a></span><br>
    <span><a href="<s:url namespace="/activity" action='activityPage'/>">Activity page</a></span><br>
    <span><a href="<s:url namespace="/" action="index"/>">Go back to home</a></span>
</div>
</body>
</html>
