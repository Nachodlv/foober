<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>DG Register</title>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>
<br>
<div class="d-inline-flex ml-3">
    <form method="post" enctype="multipart/form-data">
        <div class="form-row">
            <div class="col-md-6 mb-3">
                <label>Name</label>
                <input type="text" class="form-control" placeholder="Name" name="name" value="${user.name}"
                       required>
            </div>
            <div class="col-md-6 mb-3">
                <label>Email</label>
                <input type="email" class="form-control" placeholder="Mail" name="email" value="${user.email}" required>
                <p class="text-danger" style="margin-bottom: -3rem;">${errorEmail}</p>
            </div>
        </div>
        <div class="form-row">
            <div class="col-md-6 mb-4">
                <label>Password</label>
                <input type="password" class="form-control" placeholder="Password" name="password"
                       value="${user.password}" required>
            </div>
            <div class="col-md-6 mb-4">
                <label>Confirm Password</label>
                <input type="password" class="form-control" placeholder="Confirm password" name="passwordRepeated"
                       value="${user.passwordRepeated}" required>
                <p class="text-danger" style="margin-bottom: -3rem;">${errorPassword}</p>
            </div>
        </div>
        <div class="form-row">
            <div class="col-md-6 mb-4">
                <label>Phone</label>
                <input type="text" pattern="[0-9]+" class="form-control" placeholder="Phone" name="phone"
                       value="${user.phone}" required>
            </div>
            <div class="col-md-6 mb-4 btn-group btn-group-toggle" data-toggle="buttons">
                <label>Means of Transport</label>
                <label class="btn btn-secondary active">
                    <input type="radio" name="meansOfTransport" id="option1" value ="1" autocomplete="off" checked> Bike
                </label>
                <label class="btn btn-secondary">
                    <input type="radio" name="meansOfTransport" id="option2" value ="2" autocomplete="off"> Bus
                </label>
                <label class="btn btn-secondary">
                    <input type="radio" name="meansOfTransport" id="option3" value ="3" autocomplete="off"> Car
                </label>
            </div>
        </div>

        <div>
            <label for="id">Scan ID</label>
            <br>
            <input type="file" id="id" name="id"
                   accept=".jpg, .jpeg, .png">
        </div>


        <div class="form-group">
            <div class="form-check">
                <input class="form-check-input is-invalid" type="checkbox" value="" id="invalidCheck3" required>
                <label class="form-check-label" for="invalidCheck3">
                    Agree to terms and conditions
                </label>
            </div>
        </div>
        <button type="submit" class="btn btn-primary" value="Submit">Submit</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

</body>
</html>