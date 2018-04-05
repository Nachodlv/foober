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
<!-- Trigger the modal with a button -->
<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="form-inline">
                    <label class="sr-only" for="inlineFormInput">Name</label>
                    <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" id="inlineFormInput" placeholder="Jane Doe">

                    <label class="sr-only" for="inlineFormInputGroup">Username</label>
                    <input type="number" value="1000" min="0" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" class="form-control currency" id="inlineFormInputGroup" />

                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
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
