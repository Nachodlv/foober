<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>FO Menu</title>
</head>

<body>

<jsp:include page="_menu.jsp"></jsp:include>

<h3>Franchise Owner Menu</h3>

<form method="post" action="${pageContext.request.contextPath}/foMenu" >
    <table class="table table-striped">
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
                    <td>${client.name}</td>
                    <td>${client.phone} $</td>
                    <td>${client.address} $</td>
                    <td>${client.email} $</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</form>
</body>
</html>

