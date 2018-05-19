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
            <h4 class="text-muted">#${order.id}</h4>
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
                        <c:choose>
                            <c:when test="${order.deliveryGuy != null}">
                                <img src="${pageContext.request.contextPath}/images/${order.deliveryGuy.email}.png"
                                     style="width: 8rem;" class="rounded-circle">
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/chooseDG">
                                    <img src="${pageContext.request.contextPath}/images/anonymus.png"
                                         style="width: 8rem;" class="rounded-circle">
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td>${order.client.name}</td>
                    <td style="padding: 1rem;"><i class="fas fa-info"></i></td>
                    <td>
                        ${order.deliveryGuy != null ? order.deliveryGuy.name : "not assigned"}
                    </td>
                </tr>
                <tr>
                    <td>${order.client.phone}</td>
                    <td style="padding: 1rem;"><i class="fas fa-phone"></i></td>
                    <td>
                        ${order.deliveryGuy != null ? order.deliveryGuy.phone : "not assigned"}
                    </td>
                </tr>
                <tr>
                    <td>${order.client.email}</td>
                    <td style="padding: 1rem;"><i class="fas fa-envelope"></i></td>
                    <td>
                        ${order.deliveryGuy != null ? order.deliveryGuy.email : "not assigned"}
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="col fixed-panel-big">
            <h5 class="">Ordered products</h5>
            <h6 class="text-muted">Total cost: ${order.getTotalCost()}$</h6>
            <table class="table-striped" style="text-align:center; width: 100%;">
                <tbody>
                <c:forEach items="${order.orderedProducts}" var="product">
                    <tr>
                        <td class="col" style="width:20%; padding: 0.6rem;"><img width="100" height="100"
                                                                                 src="${pageContext.request.contextPath}/images/${product.product.id}.png">
                        </td>
                        <td class="col" style="width:40%">
                            <p><b>${product.product.name}</b> x${product.quantity} </p>
                        </td>
                        <td class="col" style="width:40%">
                            <p> ${product.comment != "" ? product.comment : "<i>no comment</i>"}</p>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<footer class="footer fixed-bottom mb-3 ml-3">
    <a href="${pageContext.request.contextPath}/foMenu" class="btn btn-outline-dark"><i class="fas fa-undo" style="color:black"></i> Return to Main Menu</a>
</footer>

<jsp:include page="../bootstrapImportBody.jsp"/>
</body>
</html>
