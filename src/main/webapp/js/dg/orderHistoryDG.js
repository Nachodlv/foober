var orders;
getOrders();

function getOrders() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            orders = JSON.parse(this.responseText);
            generateTable();
        }
    };
    var url = window.location.href.split('/');
    url[3] = 'allOrdersDG?dgID=' + document.getElementById('dgID').value;
    xhttp.open("GET", url.join('/'), true);
    xhttp.send();
}

function generateTable() {
    var tableBody = document.getElementById("tbody");
    for (var i = 0; i < orders.length; i++) {
        var order = orders[i];
        var row = document.createElement('tr');
        var orderID = document.createElement('td');
        var clientName = document.createElement('td');
        var foName = document.createElement('td');
        var issuedTime = document.createElement('td');
        var status = document.createElement('td');

        orderID.innerHTML = order.id;
        clientName.innerHTML = order.client.name;
        foName.innerHTML = order.franchiseOwner.name;
        issuedTime.textContent = getTime(order.issuedTime);
        status.innerHTML = order.stateOrder;

        row.appendChild(orderID);
        row.appendChild(clientName);
        row.appendChild(foName);
        row.appendChild(issuedTime);
        row.appendChild(status);
        tableBody.appendChild(row);
    }
}