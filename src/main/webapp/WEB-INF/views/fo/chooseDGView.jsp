<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <jsp:include page="../bootstrapImportHeader.jsp"></jsp:include>
    <title>Choose delivery-guy</title>
    <link rel="stylesheet" href="../../../css/foober.css" type="text/css">
</head>
<body class="outer" onbeforeunload="closeSocket()">
<form method="post">
    <button class="buttonWithFunction" type="submit" name="mail" value="mail">MAIL</button>
</form>
<table>
    <thead>
    <tr>
        <th class="text-center" scope="col">Name</th>
        <th class="text-center" scope="col">Status</th>
    </tr>
    </thead>
    <tbody id="dgTable">
        <c:forEach items="${deliveryGuys}"  var="deliveryGuy">
            <tr id="${deliveryGuy.email}" onclick="chooseDg('${deliveryGuy.email}')">
                <td>${deliveryGuy.name}</td>
                <td>${deliveryGuy.state}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<script src="../../../js/fo/chooseDG.js"></script>
<jsp:include page="../bootstrapImportBody.jsp"></jsp:include>
</body>
</html>
