<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../bootstrapImportHeader.jsp"/>
    <title>DG Menu</title>
    <link rel="stylesheet" href="../../../css/foober.css" type="text/css">
</head>
<body class="outer">
<jsp:include page="../_header.jsp"/>
<h3>Welcome, ${loginedUser.name}!</h3><br>
<div class="container-fluid bg-light rounded">
    <form method="post" action="${pageContext.request.contextPath}/dgMenu">
        <div id="deliveryGuy">
            <input hidden value="${loginedUser.name}">
            <input hidden value="${loginedUser.phone}">
            <input hidden value="${loginedUser.meansOfTransport}">
            <input hidden value="${loginedUser.email}">
            <input hidden id="dgState" value="${loginedUser.state}">
        </div>
        <div class="d-flex">
            <div>
                <img width="120" height="120" class="rounded-circle"
                     src="${pageContext.request.contextPath}/images/${loginedUser.email}.png">
            </div>
            <div class="ml-auto mr-3 mb-1">
                <div class="row">
                    <button type="button" id="offline" value="offline" name="state"
                            class="btn btn-outline-dark spaced-top" onclick="login_logout('OFFLINE')" disabled><i
                            class="fas fa-power-off"></i> Go offline
                    </button>
                </div>
                <div class="row">
                    <button type="button" id="online" value="online" name="state" class="btn btn-outline-dark spaced"
                            onclick="login_logout('ONLINE_WAITING')"><i class="fas fa-power-off"></i> Go online
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>

<div class="d-flex justify-content-center">
    <%--Spinner--%>
    <div class="spinner" id="spinner">
        <h3 align="center">Waiting for orders...</h3>
        <div class="bounce1"></div>
        <div class="bounce2"></div>
        <div class="bounce3"></div>
    </div>

    <%--Order when delivering--%>
    <div id="order" class="form-row" hidden>
        <table id="tableOrder" class="table-bordered table text-center" style="width: 70%;margin-top: 3rem;" hidden>
            <tr>
                <td><p class="rem1"><b>Franchise</b></p></td>
                <td><p class="rem1"><b>Franchise phone</b></p></td>
                <td><p class="rem1"><b>Elaboration Time</b></p></td>
                <td><p class="rem1"><b>Client address</b></p></td>
                <td><p class="rem1"><b>Client phone</b></p></td>
                <td><p class="rem1"><b>Total Price</b></p></td>
                <td><p class="rem1"><b>Tip</b></p></td>
            </tr>
            <tr>
                <td id="foNameDeliver"></td>
                <td id="foPhoneDeliver"></td>
                <td id="elaborationTimeDeliver"></td>
                <td id="clientAddressDeliver"></td>
                <td id="clientPhoneDeliver"></td>
                <td id="totalPriceDeliver"></td>
                <td id="tipDeliver"></td>
            </tr>
        </table>
        <button type="button" id="finishDelivering" style="height: 40%; margin-top: 4.5rem;"
                class="btn btn-success spaced mr-auto ml-auto" onclick="finishDelivering()">Order<br>delivered
        </button>
    </div>
</div>


<%--modal for confirming--%>
<div class="modal fade bd-example-modal-sm" id="options" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
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
                            <td><p class="rem1"><b>Franchise</b></p></td>
                            <td id="foName"></td>
                        </tr>
                        <tr>
                            <td><p class="rem1"><b>Elaboration Time</b></p></td>
                            <td id="elaborationTime"></td>
                        </tr>
                        <tr>
                            <td><p class="rem1"><b>Client Address</b></p></td>
                            <td id="clientAddress"></td>
                        </tr>
                        <tr>
                            <td><p class="rem1"><b>Client phone</b></p></td>
                            <td id="clientPhone"></td>
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
                    <button type="button" onclick="refuseOrder()" class="btn btn btn-danger" data-dismiss="modal">
                        Refuse
                    </button>
                    <button type="button" onclick="acceptOrder()" class="btn btn-success">Accept</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%--<div class="row" id="options" hidden>--%>
<%--<button type="button" value="accept" name="accept" class="btn btn-outline-dark spaced-top" onclick="acceptOrder()"><i class="fas fa-power-off"></i> Accept</button>--%>
<%--<button type="button" value="accept" name="accept" class="btn btn-outline-dark spaced-top" onclick="refuseOrder()"><i class="fas fa-power-off"></i> Refuse</button>--%>
<%--</div>--%>

<script src="../../../js/dg/sw.js"></script>
<script src="../../../js/dg/notificationDG.js"></script>
<script src="../../../js/dg/dgMenu.js"></script>
<jsp:include page="../bootstrapImportBody.jsp"/>
</body>
</html>