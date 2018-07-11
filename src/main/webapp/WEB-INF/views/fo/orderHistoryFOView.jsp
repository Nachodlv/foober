<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <jsp:include page="../bootstrapImportHeader.jsp"/>
    <link rel="stylesheet" href="../../../css/foober.css" type="text/css">
    <title>Order history</title>
</head>
<body class="outer">
<h1 align="left">Order history</h1>
<jsp:include page="../_header.jsp"/>
<input id="foID" hidden value=${loginedUser.email}>

<table id="table" class="table table-striped text-center">
    <thead>
    <tr>
        <th style="cursor: pointer" scope="col" onclick="sortTable(0)">#</th>
        <th style="cursor: pointer" scope="col" onclick="sortTable(1)">Client's name</th>
        <th style="cursor: pointer" scope="col" onclick="sortTable(2)">Delivery-guy's name</th>
        <th style="cursor: pointer" scope="col" onclick="sortTable(0)">Time issued</th>
        <th style="cursor: pointer" scope="col" onclick="sortTable(4)">Status</th>
        <th style="cursor: pointer" scope="col" onclick="sortTable(5)">Rating</th>
    </tr>
    </thead>
    <tbody id="tbody"></tbody>
</table>

<footer class="footer fixed-bottom mb-3 ml-3">
    <a href="${pageContext.request.contextPath}/foMenu" class="btn btn-outline-dark"><i class="fas fa-undo"
                                                                                        style="color:black"></i> Return
        to Main Menu</a>
</footer>

<script src="../../../js/starRating.js"></script>
<script src="../../../js/fo/orderHistoryFO.js"></script>
<script src="../../../js/history.js"></script>
<jsp:include page="../bootstrapImportBody.jsp"/>
</body>
</html>