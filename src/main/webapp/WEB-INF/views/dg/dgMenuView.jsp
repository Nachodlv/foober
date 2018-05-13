<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../bootstrapImportHeader.jsp"/>
    <title>DG Menu</title>
    <link rel="stylesheet" href="../../../css/foober.css" type="text/css">
</head>
<body>

<div class="outer container-fluid">
    <jsp:include page="../_header.jsp"/>
<h3>Welcome, ${loginedUser.name}!</h3><br>
<div class="container-fluid bg-light rounded">
    <form method="post" action="${pageContext.request.contextPath}/dgMenu">
    <div class="row">
        <div class="col-3">
            <img style="margin: 5%;" width="120" height="120" class="rounded-circle" src="http://localhost:8080/images/${loginedUser.email}.png">
        </div>
        <div class="col-md-auto offset-md-7">
            <div id="deliveryGuy">
                <input type="hidden" value="${loginedUser.name}">
                <input type="hidden" value="${loginedUser.phone}">
                <input type="hidden" value="${loginedUser.meansOfTransport}">
                <input type="hidden" value="${loginedUser.email}">
                <input type="hidden" id="dgStatus" value="${loginedUser.state}">
            </div>
            <div class="row">
                <button type="button" id="offline" value="offline" name="state" class="btn btn-outline-dark spaced-top" onclick="login_logout('OFFLINE')" disabled><i class="fas fa-power-off"></i> Go offline</button>
            </div>
            <div class="row">
                <button type="button" id="online" value="online" name="state" class="btn btn-outline-dark spaced" onclick="login_logout('ONLINE_WAITING')"><i class="fas fa-power-off"></i> Go online </button>
            </div>
        </div>
    </div>
    </form>
</div>
    <%--Spinner--%>
    <div class="spinner" id="spinner">
        <h3 align="center">Waiting for orders...</h3>
        <div class="bounce1"></div>
        <div class="bounce2"></div>
        <div class="bounce3"></div>
    </div>

    <div class="row" id="options" hidden>
        <button type="button" value="accept" name="accept" class="btn btn-outline-dark spaced-top" onclick="acceptOrder()"><i class="fas fa-power-off"></i> Accept</button>
        <button type="button" value="accept" name="accept" class="btn btn-outline-dark spaced-top" onclick="refuseOrder()"><i class="fas fa-power-off"></i> Refuse</button>
    </div>

</div>

<script src="../../../js/dg/sw.js"></script>
<script src="../../../js/dg/notificationDG.js"></script>
<script src="../../../js/dg/dgMenu.js"></script>
<jsp:include page="../bootstrapImportBody.jsp"/>
</body>
</html>