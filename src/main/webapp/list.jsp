<%--
  ~ Copyright (c) 2016. 上海趣医网络技术服务有限公司 版权所有
  ~ Shanghai QuYi Network Technology Co., Ltd. All Rights Reserved.
  ~
  ~ This is NOT a freeware,use is subject to license terms.
  --%>

<%--
  Created by IntelliJ IDEA.
  User: Fsx
  Date: 2016/12/5
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>List</title>
</head>
<body>
    <h2>List Page</h2>

    Welcome: <shiro:principal></shiro:principal>

    <shiro:hasRole name="admin">
        <hr/>
        <a href="admin.jsp">Admin Page</a>
    </shiro:hasRole>

    <shiro:hasRole name="user">
        <hr/>
        <a href="user.jsp">User Page</a>
    </shiro:hasRole>

    <hr/>
    <a href="/shiro/testAnnotation">Test Annotation</a>

    <a href="/shiro/logout">Logout</a>
</body>
</html>
