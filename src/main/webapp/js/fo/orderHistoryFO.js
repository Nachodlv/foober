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
    url[3] = 'allOrdersFO?foID=' + document.getElementById('foID').value;
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
        var dgName = document.createElement('td');
        var issuedTime = document.createElement('td');
        var status = document.createElement('td');
        var rating = document.createElement('td');

        orderID.innerHTML = order.id;
        clientName.innerHTML = order.client.name;
        if (order.deliveryGuy) {
            dgName.innerHTML = order.deliveryGuy.name;
        } else {
            dgName.innerHTML = ('no DG assigned yet').italics()
        }

        issuedTime.innerHTML = getTime(order.issuedTime);
        status.innerHTML = order.stateOrder;

        setRating(order.ratingDG, 1, rating, 1);

        row.appendChild(orderID);
        row.appendChild(clientName);
        row.appendChild(dgName);
        row.appendChild(issuedTime);
        row.appendChild(status);
        row.appendChild(rating);
        tableBody.appendChild(row);
    }
}