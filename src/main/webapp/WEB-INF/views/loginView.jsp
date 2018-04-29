<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="bootstrapImportHeader.jsp"></jsp:include>
    <link rel="stylesheet" href="../../css/foober.css" type="text/css">
    <title>Login</title>
</head>
<body>

<div style="width: 30%;" class="container d-flex justify-content-center align-items-center flex-column alert-secondary
                                        rounded mt-5">
    <img src="../../images/FooberLogo.png" class="img-fluid mt-3" style="width: 70%">
    <h3 class="mt-2">Welcome to Foober!</h3>


    <p style="color: red;" class="text-danger">${errorMessage}</p>

    <form method="POST" action="${pageContext.request.contextPath}/login" class="text-left mb-3">
        <input type="hidden" name="redirectId" value="${param.redirectId}"/>
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                   placeholder="Enter email" name="email" value="${user.email}">
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"
                   name="password" value="${user.password}">
        </div>
        <div class="row">
            <div class="col-md-4" align="center"><button type="submit" class="btn btn-dark" value="Submit">Submit</button></div>
            <div class="col-md-4" align="center">
                <a style= "font-size: 11px" class="registerLink" href="${pageContext.request.contextPath}/dgRegister">Register<br> Delivery Guy</a>
            </div>
            <div class="col-md-4" align="center"><a class="registerLink" style= "font-size: 11px" href="${pageContext.request.contextPath}/foRegister">Register Franchise</a></div>
        </div>
    </form>
</div>

<br>
<jsp:include page="bootstrapImportBody.jsp"></jsp:include>

</body>
</html>
