<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Access Denied</title>
    <jsp:include page="bootstrapImportHeader.jsp"/>
    <link rel="stylesheet" href="../../css/foober.css" type="text/css">

</head>
<body class="outer">
<div class="container" align="center">
    <div class="col">
        <img src="../../images/FooberLogo.png" class="img-fluid" style="max-width: 6rem">
        <p>403: Permission Denied</p>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">Try a different account</a>
    </div>
</div>
<jsp:include page="bootstrapImportBody.jsp"/>
</body>
</html>