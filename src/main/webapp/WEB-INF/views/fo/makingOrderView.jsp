<%--
  Created by IntelliJ IDEA.
  User: Ignacio
  Date: 4/14/2018
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="../bootstrapImportHeader.jsp"></jsp:include>
    <title>Title</title>
    <link rel="stylesheet" href="../../../css/foober.css" type="text/css">
    <link rel="stylesheet" href="../../../css/dg/makingOrderView.css" type="text/css">
</head>
<body class="outer">
<jsp:include page="../bootstrapImportBody.jsp"></jsp:include>
<jsp:include page="../_header.jsp"></jsp:include>
<h1>Menu</h1>
<form method="post" action="${pageContext.request.contextPath}/makingOrder">
    <div class="row form-inline mb-3">
        <input type="text" class="form-control ml-2" placeholder="Search by name" name="searchProduct" value="${pageContext.request.getParameter("searchProduct")}">
        <a class="ml-2" href="${pageContext.request.contextPath}/makingOrder"><i class="fas fa-times icon"></i></a>
        <button type="submit" class="btn btn-info ml-4">Search</button>
        <span id="totalPrice" class="ml-auto mr-3"><a class="btn btn-outline-success" >Make order - 0 $</a></span>
    </div>
</form>
<form method="post" action="${pageContext.request.contextPath}/makingOrder" >
    <div class="row fixed-panel">
        <table class="table table-striped" style="margin-right:1%;">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">Name</th>
                <th scope="col">Price</th>
                <th scope="col">Quantity</th>
                <th scope="col">Total Price</th>
                <th scope="col">Comment</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}"  var="product">
                <tr class="product-row">
                    <td><img width="100" height="100" src="http://localhost:8080/images/${product.id}.png"></td>
                    <td>${product.name}</td>
                    <td price="${product.price}" class="productPrice">${product.price} $</td>
                    <td style="width: 12%;"><input name = "${product.id}" class="quantityInput" type="number" min="0"></td>
                    <td id="totalPriceProduct" class="totalPriceProduct">0 $</td>
                    <td><input type="text" class="form-control ml-2" name="comment${product.id}" placeholder="Add comment..."></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <input hidden id="total">
    <div id="modal" class="modal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-making-order" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Review Order</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <table id="products" class="order-table" style="margin-bottom:2%;">
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Total Price</th>
                            <th scope="col">Comment</th>
                        </tr>
                    </table>
                    <div class="ml-1">
                        <h5 id="total-price-check"></h5>
                        <input id="tipping-percentage" hidden value="${loginedUser.tippingPercentage}">
                        <h5 id="delivery-man-tip"></h5>
                        <div class="row" style="margin-left: 0">
                            <h5 class="mr-2">Estimated elaboration time: </h5>
                            <input class="elaboration-time form-control-sm" type="number" placeholder="elaboration time" name="elaborationTime" min="0" required>
                            <h5 class="ml-2">minutes</h5>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="make-order-submit" type="submit" class="btn btn-primary" name="MakeOrder" value="Order made">Make order</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</form>
<footer class="footer fixed-bottom mb-3 ml-3">
    <a href="${pageContext.request.contextPath}/foMenu" class="btn btn-outline-dark"><i class="fas fa-undo" style="color:black"></i> Return to Main Menu</a>
</footer>
<script src="../../../js/fo/makingOrder.js"></script>
<script src="../../../js/replaceNoImg.js"></script>
<script>
    $("#totalPrice").click(function () {
        $("#modal").modal();
        completeOrder();
    })
</script>
</body>
</html>
