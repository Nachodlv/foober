<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>DG Menu</title>
</head>
<body>

<jsp:include page="_menu.jsp"></jsp:include>

<h3>Delivery Guy Menu</h3>

Waiting for orders...
Means of transport: ${loginedUser.meansOfTransport}
Name: ${loginedUser.name}
<img src="http://localhost:8080/imgDGuy">


</body>
</html>