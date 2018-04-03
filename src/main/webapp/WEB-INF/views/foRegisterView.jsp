<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>FO Register View</title>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>
<br>
<div class="d-inline-flex ml-3">
    <form method="post">
        <div class="form-row">
            <div class="col-md-6 mb-3">
                <label>Franchise name</label>
                <input type="text" class="form-control" placeholder="Franchise name" name="name"
                       required>
            </div>
            <div class="col-md-6 mb-3">
                <label>Email</label>
                <input type="email" class="form-control" placeholder="Mail" name="email" required>
                <p class="text-danger" style="margin-bottom: -3rem;"></p>
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
                <p class="text-danger" style="margin-bottom: -3rem;"></p>
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
