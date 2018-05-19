var orders;
var openWS = [];
getOrders();
showOrderAcceptedModal();

window.onbeforeunload = closeSockets;
window.onunload = closeSockets;

function minutesAgo() {
    var timeElapsed = document.getElementsByClassName("timeElapsed");
    var issuedTimes = document.getElementsByClassName("issuedTime");

    for(var i = 0; i < issuedTimes.length; i++) {
        var issuedTime = Number(issuedTimes[i].value);
        var time = new Date();
        var timeMillis = time.getTime();
        var timeDiff = timeMillis - issuedTime;
        // strip the ms
        timeDiff /= 1000;
        // get seconds
        var seconds = Math.round(timeDiff);
        var minutes = seconds/60;
        timeElapsed[i].innerHTML = (" " + parseFloat(minutes).toFixed(0) + " minutes ago");
        setInterval(function() { minutesAgo()}, 60000);
    }
}

function getOrders() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            orders = JSON.parse(this.responseText);
            setOrders(orders);
            minutesAgo();
        }
    };
    var url = window.location.href.split('/');
    url[3] = 'orders?foID=' + document.getElementById('foID').value;
    xhttp.open("GET", url.join('/') , true);
    xhttp.send();
}

function openWebSocket(dgEmail) {
    var orderSocket = new WebSocket(getUrl('/orderSender/' + dgEmail));
    openWS.push(orderSocket);
    orderSocket.onmessage = function (ev) {
        var order = JSON.parse(ev.data);
        var column = document.getElementById(order.id);
        column.replaceChild(getOrderStatus(order.stateOrder), column.childNodes[9])
    };
}

function setOrders(orders) {
    var panel = document.getElementById("panel1");
    for (var i = 0; i < orders.length; i++) {
        var order = orders[i];
        var column = document.createElement('div');
        column.className = 'col-md-4 text-center';
        column.style = 'margin-bottom: 1rem;';
        column.id = orders[i].id;
        addLink(column, order);

        var img = document.createElement('img');
        img.className = 'card-img-top rounded-circle';
        img.align = 'center';
        img.style = 'height: 8rem; width:8rem;';

        var title = document.createElement('h5');
        title.className = 'card-title';


        if (order.deliveryGuy === undefined) {
            img.src = '../../images/anonymus.png';
            title.innerHTML = 'not assigned';
        } else {
            var url = window.location.href.split('/');
            url[3] = 'images/' + order.deliveryGuy.email + '.png';
            img.src = url.join('/');
            title.innerHTML = order.deliveryGuy.name;
        }

        var issuedTime = document.createElement('input');
        issuedTime.className = 'issuedTime';
        issuedTime.hidden = true;
        issuedTime.value = order.issuedTime;

        var timeIcon = document.createElement('i');
        timeIcon.className = 'far fa-clock';

        var time = document.createElement('p');
        time.className = 'card-text timeElapsed';

        var clientIcon = document.createElement('i');
        clientIcon.className = 'far fa-user';

        var client = document.createElement('p');
        client.className = 'card-text';
        client.innerHTML = order.client.email;

        var status = getOrderStatus(order.stateOrder);
        if(order.stateOrder === 'DELIVERING') openWebSocket(order.deliveryGuy.email);

        column.appendChild(img);
        column.appendChild(title);
        column.appendChild(issuedTime);
        column.appendChild(timeIcon);
        column.appendChild(time);
        column.appendChild(clientIcon);
        column.appendChild(client);
        column.appendChild(status);
        panel.appendChild(column);
    }
}


function showOrderAcceptedModal(){
    var orderAccepted = document.getElementById('orderAccepted').value;
    if(orderAccepted !== '') $('#orderAcceptedModal').modal();
}

//change color depending on the status
function getOrderStatus(status) {
    var stToUpper = status.toUpperCase();
    var statusElement =  document.createElement('p');
    statusElement.innerHTML = stToUpper;

    switch (stToUpper) {
        case 'WAITING':
            statusElement.style = 'color: red;';
            break;
        case 'DELIVERING':
            statusElement.style = 'color: #D35400;';
            break;
        case 'DELIVERED':
            statusElement.style = 'color: navy;';
            break;
        case 'REVIEWED':
            statusElement.style = 'color: green;';
            break;
        default: break;
    }
    return statusElement;
}

function getUrl(url){
    var loc = window.location, new_uri;
    if (loc.protocol === "https:") {
        new_uri = "wss:";
    } else {
        new_uri = "ws:";
    }
    new_uri += "//" + loc.host;
    new_uri += url;

    return new_uri;
}

function closeSockets(){
    for(var i=0; i<openWS.length; i++){
        openWS[i].close();
    }

    window.onbeforeunload = undefined;
    window.onunload = undefined;
}

function addLink(column, order){
    column.onclick = function() {
        window.open("http://localhost:8080/orderInfo?orderID=" + order.id, "_self")
    };
}
