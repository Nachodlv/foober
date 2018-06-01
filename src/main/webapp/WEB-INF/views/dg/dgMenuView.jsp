<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../bootstrapImportHeader.jsp"/>
    <title>DG Menu</title>
    <link rel="stylesheet" href="../../../css/foober.css" type="text/css">
    <link rel="stylesheet" href="../../../css/dg/dgMenu.css" type="text/css">
</head>
<body class="outer">
<jsp:include page="../_header.jsp"/>
<h2>Welcome, ${loginedUser.name}!</h2>
<h6 id="ratingTitle" style="margin-bottom: 2rem;" class="text-muted"></h6>

<div class="bg-light rounded">
    <form method="post" action="${pageContext.request.contextPath}/dgMenu">
        <div id="deliveryGuy">
            <input hidden value="${loginedUser.name}">
            <input hidden value="${loginedUser.phone}">
            <input hidden value="${loginedUser.meansOfTransport}">
            <input hidden value="${loginedUser.email}">
            <input hidden id="rating" value="${loginedUser.rating}">
            <input hidden id="ratingQuantity" value="${loginedUser.ratingQuantity}">
            <input hidden id="dgState" value="${loginedUser.state}">
        </div>
        <div class="d-flex">
            <div>
                <img width="120" height="120" class="rounded-circle"
                     src="${pageContext.request.contextPath}/images?imgID=${loginedUser.email}.png">
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
    <div class="spinner" id="spinner" hidden>
        <h3 align="center">Waiting for orders...</h3>
        <div class="bounce1"></div>
        <div class="bounce2"></div>
        <div class="bounce3"></div>
    </div>

    <%--Order when delivering--%>
        <div id="order" class="mt-5 rounded d-flex flex-column justify-content-center" style="background:#F8F8FA;" hidden>
        <table id="tableOrder" class="table" hidden>
            <tr>
                <td><p class="rem1"><b>Franchise</b></p></td>
                <td id="foNameDeliver"></td>
            </tr>
            <tr>
                <td><p class="rem1"><b>Franchise phone</b></p></td>
                <td id="foPhoneDeliver"></td>
            </tr>
            <tr>
                <td><p class="rem1"><b>Elaboration Time</b></p></td>
                <td id="elaborationTimeDeliver"></td>
            </tr>
            <tr>
                <td><p class="rem1"><b>Client address</b></p></td>
                <td id="clientAddressDeliver"></td>
            </tr>
            <tr>
                <td><p class="rem1"><b>Client phone</b></p></td>
                <td id="clientPhoneDeliver"></td>
            </tr>
            <tr>
                <td><p class="rem1"><b>Total Price</b></p></td>
                <td id="totalPriceDeliver"></td>
            </tr>
            <tr>
                <td><p class="rem1"><b>Tip</b></p></td>
                <td id="tipDeliver"></td>
            </tr>
        </table>
        <button type="button" id="finishDelivering" class="btn btn-outline-success spaced mr-auto ml-auto" onclick="finishDelivering()" hidden>Order delivered</button>
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

<%--Time Out LogOut--%>
<div class="modal fade bd-example-modal-sm" id="timeOutModal" tabindex="-1" role="dialog"
     aria-labelledby="timeOutModal" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="orderAcceptedLabel">Logged out</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                You were logged out due to inactivity.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script src="../../../js/utils.js"></script>
<script src="../../../js/starRating.js"></script>
<script src="../../../js/dg/sw.js"></script>
<script src="../../../js/dg/notificationDG.js"></script>
<script src="../../../js/dg/dgMenu.js"></script>
<jsp:include page="../bootstrapImportBody.jsp"/>
</body>
</html>