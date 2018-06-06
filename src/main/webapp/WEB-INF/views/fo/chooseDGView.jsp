<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <jsp:include page="../bootstrapImportHeader.jsp"/>
    <title>Choose delivery-guy</title>
    <link rel="stylesheet" href="../../../css/foober.css" type="text/css">
</head>
<body class="outer" onbeforeunload="closeSocket()">

<div id="orderRejected"></div>

<div class="fixed-panel">
    <h2 class="mb-3">Choose your delivery-guy</h2>
    <table class="table table-striped table-hover table-bordered" id="table">
        <thead>
        <tr>
            <th class="text-center" scope="col">Name</th>
            <th class="text-center" scope="col">Status</th>
            <th class="text-center" scope="col" style="width: 20%;">Means Of Transport</th>
            <th class="text-center" scope="col">Phone</th>
            <th class="text-center" scope="col">Rating</th>
        </tr>
        </thead>
        <tbody id="dgTable"></tbody>
    </table>
</div>

<%--modal for confirming--%>
<div class="modal fade bd-example-modal-sm" id="modalConfirmDG" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content" style="width: 120%">
            <form method="post">
                <div class="modal-header">
                    <h5 class="modal-title">Confirm DG selection</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <h4 style="font-size: 1rem">Are you sure you want to choose this Delivery Guy?</h4>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="button" onclick="chooseDg()" class="btn btn-primary">Confirm</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--Waiting for response--%>
<div id="waitingForResponse" hidden>
    <div class="spinner" id="spinner">
        <h3 align="center">Waiting for response...</h3>
        <div class="bounce1"></div>
        <div class="bounce2"></div>
        <div class="bounce3"></div>
    </div>
</div>


<%--Order Info (Hidden)--%>
<div id="order" hidden>
    <input id="id" value="${order.id}">
    <input id="foName" value="${order.franchiseOwner.name}">
    <input id="foPhone" value="${order.franchiseOwner.phone}">
    <input id="elaborationTime" value="${order.elaborationTime}">
    <input id="clientPhone" value="${order.client.phone}">
    <input id="tippingPercentage" value="${order.franchiseOwner.tippingPercentage}">
    <input id="totalPrice" value="${order.getTotalCost()}">
    <input id="clientAddress" value="${order.client.address}">
    <input id="clientEmail" value="${order.client.email}">
    <input id="dgName" value="${order.deliveryGuy.name}">
    <input id="dgPhone" value="${order.deliveryGuy.phone}">
    <input id="clientName" value="${order.client.name}">
</div>

<%--Choose DG later--%>
<footer class="footer fixed-bottom mb-3 ml-3">
    <span id="popoverChooseDGLater" data-toggle="popover" data-html="true" data-placement="top"
          data-content='
        <div class="d-flex align-items-center" >
            <h6 class="mr-3 title-popover">No delivery guys online? Click here!</h6>
            <button id="closeChooseDGLater" type="button" class="close" data-dismiss="popover" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <div>'
    >
        <a id="chooseDGLater" href="${pageContext.request.contextPath}/foMenu" class="btn btn-outline-dark"><i class="fas fa-clock" style="color:black"></i> Choose delivery-guy later</a>
    </span>
</footer>

<jsp:include page="../bootstrapImportBody.jsp"/>
<script src="../../../js/utils.js"></script>
<script src="../../../js/starRating.js"></script>
<script src="../../../js/fo/chooseDG.js"></script>
<script src="../../../js/popoverUtils.js"></script>
<script>

</script>

</body>
</html>
