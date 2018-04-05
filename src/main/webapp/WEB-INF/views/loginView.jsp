<%--suppress ALL --%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="bootstrapImportHeader.jsp"></jsp:include>
    <link rel="stylesheet" href="../../css/foober.css" type="text/css">
    <title>Login</title>
</head>
<body>

<jsp:include page="_menu.jsp"></jsp:include>


<div style="width: 30%;" class="container d-flex justify-content-center align-items-center flex-column alert-primary
                                        rounded mt-5">
    <img src="../../images/FooberLogo.png" class="img-fluid mt-3" style="width: 50%">
    <h3 class="mt-2">Foober</h3>

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
        <button type="submit" class="btn btn-primary" value="Submit">Submit</button>
    </form>
</div>

<br>
<p style="color:blue;">Login with:</p>

TEST@A/123 (FO) <br>
TEST2@A/123 (DG)

<jsp:include page="bootstrapImportBody.jsp"></jsp:include>

</body>
</html>
