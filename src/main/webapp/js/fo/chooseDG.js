var dgSocket = new WebSocket(getUrl('/dgOnline'));
dgSocket.onopen = function (ev) {
    dgSocket.onmessage = function (ev) {
        var deliveyGuy = JSON.parse(ev.data);
        if(deliveyGuy.state === 'ONLINE_WAITING'){
            newDeliveryGuy(deliveyGuy);
        }else{
            deleteDeliveryGuy(deliveyGuy);
        }
    };
};

window.addEventListener("beforeunload", function(e){
    dgSocket.close();
}, false);

function newDeliveryGuy(deliveryGuy){
    var dgTable = document.getElementById('dgTable');
    var row = document.createElement('tr');

    var name = document.createElement('td');
    var state = document.createElement('td');

    name.innerHTML = deliveryGuy.name;
    state.innerHTML = deliveryGuy.state;

    row.appendChild(name);
    row.appendChild(state);

    row.id = deliveryGuy.email;

    row.addEventListener('mousedown', function () {
        chooseDg(deliveryGuy.email);
    });
    dgTable.appendChild(row);
}

function deleteDeliveryGuy(deliveryGuy) {
    var dgRow = document.getElementById(deliveryGuy.email);
    dgRow.parentNode.removeChild(dgRow);
}

function chooseDg(dgMail){
    var orderSocket = new WebSocket(getUrl('/orderSender'));
    orderSocket.onopen = function (ev) {
        orderSocket.send(JSON.stringify({
            message: 'hi' + dgMail
        }));
        orderSocket.close();
    };
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