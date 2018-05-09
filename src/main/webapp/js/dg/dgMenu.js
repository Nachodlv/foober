var orderSocket = new WebSocket(getUrl('/orderSender'));
var order;
login_logout(true);

orderSocket.onopen = function (ev) {
    orderSocket.onmessage = function (ev2) {
        console.log(ev2.data);
        order = JSON.parse(ev2.data);
        if(order.fromFO){
            document.getElementById('spinner').hidden = true;
            document.getElementById('options').hidden = false;
            showNotification(ev2);
        }
    }
};

window.onbeforeunload = closeSocket;

function closeSocket(event) {
    orderSocket.close();
    return null;
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
        email: div[7].value,
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

