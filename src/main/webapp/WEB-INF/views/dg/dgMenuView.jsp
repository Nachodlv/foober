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

    <%--modal for confirming--%>
    <div class="modal fade bd-example-modal-sm" id="options" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content" style="width: 120%">
                <form method="post">
                    <div class="modal-header">
                        <h5 class="modal-title">Order recieved!</h5>
                        <button type="button" onclick="refuseOrder()" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p class="rem1">Do you want to take this order?</p>
                        <table class="table table-striped">
                            <tr>
                                <td><p class="rem1"><b>Elaboration Time</b></p></td>
                                <td id="elaborationTime"></td>
                            </tr>
                            <tr>
                                <td><p class="rem1"><b>Client email</b></p></td>
                                <td id="clientEmail"></td>
                            </tr>
                            <tr>
                                <td><p class="rem1"><b>Total Price</b></p></td>
                                <td id="totalPrice"></td>
                            </tr>
                            <tr>
                                <td><p class="rem1"><b>Tip</b></p></td>
                                <td id="tip"></td>
                            </tr>
                        </table>

                    </div>
                    <div class="modal-footer">
                        <button type="button" onclick="refuseOrder()" class="btn btn btn-danger" data-dismiss="modal">Refuse</button>
                        <button type="button" onclick="acceptOrder()" class="btn btn-success">Confirm</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <%--<div class="row" id="options" hidden>--%>
        <%--<button type="button" value="accept" name="accept" class="btn btn-outline-dark spaced-top" onclick="acceptOrder()"><i class="fas fa-power-off"></i> Accept</button>--%>
        <%--<button type="button" value="accept" name="accept" class="btn btn-outline-dark spaced-top" onclick="refuseOrder()"><i class="fas fa-power-off"></i> Refuse</button>--%>
    <%--</div>--%>

</div>

<script src="../../../js/dg/sw.js"></script>
<script src="../../../js/dg/notificationDG.js"></script>
<script src="../../../js/dg/dgMenu.js"></script>
<jsp:include page="../bootstrapImportBody.jsp"/>
</body>
</html>