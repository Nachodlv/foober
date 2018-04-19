<!DOCTYPE html>
<html>
<head>
    <jsp:include page="bootstrapImportHeader.jsp"></jsp:include>
    <title>DG Menu</title>
    <link rel="stylesheet" href="../../css/foober.css" type="text/css">
</head>
<body>

<div class="outer container">
    <jsp:include page="_header.jsp"></jsp:include>
<h3>Delivery Guy Menu</h3>

Waiting for orders...
Means of transport: ${loginedUser.meansOfTransport}
Name: ${loginedUser.name}

<img src="http://localhost:8080/images/${loginedUser.email}.png" width="100" height="100">
</div>

<jsp:include page="bootstrapImportBody.jsp"></jsp:include>
</body>
</html>