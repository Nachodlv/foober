var email = document.getElementById('deliveryGuy').childNodes[7].value;
var orderSocket = new WebSocket(getUrl('/orderSender/' + email));
var order;
var waitingResponse = false;
var delivering = false;
onlineWorking();

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
    if(waitingResponse) refuseOrder();

    orderSocket.close();

    if(!delivering) changeState('OFFLINE');

    window.onbeforeunload = undefined;
    window.onunload = undefined;
}

function showOrder(order){
    //show order info
    waitingResponse = true;
    document.getElementById('spinner').hidden = true;
    document.getElementById('options').hidden = false;
    $('#foName').html(order.foName);
    $('#elaborationTime').html(order.elaborationTime + ' minutes');
    $('#clientPhone').html(order.clientPhone);
    $('#totalPrice').html(order.totalPrice + '$');
    $('#tip').html(getTip(order.tippingPercentage, order.totalPrice) + '$');
    $("#options").modal({
        backdrop: false
    });
}

function hideOrder(){
    $("#options").modal('hide');
    waitingResponse = false;
}

function acceptOrder(){
    order.stateOrder = 'DELIVERING';
    order.fromFO = false;
    orderSocket.send(JSON.stringify(order));
    delivering = true;
    changeState('ONLINE_WORKING');
    onlineWorking(true);
    hideOrder();
}

function refuseOrder(){
    order.fromFO = false;
    orderSocket.send(JSON.stringify(order));
    hideOrder();
}

function login_logout(state) {
    var online = document.getElementById("online");
    var offline = document.getElementById("offline");
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
    changeState(state);
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

function changeState(state) {
    var xhttp = new XMLHttpRequest();
    document.getElementById("dgState").value = state;
    var newHref = window.location.href.split('/');
    newHref[3] = 'dgMenu?state=' + state;
    xhttp.open("POST", newHref.join('/'), true);
    xhttp.send("state=" + state);
    sendState(state);
}

function getTip(tippingPercentage, totalCost) {
    return (totalCost * tippingPercentage)/100;
}

function finishDelivering(){
    document.getElementById('finishDelivering').hidden = true;
    document.getElementById('offline').disabled = false;
    document.getElementById('spinner').hidden = false;

    changeState('ONLINE_WAITING');
}

function onlineWorking(startWorking){
    var state = document.getElementById('dgState').value;
    if(state === 'ONLINE_WORKING' || startWorking) {
        //if order is undefined, getOrder (if the page reloads)
        if(!order) getOrder();

        document.getElementById('finishDelivering').hidden = false;
        document.getElementById('offline').disabled = true;
    }
}

function getOrder(){
    //get order AJAX
}

