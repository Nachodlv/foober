<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="bootstrapImportHeader.jsp"></jsp:include>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../css/foMenuView.css" type="text/css">
    <link rel="stylesheet" href="../../css/foober.css" type="text/css">
    <title>FO Menu</title>
</head>

<body>

<jsp:include page="_menu.jsp"></jsp:include>

<h3 class="ml-2">Franchise Owner Menu</h3>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Create new client</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" enctype="multipart/form-data" >
                    <div class="align-items-center">
                        <div class="">
                            <label for="inlineFormInput">Name</label>
                            <input type="text" class="form-control mb-2" id="inlineFormInput" placeholder="Name" name="clientName" required>
                        </div>
                        <div class="">
                            <label for="inlinePhoneInput">Phone</label>
                            <input type="text" class="form-control mb-2" id="inlinePhoneInput" placeholder="Phone" name="clientPhone"  required>
                        </div>
                        <div class="">
                            <label for="inlineAddressInput">Address</label>
                            <input type="text" class="form-control mb-2" id="inlineAddressInput" placeholder="Address" name="clientAddress" required>
                        </div>
                        <div class="">
                            <label for="inlineEmailInput">Email</label>
                            <input type="email" class="form-control mb-2" id="inlineEmailInput" placeholder="Email" name="clientEmail" required>
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

<%--Orders container--%>
<div class="row container mt-3">
<div class="container col-6">
    <form method="post" action="${pageContext.request.contextPath}/foMenu" >
        <div class="form-inline row mb-2">
            <input type="text" class="form-control col-4 ml-2" placeholder="Search by name" name="searchClient" value="${searchClient}">
            <a class="col-1" href="${pageContext.request.contextPath}/foMenu"><i class="fas fa-times icon"></i></a>
            <button type="submit" class="btn btn-info col-3">Search</button>
            <button type="button" class="btn btn-primary col-3 ml-2" data-toggle="modal" data-target="#exampleModal">New client</button>
            <h4 style="color: red;" class="text-danger">${errorPhone}</h4>
        </div>
        <table class="table table-striped table-hover table-bordered">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Phone</th>
                <th scope="col">Address</th>
                <th scope="col">Email</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${clients}"  var="client">
                <tr onclick="window.location = '${pageContext.request.contextPath}/makingOrder?clientId=${client.id}';">
                    <td>${client.name}</td>
                    <td>${client.phone}</td>
                    <td>${client.address}</td>
                    <td>${client.email}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
<%--Currnet orders--%>
<div class="container col-6"></div>
</div>

<jsp:include page="bootstrapImportBody.jsp"></jsp:include>
</body>
</html>

