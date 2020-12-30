<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020/12/28
  Time: 2:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    \${userObject.name} : ${userObject.name}
<%--    \${user.name} : ${user.name}--%>
    \${applicationScope['scopedTarget.user'].name} : ${applicationScope['scopedTarget.user'].name}
</body>
</html>
