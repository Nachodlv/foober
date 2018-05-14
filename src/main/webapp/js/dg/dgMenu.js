var email = document.getElementById('deliveryGuy').childNodes[7].value;
var orderSocket = new WebSocket(getUrl('/orderSender/' + email));
var order;

orderSocket.onopen = function (ev) {
    orderSocket.onmessage = function (ev2) {
        console.log(ev2.data);
        order = JSON.parse(ev2.data);
        if(order.fromFO){
            showNotification(ev2);
            showOrder(order);
        }
    }
};

window.onbeforeunload = closeSocket;
window.onunload = closeSocket;

function closeSocket(event) {
    orderSocket.close();

    window.onbeforeunload = undefined;
    window.onunload = undefined;

    changeStatus('OFFLINE');
}

function showOrder(order){
    //show order info
    document.getElementById('spinner').hidden = true;
    document.getElementById('options').hidden = false;
    $('#elaborationTime').html(order.elaborationTime + ' minutes');
    $('#clientEmail').html(order.clientEmail);
    $('#totalPrice').html(order.totalPrice + '$');
    $('#tip').html(getTip(order.tippingPercentage, order.totalPrice) + '$');
    $("#options").modal();
}

function hideOrder(){
    //hideOrder
}

function acceptOrder(){
    order.stateOrder = 'DELIVERING';
    orderSocket.send(order);
    hideOrder();
}

function refuseOrder(){
    orderSocket.send(order);
    hideOrder();
}

function login_logout(state) {
    var online = document.getElementById("online");
    var offline = document.getElementById("offline");
    //var state = document.getElementById("dgStatus").value;
    switch (state) {
        case 'ONLINE_WAITING':
            online.disabled = true;
            offline.disabled = false;
            break;
        case 'OFFLINE':
            offline.disabled = true;
            online.disabled = false;
            break;
    }
    //var newState = state==='OFFLINE'? 'ONLINE_WAITING': 'OFFLINE';
    changeStatus(state);
}

function sendState(state){
    var stateSocket = new WebSocket(getUrl('/dgOnline'));
    stateSocket.onopen = function () {
        stateSocket.send(getDeliveryGuy(state));
        stateSocket.close();
    };
}

function getDeliveryGuy(state){
    var div = document.getElementById('deliveryGuy').childNodes;

    var dg =  {
        name: div[1].value,
        phone: div[3].value,
        meansOfTransport: div[5].value,
        email: email,
        state: state
    };

    return JSON.stringify(dg);
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

function changeStatus(state) {
    var xhttp = new XMLHttpRequest();
    document.getElementById("dgStatus").value = state;
    xhttp.open("POST", window.location.href, true);
    xhttp.send("state=" + state);
    sendState(state);
}

function getTip(tippingPercentage, totalCost) {
    return (totalCost * tippingPercentage)/100;
}

