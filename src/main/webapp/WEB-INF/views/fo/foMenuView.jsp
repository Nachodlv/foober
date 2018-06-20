<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="../bootstrapImportHeader.jsp"/>
    <link rel="stylesheet" href="../../../css/fo/foMenuView.css" type="text/css">
    <link rel="stylesheet" href="../../../css/foober.css" type="text/css">
    <title>FO Menu</title>
</head>

<body class="outer">

<jsp:include page="../_header.jsp"/>

<%--Current orders--%>
<input id="foID" hidden value=${loginedUser.email}>
<div class="form-row">
    <div class="container col-6">
        <div class="col-md-12"> <%--adds spacing between cols--%>
            <h3 align="center" class="title">Current Orders</h3>
            <div id="panel1" class="fixed-panel form-row" style="margin-top:2rem"></div>
        </div>
    </div>

    <%--Clients container--%>
    <div class="container col-6">
        <h3 align="center" class="title">Clients</h3>

        <div class="col-md-12" style="margin-top:2rem">
            <form method="post" action="${pageContext.request.contextPath}/foMenu">
                <div class="form-inline row mb-2">
                    <input type="text" class="form-control col-4 ml-2" placeholder="Search by name" name="searchClient"
                           value="${pageContext.request.getParameter("searchClient")}">
                    <a class="col-1" href="${pageContext.request.contextPath}/foMenu"><i class="fas fa-times icon"></i></a>
                    <button type="submit" class="btn btn-info col-3">Search</button>
                    <button type="button" class="btn btn-primary col-3 ml-2" onclick="openNewClientModal()">New client
                    </button>
                </div>

            </form>
            <span id="popoverChooseClient" class="col-3 ml-2" data-toggle="popover" data-html="true" data-placement="right"
                  data-content='
                            <div class="d-flex align-items-center" >
                                <h6 class="mr-3 title-popover">Choose a client to make a new order!</h6>
                                <button id="closeChooseClient" type="button" class="close" data-dismiss="popover" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            <div>'
            >
            <div class="fixed-panel">
                <div class="errorCatcher"></div>

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
                    <c:forEach items="${clients}" var="client" varStatus="loop">
                        <tr style="cursor: pointer">
                            <td class="limitWidth" onclick="window.location = '${pageContext.request.contextPath}/makingOrder?clientId=${client.id}';">${client.name}</td>
                            <td class="limitWidth" onclick="window.location = '${pageContext.request.contextPath}/makingOrder?clientId=${client.id}';">${client.phone}</td>
                            <td id="address${loop.index}" class="limitWidth" onclick="window.location = '${pageContext.request.contextPath}/makingOrder?clientId=${client.id}';"></td>
                            <input hidden id="idAddress${loop.index}" value="${client.address}">
                            <td class="limitWidth" onclick="window.location = '${pageContext.request.contextPath}/makingOrder?clientId=${client.id}';">${client.email}</td>
                            <td class="clientEdit" align="center">
                                <button type="button" class="buttonWithFunction"><i class="fas fa-edit"
                                                                                    onclick="openEditClient('${client.address}', '${client.id}')"></i>
                                </button>
                            </td>
                        </tr>
                        <div class="modal fade" id="clientModal${client.id}" tabindex="-1" role="dialog"
                             aria-labelledby="clientModalLabel${client.id}" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="clientModalLabel${client.id}">Create new client</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form method="post" enctype="multipart/form-data">
                                            <div class="">
                                                <div class="align-items-center">
                                                    <label for="inlineFormInputClient${client.id}">Name</label>
                                                    <input type="text" class="form-control mb-2"
                                                           id="inlineFormInputClient${client.id}" placeholder="Name"
                                                           name="clientName" value="${client.name}" required>
                                                </div>
                                                <div class="">
                                                    <label for="inlinePhoneInputClient${client.id}">Phone</label>
                                                    <input type="text" class="form-control mb-2"
                                                           id="inlinePhoneInputClient${client.id}" placeholder="Phone"
                                                           name="clientPhone" value="${client.phone}" required>
                                                </div>
                                                <div class="">
                                                    <label for="addressEdit${client.id}">Address</label>
                                                    <input id="addressEdit${client.id}" class="form-control mb-2" required>
                                                    <input hidden type="text"
                                                           id="idAddressEdit${client.id}"
                                                           value="${client.address}" name="clientAddress" required>
                                                </div>
                                                <div class="">
                                                    <label for="inlineEmailInputClient${client.id}">Email</label>
                                                    <input type="email" class="form-control mb-2"
                                                           id="inlineEmailInputClient${client.id}" placeholder="Email"
                                                           name="clientEmail" value="${client.email}" required>
                                                </div>
                                                <div class="mt-3 mb-2 row">
                                                    <button type="button" class="btn btn-danger ml-3"
                                                            data-toggle="modal" data-target="#deleteClient${client.id}">
                                                        Delete Client
                                                    </button>
                                                    <button type="submit" class="btn btn-primary ml-auto mr-3"
                                                            value="${client.id}" name="editClient">Save changes
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal" value="Submit">
                                            Close
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal fade bd-example-modal-sm" id="deleteClient${client.id}" tabindex="-1"
                             role="dialog" aria-labelledby="titleModalDelete${client.id}" aria-hidden="true">
                            <div class="modal-dialog modal-sm">
                                <div class="modal-content" style="width: 120%">
                                    <form method="post" enctype="multipart/form-data">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="titleModalDelete${client.id}">Client
                                                deletion</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <h4 style="font-size: 1rem">Are you sure you want to delete this
                                                client?</h4>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                Cancel
                                            </button>
                                            <button type="submit" class="btn btn-primary" name="deleteClient"
                                                    value="${client.id}">Delete client
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            </span>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Create new client</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form method="post" enctype="multipart/form-data">
                        <div class="">
                            <div class="align-items-center">
                                <label for="inlineFormInput">Name</label>
                                <input type="text" class="form-control mb-2" id="inlineFormInput" placeholder="Name"
                                       name="clientName" required>
                            </div>
                            <div class="">
                                <label for="inlinePhoneInput">Phone</label>
                                <input type="text" class="form-control mb-2" id="inlinePhoneInput" placeholder="Phone"
                                       name="clientPhone" required>
                            </div>
                            <div class="">
                                <label for="autocomplete">Address</label>
                                <input class="form-control" id="autocomplete" placeholder="Enter your address"
                                       onFocus="geolocate()" type="text" required>
                                <input hidden class="form-control" id="address" name="clientAddress">
                            </div>
                            <div class="">
                                <label for="inlineEmailInput">Email</label>
                                <input type="email" class="form-control mb-2" id="inlineEmailInput" placeholder="Email"
                                       name="clientEmail" required>
                            </div>
                            <div class="mt-3">
                                <button type="submit" class="btn btn-primary mb-2" value="submit" name="newClient">
                                    Submit
                                </button>
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
    <%--Modal order accepted--%>
    <div class="modal fade bd-example-modal-sm" id="orderAcceptedModal" tabindex="-1" role="dialog"
         aria-labelledby="orderAcceptedLabel" aria-hidden="true">
        <input id="orderAccepted" value="${pageContext.request.getParameter("orderAccepted")}" hidden>
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="orderAcceptedLabel">Success</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Order accepted!
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <%--Modal Disable popovers--%>
    <div class="modal fade bd-example-modal-sm" id="popoversModal" tabindex="-1" role="dialog"
         aria-labelledby="popoversModal" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Tutorial</h5>
                </div>
                <div class="modal-body d-flex flex-column justify-content-center mb-3">
                    <span>The tutorial's popovers are enabled.</span>
                    <span>Do you want to disable them?</span>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" onclick="disablePopovers()" data-dismiss="modal">Disable</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <footer class="footer fixed-bottom mb-3 ml-3">
        <span id="popoverEditMenu" data-toggle="popover" data-html="true" data-placement="top"
        data-content='
        <div class="d-flex align-items-center" >
            <h6 class="mr-3 title-popover">Add a new product to your menu!</h6>
            <button id="closeEditMenu" type="button" class="close" data-dismiss="popover" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <div>'
                >
        <a href="${pageContext.request.contextPath}/editMenu" class="btn btn-outline-secondary "><i class="fas fa-cogs" style="color:black"></i>
            Edit menu</a>
        </span>
    </footer>

    <jsp:include page="../bootstrapImportBody.jsp"/>

    <script src="../../../js/autocompleteAddress.js"></script>
    <script src="../../../js/utils.js"></script>
    <script src="../../../js/fo/foMenu.js"></script>
    <script src="../../../js/errorCatcher.js"></script>
    <script src="../../../js/popoverUtils.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDRtC9nTA8nx3D7jpH07HcU5SjpLhQgA6E&libraries=places"
            async defer></script>
    <%--<script src="../../../js/dg/anonymusDG.js"></script>--%>
</body>
</html>

