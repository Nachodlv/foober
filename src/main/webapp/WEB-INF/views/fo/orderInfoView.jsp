<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <jsp:include page="../bootstrapImportHeader.jsp"/>
    <link rel="stylesheet" href="../../../css/foober.css" type="text/css">
    <title>Order Info</title>
</head>
<body class="outer">

<div class="container text-center">
    <div class="row">
        <div class="col">
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
                        <img src="" id="imgDG" style="width: 8rem;" class="rounded-circle">
                    </td>
                </tr>
                <tr>
                    <td id="clientName"></td>
                    <td style="padding: 1rem;"><i class="fas fa-info"></i></td>
                    <td id="dgName"></td>
                </tr>
                <tr>
                    <td id="clientPhone"></td>
                    <td style="padding: 1rem;"><i class="fas fa-phone"></i></td>
                    <td id="dgPhone"></td>
                </tr>
                <tr>
                    <td id="clientEmail"></td>
                    <td style="padding: 1rem;"><i class="fas fa-envelope"></i></td>
                    <td id="dgEmail"></td>
                </tr>
                <tr>
                    <td id="clientAddress"></td>
                    <td style="padding: 1rem;"><i class="fas fa-map-marker"></i></td>
                    <td>-</td>
                </tr>
                </tbody>
            </table>
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
    <a href="${pageContext.request.contextPath}/foMenu" class="btn btn-outline-dark"><i class="fas fa-undo"
                                                                                        style="color:black"></i> Return
        to Main Menu</a>
</footer>

<script src="../../../js/fo/orderInfo.js"></script>
<jsp:include page="../bootstrapImportBody.jsp"/>
</body>
</html>
