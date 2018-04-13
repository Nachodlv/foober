<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="bootstrapImportHeader.jsp"></jsp:include>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../css/foMenuView.css" type="text/css">
    <link rel="stylesheet" href="../../css/foober.css" type="text/css">
    <title>FO Menu</title>
</head>

<body>

<jsp:include page="_menu.jsp"></jsp:include>

<h3 class="ml-2">Franchise Owner Menu</h3>

<%--Orders container--%>
<div class="row container mt-3">
<div class="container col-6">
    <form method="post" action="${pageContext.request.contextPath}/foMenu" >
        <div class="form-inline row mb-2">
            <input type="text" class="form-control col-5 ml-2" placeholder="Search client">
            <button type="button" class="btn btn-info col-3 ml-2">Search</button>
            <button type="button" class="btn btn-primary col-3 ml-2">New client</button>
        </div>
        <table class="table table-striped table-hover table-bordered">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Phone</th>
                <th scope="col">Address</th>
                <th scope="col">Email</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${clients}"  var="client">
                <tr>
                    <button class="buttonWithFunction" type="submit" name = "aa">
                        <td>${client.name}</td>
                        <td>${client.phone} $</td>
                        <td>${client.address} $</td>
                        <td>${client.email} $</td>
                    </button>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>

<%--Currnet orders--%>
<div class="container col-6"></div>
</div>
<jsp:include page="bootstrapImportBody.jsp"></jsp:include>
</body>
</html>

