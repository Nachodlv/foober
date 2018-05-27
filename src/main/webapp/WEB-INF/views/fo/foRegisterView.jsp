<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="../bootstrapImportHeader.jsp"/>
    <link rel="stylesheet" href="../../../css/registerView.css" type="text/css">
    <link rel="stylesheet" href="../../../css/foober.css" type="text/css">
    <title>FO Register View</title>
</head>
<body>
<br>
<div class="outer">
<div class="container h-100 d-flex justify-content-center">
    <div class="jumbotron vertical-center">
        <form method="post">
            <div id="legend">
                <h3 class="">Register Franchise Owner</h3>
            </div>
            <div class="form-row">
                <div class="col-md-6 mb-3">
                    <label>Franchise name</label>
                    <input type="text" class="form-control" placeholder="Franchise name" name="name"
                           required>
                </div>
                <div class="col-md-6 mb-3">
                    <label>Email</label>
                    <input type="email" class="form-control" placeholder="Mail" name="email" required>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-6 mb-4">
                    <label>Password</label>
                    <input type="password" class="form-control" placeholder="Password" name="password" required>
                </div>
                <div class="col-md-6 mb-4">
                    <label>Confirm Password</label>
                    <input type="password" class="form-control" placeholder="Confirm password" name="passwordRepeated" required>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-6 mb-4">
                    <label>Phone</label>
                    <input type="text" pattern="[0-9]+" class="form-control" placeholder="Phone" name="phone"
                           value="" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label>Address</label>
                    <input type="text" class="form-control" placeholder="Address" name="address" value=""
                           required>
                </div>
            </div>
            <div class="form-group">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="invalidCheck3" required>
                    <label class="form-check-label" for="invalidCheck3">
                        <b>Agree to terms and conditions</b>
                    </label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary" value="Submit" onclick="firstTime()">Submit</button>
            <div class="errorCatcher mt-2" style="max-width: 26rem;"></div>
        </form>
    </div>
</div>
</div>

<script src="../../../js/errorCatcher.js"></script>
<script src="../../../js/fo/foRegister.js"></script>
<jsp:include page="../bootstrapImportBody.jsp"/>

</body>
</html>
