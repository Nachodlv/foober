<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <jsp:include page="bootstrapImportHeader.jsp"></jsp:include>
    <title>Title</title>
    <link rel="stylesheet" href="../../css/addProduct.css" type="text/css">
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>

<h1>Products: </h1>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Add product</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Add a product</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="align-items-center">
                        <div class="">
                            <label for="inlineFormInput">Product name</label>
                            <input type="text" class="form-control mb-2" id="inlineFormInput" placeholder="Product name">
                        </div>
                        <div class="">
                            <label for="inlinePriceInput">Price</label>
                            <input type="text" class="form-control mb-2" id="inlinePriceInput" placeholder="Price">
                        </div>
                        <div class="mt-3">
                            <button type="submit" class="btn btn-primary mb-2">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>



<%--List Products--%>
<ul>
    <c:forEach items="${products}"  var="product">
        <li>${product.name}     ${product.price}</li>
    </c:forEach>

    <jsp:include page="bootstrapImportBody.jsp"></jsp:include>
</ul>

<script>
    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').trigger('focus')
    })
</script>
</body>
</html>
