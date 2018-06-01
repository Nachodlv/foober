<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <jsp:include page="../bootstrapImportHeader.jsp"/>
    <link rel="stylesheet" href="../../../css/foober.css" type="text/css">
    <link rel="stylesheet" href="../../../css/ratingStar.css" type="text/css">
    <link rel="stylesheet" href="../../../css/fo/orderInfo.css" type="text/css">
    <title>Order Info</title>
</head>
<body class="outer">

<div class="container text-center">
    <div class="row">
        <div class="col d-flex flex-column">
            <h5>Useful Information</h5>
            <h4 class="text-muted" id="order"></h4>
            <table class="table-bordered" align="center" style="text-align:center;">
                <tbody>
                <tr>
                    <td style="padding: 1rem;">
                        <b>Client</b>
                    </td>
                    <td></td>
                    <td style="padding: 1rem;"><b>Delivery Guy</b></td>
                </tr>
                <tr>
                    <td>
                        <img class="rounded-circle" src="${pageContext.request.contextPath}/images/anonymus.png"
                             style="width: 8rem;">
                    </td>
                    <td></td>
                    <td>
                        <span id="popoverAssignDG" data-toggle="popover" data-html="true" data-placement="right"
                              data-content='
                                <div class="d-flex align-items-center" >
                                    <h6 class="mr-3 title-popover">Press here to assign a Delivery Guy!</h6>
                                    <button id="closeAssignDG" type="button" class="close" data-dismiss="popover" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                <div>'>
                            <img src="" id="imgDG" style="width: 8rem;" class="rounded-circle">
                        </span>
                    </td>
                </tr>
                <tr>
                    <td id="clientName" class="p-2"></td>
                    <td style="padding: 1rem;"><i class="fas fa-info"></i></td>
                    <td id="dgName" class="p-2"></td>
                </tr>
                <tr>
                    <td id="clientPhone" class="p-2"></td>
                    <td style="padding: 1rem;"><i class="fas fa-phone"></i></td>
                    <td id="dgPhone" class="p-2"></td>
                </tr>
                <tr>
                    <td id="clientEmail" class="p-2"></td>
                    <td style="padding: 1rem;"><i class="fas fa-envelope"></i></td>
                    <td id="dgEmail" class="p-2"></td>
                </tr>
                <tr>
                    <td id="clientAddress" class="p-2"></td>
                    <td style="padding: 1rem;"><i class="fas fa-map-marker"></i></td>
                    <td>-</td>
                </tr>
                </tbody>
            </table>
            <button id="but" hidden style="margin-top: 1rem; margin-left: 18rem; margin-right: 7rem" class="btn btn-info">Button</button>
        </div>

        <div class="col fixed-panel-big">
            <h5 class="">Ordered products</h5>
            <h6 class="text-muted" id="totalCost"></h6>
            <table class="table-striped" style="text-align:center; width: 100%;">
                <tbody id="productsTBody"></tbody>
            </table>
        </div>
    </div>
</div>

<footer class="footer fixed-bottom mb-3 ml-3">
    <a href="${pageContext.request.contextPath}/foMenu" class="btn btn-outline-dark"><i class="fas fa-undo" style="color:black"></i> Return
        to Main Menu</a>
</footer>

<div class="modal fade bd-example-modal-sm" id="rateModal" tabindex="-1" role="dialog"
     aria-labelledby="rateModal" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="rateLabel">Rating</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body d-flex flex-column justify-content-center">
                <span class="titleModal">Please rate the delivery-guy</span>
                <%--Star rating--%>
                <div class="wrapper">
                    <input type="checkbox" id="st5" value="5" onclick="starClick(5)" />
                    <label id="label5" for="st5"></label>
                    <input type="checkbox" id="st4" value="4" onclick="starClick(4)"/>
                    <label id="label4" for="st4"></label>
                    <input type="checkbox" id="st3" value="3" onclick="starClick(3)"/>
                    <label id="label3" for="st3"> </label>
                    <input type="checkbox" id="st2" value="2" onclick="starClick(2)"/>
                    <label id="label2" for="st2"></label>
                    <input type="checkbox" id="st1" value="1" onclick="starClick(1)"/>
                    <label id="label1" for="st1"></label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<%--Order delivered modal--%>
<div class="modal fade bd-example-modal-sm" id="deliveredModal" tabindex="-1" role="dialog"
    aria-labelledby="deliveredModal" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deliveredLabel">Delivered</h5>
                <button type="button" class="close"onclick="closeDeliveredModal()" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body d-flex flex-column justify-content-center">
                <span id="orderDelivered">Your order was delivered!</span>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" onclick="closeDeliveredModal()">Close</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../bootstrapImportBody.jsp"/>
<script src="../../../js/utils.js"></script>
<script src="../../../js/starRating.js"></script>
<script src="../../../js/fo/orderInfo.js"></script>
</body>
</html>
