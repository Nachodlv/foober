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
    <jsp:include page="bootstrapImportHeader.jsp"></jsp:include>
    <title>Title</title>
    <link rel="stylesheet" href="../../css/foober.css" type="text/css">
    <link rel="stylesheet" href="../../css/makingOrderView.css" type="text/css">
</head>
<body class="outer">
<jsp:include page="bootstrapImportBody.jsp"></jsp:include>
<div class="row">
    <h1>Menu</h1>
    <span class="text-muted return-to-menu"><a href="${pageContext.request.contextPath}/foMenu" class="btn btn-outline-dark"><i class="fas fa-undo" style="color:black"></i> Return to Main Menu</a></span>
</div>
<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/editMenu" >
    <div class="row">
        <table class="table table-striped">
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
                    <td  price="${product.price}" class="productPrice">${product.price} $</td>
                    <td><input id = "quantityProduct${product.id}" class="quantityInput" type="number" name="quantity" min="0"></td>
                    <td id="totalPriceProduct${product.id}">0 $</td>
                    <td><input type="text" name="comment" placeholder="Add comment..."></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>
<script src="../../js/makingOrder.js"></script>
</body>
</html>
