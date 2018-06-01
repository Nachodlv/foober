<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <jsp:include page="../bootstrapImportHeader.jsp"/>
    <title>Edit Menu</title>
    <link rel="stylesheet" href="../../../css/foober.css" type="text/css">
</head>

<body class="outer">
<jsp:include page="../_header.jsp"/>
<h1 align="left">Products: </h1>
<form class="form-inline row" enctype="multipart/form-data">
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary ml-4 mr-5" data-toggle="modal" data-target="#exampleModal" onclick="closePopover()">Add product</button>
    <input type="text" class="form-control ml-2" placeholder="Search by name" name="searchProduct" value="${pageContext.request.getParameter("searchProduct")}">
    <a class="ml-2" href="${pageContext.request.contextPath}/editMenu"><i class="fas fa-times icon"></i></a>
    <button type="submit" class="btn btn-info ml-4">Search</button>
</form>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Add a product</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" enctype="multipart/form-data">
                    <div class="align-items-center">
                        <div class="">
                            <label for="inlineFormInput">Product name</label>
                            <input type="text" class="form-control mb-2" id="inlineFormInput" placeholder="Product name" name="productName"required>
                        </div>
                        <div class="">
                            <label for="inlinePriceInput">Price</label>
                            <input type="text" class="form-control mb-2" id="inlinePriceInput" placeholder="Price" name="productPrice"  required>
                        </div>
                        <div class ="">
                            <label for="productPic">Add product photo</label>
                            <br>
                            <input type="file" id="productPic" name="productPic" accept=".jpg, .jpeg, .png" class="file">
                        </div>
                        <div class="mt-3">
                            <button type="submit" class="btn btn-primary mb-2" value="submit" name="submit">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" value="Submit">Close</button>
            </div>
        </div>
    </div>
</div>

<%--List Products--%>
<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/editMenu" >
    <div class="fixed-panel">
        <div class="errorCatcher"></div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">Name</th>
                <th scope="col">Price</th>
                <th scope="col">Edit</th>
                <th scope="col">Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}"  var="product">
                <tr>
                    <td><img width="100" height="100" src="http://localhost:8080/images?imgID=${product.id}.png"></td>
                    <td>${product.name}</td>
                    <td>${product.price} $</td>
                    <td><button class="buttonWithFunction" type="button" data-toggle="modal" data-target="#exampleModalEdit${product.id}"><i class="fas fa-edit fa-sm"></i></button></td>
                    <td><button class="buttonWithFunction" type="submit" name="delete" value=${product.id}><i class="fas fa-times" style="color:Tomato"></i></button></td>
                </tr>
                <%--Modal for editing--%>
                <div class="modal fade" id="exampleModalEdit${product.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabelEdit${product.id}">Add a product</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/editMenu" >
                                    <div class="align-items-center">
                                        <div class="">
                                            <label for="inlineFormInputEdit${product.id}">Product name</label>
                                            <input type="text" class="form-control mb-2" id="inlineFormInputEdit${product.id}" placeholder="Product name" name="productNameEdit" value="${product.name}" required>
                                        </div>
                                        <div class="">
                                            <label for="inlinePriceInputEdit${product.id}">Price</label>
                                            <input type="text" class="form-control mb-2" id="inlinePriceInputEdit${product.id}" placeholder="Price" name="productPriceEdit" value="${product.price}" required>
                                        </div>

                                        <label>Edit product photo</label>
                                        <br>
                                        <img width="100" height="100" src="http://localhost:8080/images?imgID=${product.id}.png">

                                        <input type="file" id="productPicEdit${product.id}" name="productPicEdit" accept=".jpg, .jpeg, .png" class="file">

                                        <div class="mt-3">
                                            <button type="submit" class="btn btn-primary mb-2" value="${product.id}" name="modify">Update Product</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal" value="Submit">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>
<footer class="footer fixed-bottom mb-3 ml-3">
    <a href="${pageContext.request.contextPath}/foMenu" class="btn btn-outline-dark"><i class="fas fa-undo" style="color:black"></i> Return to Main Menu</a>
</footer>


<jsp:include page="../bootstrapImportBody.jsp"/>
<script src="../../../js/replaceNoImg.js"></script>
<script src="../../../js/errorCatcher.js"></script>
<script src="../../../js/fo/editMenu.js"></script>
</body>
</html>
