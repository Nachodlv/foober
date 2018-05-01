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

<body class="outer">

<jsp:include page="_header.jsp"></jsp:include>
<%--<h3 class="ml-2">Franchise Owner Menu</h3>--%>

<%--Current orders--%>
<br>
<container class="form-row" style="margin-top: -0.5rem">
<div class="container col-6">
    <div class="col-md-12"> <%--adds spacing between cols--%>
    <h3 align="center" class="title">Current Orders</h3>
        <div class="fixed-panel">
    <div class="card-group">
        <div class="card bg-light" style="width: 10rem;" align="center">
            <img class="card-img-top" style="width: 8rem;" src="../../images/FooberLogo.png" align="center">
            <div class="card-body">
                <h5 class="card-title">DG: foober</h5>
                <p class="card-text">Gonzalo cliente</p>
                <p class="card-text">20 minutes ago$</p>
                <p class="card-text">60$</p>
            </div>
        </div>
        <div class="card bg-light" style="width: 10rem;" align="center">
            <img class="card-img-top" style="width: 8rem;" src="../../images/FooberLogo.png" align="center">
            <div class="card-body">
                <h5 class="card-title">DG: foober2</h5>
                <p class="card-text">Nacho cliente</p>
                <p class="card-text">10 minutes ago$</p>
                <p class="card-text">70$</p>
            </div>
        </div>
    </div>
        <div class="card-group" style="margin-top:1%;">
            <div class="card bg-light" style="width: 10rem;" align="center">
                <img class="card-img-top" style="width: 8rem;" src="../../images/FooberLogo.png" align="center">
                <div class="card-body">
                    <h5 class="card-title">DG: foober</h5>
                    <p class="card-text">Gonzalo cliente</p>
                    <p class="card-text">20 minutes ago$</p>
                    <p class="card-text">60$</p>
                </div>
            </div>
            <div class="card bg-light" style="width: 10rem;" align="center">
                <img class="card-img-top" style="width: 8rem;" src="../../images/FooberLogo.png" align="center">
                <div class="card-body">
                    <h5 class="card-title">DG: foober2</h5>
                    <p class="card-text">Nacho cliente</p>
                    <p class="card-text">10 minutes ago$</p>
                    <p class="card-text">70$</p>
                </div>
            </div>
        </div>
        </div>
    </div>
</div>

<%--Clients container--%>
<div class="container col-6">
    <h3 align="center" class="title">Clients</h3>

    <div class="col-md-12">
    <form method="post" action="${pageContext.request.contextPath}/foMenu" >
        <div class="form-inline row mb-2">
            <input type="text" class="form-control col-4 ml-2" placeholder="Search by name" name="searchClient" value="${pageContext.request.getParameter("searchClient")}">
            <a class="col-1" href="${pageContext.request.contextPath}/foMenu"><i class="fas fa-times icon"></i></a>
            <button type="submit" class="btn btn-info col-3">Search</button>
            <button type="button" class="btn btn-primary col-3 ml-2" data-toggle="modal" data-target="#exampleModal">New client</button>
        </div>
        <div class="errorCatcher"></div>
    </form>
        <div class="fixed-panel">
    <table class="table table-striped table-hover table-bordered">
        <thead>
        <tr>
            <th class="text-center" scope="col">Name</th>
            <th class="text-center" scope="col">Phone</th>
            <th class="text-center" scope="col">Address</th>
            <th class="text-center" scope="col">Email</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${clients}" var="client">
            <tr>
                <td onclick="window.location = '${pageContext.request.contextPath}/makingOrder?clientId=${client.id}';">${client.name}</td>
                <td onclick="window.location = '${pageContext.request.contextPath}/makingOrder?clientId=${client.id}';">${client.phone}</td>
                <td onclick="window.location = '${pageContext.request.contextPath}/makingOrder?clientId=${client.id}';">${client.address}</td>
                <td onclick="window.location = '${pageContext.request.contextPath}/makingOrder?clientId=${client.id}';">${client.email}</td>
                <td class="clientEdit" align="center"><button type="button" class="buttonWithFunction"><i class="fas fa-edit" data-toggle="modal" data-target="#clientModal${client.id}"></i></button> </td>
            </tr>
            <div class="modal fade" id="clientModal${client.id}" tabindex="-1" role="dialog" aria-labelledby="clientModalLabel${client.id}" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="clientModalLabel${client.id}">Create new client</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form method="post" enctype="multipart/form-data" >
                                <div class="">
                                    <div class="align-items-center">
                                        <label for="inlineFormInputClient${client.id}">Name</label>
                                        <input type="text" class="form-control mb-2" id="inlineFormInputClient${client.id}" placeholder="Name" name="clientName" value="${client.name}" required>
                                    </div>
                                    <div class="">
                                        <label for="inlinePhoneInputClient${client.id}">Phone</label>
                                        <input type="text" class="form-control mb-2" id="inlinePhoneInputClient${client.id}" placeholder="Phone" name="clientPhone" value="${client.phone}" required>
                                    </div>
                                    <div class="">
                                        <label for="inlineAddressInputClient${client.id}">Address</label>
                                        <input type="text" class="form-control mb-2" id="inlineAddressInputClient${client.id}" placeholder="Address" name="clientAddress" value="${client.address}" required>
                                    </div>
                                    <div class="">
                                        <label for="inlineEmailInputClient${client.id}">Email</label>
                                        <input type="email" class="form-control mb-2" id="inlineEmailInputClient${client.id}" placeholder="Email" name="clientEmail" value="${client.email}" required>
                                    </div>
                                    <div class="mt-3 mb-2 row">
                                        <button type="button" class="btn btn-danger ml-3" data-toggle="modal" data-target="#deleteClient${client.id}">Delete Client</button>
                                        <button type="submit" class="btn btn-primary ml-auto mr-3" value="${client.id}" name="editClient">Save changes</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" value="Submit">Close</button>
                </div>
            </div>
            <div class="modal fade bd-example-modal-sm" id="deleteClient${client.id}" tabindex="-1" role="dialog" aria-labelledby="titleModalDelete${client.id}" aria-hidden="true">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content" style="width: 120%">
                        <form method="post" enctype="multipart/form-data">
                            <div class="modal-header">
                                <h5 class="modal-title" id="titleModalDelete${client.id}">Client deletion</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <h4 style="font-size: 1rem">Are you sure you want to delete this client?</h4>
                            </div>
                            <div class="modal-footer">
                                <button  type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                <button type="submit" class="btn btn-primary" name="deleteClient" value="${client.id}" >Delete client</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
        </tbody>
    </table>
</div>
    </div>
</div>
</container>

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
                    <div class="">
                        <div class="align-items-center">
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
                            <button type="submit" class="btn btn-primary mb-2" value="submit" name="newClient">Submit</button>
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
    <br>
<footer class="footer fixed-bottom mb-3 ml-3">
    <a href="${pageContext.request.contextPath}/editMenu" class="btn btn-outline-secondary "><i class="fas fa-cogs" style="color:black"></i> Edit menu</a>
</footer>

<script src="../../js/errorCatcher.js"></script>
<jsp:include page="bootstrapImportBody.jsp"></jsp:include>

</body>
</html>

