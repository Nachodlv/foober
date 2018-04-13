<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="bootstrapImportHeader.jsp"></jsp:include>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../../css/foMenuView.css" type="text/css">
    <title>FO Menu</title>
</head>

<body>

<jsp:include page="_menu.jsp"></jsp:include>

<h3>Franchise Owner Menu</h3>

<form method="post" action="${pageContext.request.contextPath}/foMenu" >
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6">
                <div class="inner">
                    Current orders
                </div>
            </div>
            <div class="col-md-6">
                <div class="inner">
                    <table class="table table-bordered text-center">
                        <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Phone</th>
                            <th scope="col">Address</th>
                            <th scope="col">Email</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${clients}" var="client">
                                <tr>
                                    <td>${client.name}</td>
                                    <td>${client.phone} $</td>
                                    <td>${client.address} $</td>
                                    <td>${client.email} $</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>
<jsp:include page="bootstrapImportBody.jsp"></jsp:include>
</body>
</html>

