<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>

<jsp:include page="_menu.jsp"></jsp:include>

<h3>Hello: ${loginedUser.email}</h3>

User Name: <b>${loginedUser.email}</b>
<br />
Role: ${loginedUser.role } <br />
<br/>
Password: ${loginedUser.password}


</body>
</html>