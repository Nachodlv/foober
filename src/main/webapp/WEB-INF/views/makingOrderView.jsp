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
<div class="row">
    <h1>HI ${pageContext.request.getParameter("clientId")}</h1>
    <span class="text-muted d-flex justify-content-start"><a href="${pageContext.request.contextPath}/foMenu" class="btn btn-outline-secondary"><i class="fas fa-undo" style="color:black"></i> Return to Main Menu</a></span>
</div>
<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/editMenu" >
    <div class="row">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">Name</th>
                <th scope="col">Price</th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}"  var="product">
                <tr>
                    <td><img width="100" height="100" src="http://localhost:8080/images/${product.id}.png"></td>
                    <td>${product.name}</td>
                    <td>${product.price} $</td>
                    <td><button class="buttonWithFunction" type="button" data-toggle="modal" data-target="#exampleModalEdit${product.id}"><i class="fas fa-edit fa-sm"></i></button></td>
                    <td><button class="buttonWithFunction" type="submit" name="delete" value=${product.id}><i class="fas fa-times" style="color:Tomato"></i></button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <footer class="footer mb-3">
        <div class="container">
            <span class="text-muted d-flex justify-content-start"><a href="${pageContext.request.contextPath}/foMenu" class="btn btn-outline-secondary"><i class="fas fa-undo" style="color:black"></i> Return to Main Menu</a></span>
        </div>
    </footer>

    <jsp:include page="bootstrapImportBody.jsp"></jsp:include>
</body>
</html>
