<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th class="text-center" scope="col">Name</th>
        <th class="text-center" scope="col">Status</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${deliveryGuys}"  var="deliveryGuy">
            <tr>
                <td>${deliveryGuy.name}</td>
                <td>${deliveryGuy.state}</td>
            </tr>
        </c:forEach>
    </tbody>
    <script src="../../../js/fo/chooseDG.js"></script>
</table>
</body>
</html>
