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
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login Page</h2>
    <form action="/shiro/login" method="post">
        username: <input type="text" name="username" />
        <br/>
        password: <input type="password" name="password"/>
        <br/>
        <input type="submit" value="Submit"/>

    </form>
</body>
</html>
