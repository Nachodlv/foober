var email = document.getElementById('deliveryGuy').childNodes[7].value;
var orderSocket = new WebSocket(getUrl('/orderSender/' + email));
var order;
login_logout(true);

orderSocket.onopen = function (ev) {
    orderSocket.onmessage = function (ev2) {
        console.log(ev2.data);
        order = JSON.parse(ev2.data);
        if(order.fromFO){
            showNotification(ev2);
            showOrder();
        }
    }
};

window.onbeforeunload = closeSocket;
window.onunload = closeSocket;

function closeSocket(event) {
    orderSocket.close();

    var request = new XMLHttpRequest();
    request.open("POST",window.location.href,false);
    request.setRequestHeader("content-type","application/x-www-form-urlencoded");
    request.send("state=offline");

    window.onbeforeunload = undefined;
    window.onunload = undefined;

    //changeStatus('ONLINE_WAITING');
}

function showOrder(){
    //show order info
    document.getElementById('spinner').hidden = true;
    document.getElementById('options').hidden = false;
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

function login_logout(first) {
    var online = document.getElementById("online");
    var offline = document.getElementById("offline");
    var state = document.getElementById("dgStatus").value;
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
    if(!first) changeStatus(state);
}

function changeStatus(oldState){
    var stateSocket = new WebSocket(getUrl('/dgOnline'));
    oldState = oldState==='OFFLINE'? 'ONLINE_WAITING': 'OFFLINE';
    stateSocket.onopen = function () {
        stateSocket.send(getDeliveryGuy(oldState));
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


