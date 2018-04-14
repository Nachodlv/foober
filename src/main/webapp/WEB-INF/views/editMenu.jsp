<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <jsp:include page="bootstrapImportHeader.jsp"></jsp:include>
    <title>Title</title>
    <link rel="stylesheet" href="../../css/foober.css" type="text/css">
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>

<h1>Products: </h1>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Add product</button>
<h4 style="color: red;" class="text-danger">${errorPrice}</h4>
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
<form method="post" action="${pageContext.request.contextPath}/editMenu" >
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
                    <td><img width="100" height="100" src="http://localhost:8080/productPic?id=${product.id}"></td>
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
                                            <p style="color: red;" class="text-danger">${errorPrice}</p>
                                        </div>

                                        <label>Edit product photo</label>
                                        <br>
                                        <img width="100" height="100" src="http://localhost:8080/productPic?id=${product.id}">
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
</form>





<jsp:include page="bootstrapImportBody.jsp"></jsp:include>

</body>
</html>
