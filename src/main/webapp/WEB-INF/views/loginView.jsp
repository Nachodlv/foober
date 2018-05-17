<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="bootstrapImportHeader.jsp"/>
    <link rel="stylesheet" href="../../css/foober.css" type="text/css">
    <title>Login</title>
</head>
<body class="outer">

<div style="padding:0 1rem 0.1rem 1rem;" class="container align-self-center col-lg-3 col-md-4 col-sm-6 col-12 alert-secondary rounded text-center">
    <img src="../../images/FooberLogo.png" class="img-fluid" style="padding: 1.5rem;margin-bottom: -1rem;">
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
            <div class="col-md-4 col-4" align="center"><button type="submit" class="btn btn-dark btn-responsive" value="Submit">Submit</button></div>
            <div class="col-md-4 col-4" align="center" style="line-height: normal;">
                <a style= "font-size: 0.7rem" class="registerLink" href="${pageContext.request.contextPath}/dgRegister">Register<br>Delivery Guy</a>
            </div>
            <div class="col-md-4 col-4" align="center" style="line-height: normal;"><a class="registerLink" style= "font-size: 0.7rem" href="${pageContext.request.contextPath}/foRegister">Register<br>Franchise</a></div>
        </div>
    </form>
</div>

<br>
<jsp:include page="bootstrapImportBody.jsp"/>

</body>
</html>
