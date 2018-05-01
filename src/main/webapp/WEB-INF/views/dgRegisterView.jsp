<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="bootstrapImportHeader.jsp"></jsp:include>
    <link rel="stylesheet" href="../../css/registerView.css" type="text/css">
    <title>DG Register</title>
</head>
<body>
<br>
<div class="container h-100 d-flex justify-content-center">
    <div class="jumbotron vertical-center ">
    <form method="post" enctype="multipart/form-data">
        <div id="legend">
            <h3 class="">Register Delivery Guy</h3>
        </div>
        <div class="form-row">
            <div class="col-md-6 mb-3">
                <label>Name</label>
                <input type="text" class="form-control" placeholder="Name" name="name" required>
            </div>
            <div class="col-md-6 mb-3">
                <label>Email</label>
                <input type="email" class="form-control" placeholder="Mail" name="mail" required>
                <p class="text-danger" style="margin-bottom: -3rem;">${errorEmail}</p>
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
                <p class="text-danger" style="margin-bottom: -3rem;">${errorPassword}</p>
            </div>
        </div>
        <div class="form-row">
            <div class="col-md-6 mb-4">
                <label>Phone</label>
                <input type="text" pattern="[0-9]+" class="form-control" placeholder="Phone" name="phone" required>
            </div>
            <div class="col-md-6 mb-4">
                <div class="row">
                    <div class = "col">
                        <label>Transport</label>
                    </div>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="customRadioInline1" name="meansOfTransport" class="custom-control-input" value="1">
                    <label class="custom-control-label" for="customRadioInline1">Bike</label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="customRadioInline2" name="meansOfTransport" class="custom-control-input" value="2">
                    <label class="custom-control-label" for="customRadioInline2">Walk</label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input type="radio" id="customRadioInline3" name="meansOfTransport" class="custom-control-input" value="3">
                    <label class="custom-control-label" for="customRadioInline3">Car</label>
                </div>
            </div>
        </div>

        <div>
            <label for="id">Scan ID</label>
            <br>
            <input type="file" id="id" name="id" accept=".jpg, .jpeg, .png" class="file">
        </div>


        <div class="form-group">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="invalidCheck3" required>
                <label class="form-check-label" for="invalidCheck3">
                    <b>Agree to terms and conditions</b>
                </label>
            </div>
        </div>
        <p style="color: red;" class="text-danger">${nullImg}</p>
        <button type="submit" class="btn btn-primary" value="Submit">Submit</button>
    </form>
</div>
</div>

<jsp:include page="bootstrapImportBody.jsp"></jsp:include>

</body>
</html>