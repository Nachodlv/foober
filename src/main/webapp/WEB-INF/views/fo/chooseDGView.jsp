<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <jsp:include page="../bootstrapImportHeader.jsp"/>
    <title>Choose delivery-guy</title>
    <link rel="stylesheet" href="../../../css/foober.css" type="text/css">
</head>
<body class="outer" onbeforeunload="closeSocket()">
<table class="table table-striped table-hover table-bordered">
    <thead>
    <tr>
        <th class="text-center" scope="col">Name</th>
        <th class="text-center" scope="col">Status</th>
        <th class="text-center" scope="col">Means Of Transport</th>
        <th class="text-center" scope="col">Phone</th>
        <th class="text-center" scope="col">Rating</th>
    </tr>
    </thead>
    <tbody id="dgTable">
        <c:forEach items="${deliveryGuys}"  var="deliveryGuy">
            <tr class="text-center dgs" id="${deliveryGuy.email}">
                <td>${deliveryGuy.name}</td>
                <td><i class="fa fa-circle" aria-hidden="true" style="color:green;"></i></td>
                <input id="meansOfTransport" hidden value="${deliveryGuy.meansOfTransport}">
                <td id="setMeansOfTransport"></td>
                <td>${deliveryGuy.phone}</td>
                <td><i class="fa fa-star" aria-hidden="true"></i><i class="fa fa-star" aria-hidden="true"></i><i class="fa fa-star" aria-hidden="true"></i><i class="fa fa-star" aria-hidden="true"></i></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%--modal for confirming--%>
<div class="modal fade bd-example-modal-sm" id="modalConfirmDG" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content" style="width: 120%">
            <form method="post">
                <div class="modal-header">
                    <h5 class="modal-title">Confirm DG selection</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <h4 style="font-size: 1rem">Are you sure you want to choose this Delivery Guy?</h4>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="button" onclick="chooseDg()" class="btn btn-primary">Confirm</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="../../../js/fo/chooseDG.js"></script>
<jsp:include page="../bootstrapImportBody.jsp"/>
<script>
    $(".dgs").click(function (event) {
        console.log(event);
        var email = event.currentTarget.id;
        saveEmail(email);
        $("#modalConfirmDG").modal();
    })
</script>

</body>
</html>
