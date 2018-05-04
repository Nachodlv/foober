<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="../bootstrapImportHeader.jsp"/>
    <link rel="stylesheet" href="../../../css/fo/foInfoView.css" type="text/css">
    <link rel="stylesheet" href="../../../css/foober.css" type="text/css">
    <title>User Info</title>
</head>
<body class="outer">

<h3 class="ml-3">Profile information</h3>
<p class="text-danger">
    ${pageContext.request.getParameter("error")}
</p>
<form method="POST" class="mt-5">
    <div class="container-fluid d-flex flex-column">
        <table class="table">
            <thead>
            </thead>
            <tbody>
            <tr>
                <td>Franchise name</td>
                <td><input value="${loginedUser.name}" class="form-control" placeholder="Franchise owner" name="name" required></td>
                <td> </td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input class="passwordInput" type="password" value="${loginedUser.password}" placeholder="Password" readonly></td>
                <!-- Button trigger modal -->
                <td><button type="button" class="btn btn-link" data-toggle="modal" data-target="#exampleModal">Edit</button></td>
            </tr>
            <tr>
                <td>Address</td>
                <td><input value="${loginedUser.address}" class="form-control" placeholder="Address" name="address" required></td>
                <td> </td>
            </tr>
            <tr>
                <td>Tipping percentage</td>
                <td><input value="${loginedUser.tippingPercentage}" class="form-control" pattern="[0-9]+" placeholder="Tipping percentage" name="tippingPercentage" required> </td>
                <td> </td>
            </tr>
            <tr>
                <td>Phone</td>
                <td><input value="${loginedUser.phone}" class="form-control" pattern="[0-9]+" placeholder="Phone" name="phone" required> </td>
                <td> </td>
            </tr>
            </tbody>
        </table>
        <div class="row ml-auto">
            <button type="submit" class="btn btn-danger mr-2" name="change" value="cancel">Cancel changes</button>
            <button type="submit" class="btn btn-info mr-2" name="change" value="save">Save changes</button>
        </div>
    </div>
</form>

<%--Modal--%>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <form method="POST" class="mt-5">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Change password</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="align-items-center">
                        <div class="">
                            <label for="oldPasswordInput">Current password</label>
                            <input type="password" class="form-control mb-2" id="oldPasswordInput" placeholder="Current password" name="oldPassword" required>
                        </div>
                        <div class="">
                            <label for="newPasswordInput">New password</label>
                            <input type="password" class="form-control mb-2" id="newPasswordInput" placeholder="New password" name="newPassword"  required>
                        </div>
                        <div class="">
                            <label for="repeatPasswordInput">Repeat password</label>
                            <input type="password" class="form-control mb-2" id="repeatPasswordInput" placeholder="Repeat password" name="repeatPassword"  required>
                        </div>
                        <div class="mt-3">
                            <button type="submit" class="btn btn-primary mb-2" value="submit" name="changePassword">Submit</button>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" value="Submit">Close</button>
                </div>
            </div>
        </div>
    </form>
</div>

<jsp:include page="../bootstrapImportBody.jsp"/>
</body>
</html>