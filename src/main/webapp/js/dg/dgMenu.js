var online = document.getElementById("online");
var offline = document.getElementById("offline");

function login_logout(first) {

    switch (document.getElementById("dgStatus").value) {
        case 'ONLINE_WAITING':
            online.disabled = true;
            offline.disabled = false;
            break;
        case 'OFFLINE':
            var socket = new WebSocket("ws://localhost:8080/dgOnline");
            offline.disabled = true;
            online.disabled = false;
            socket.onopen = function () {
                socket.send(getDeliveryGuy());
                socket.close();
            };
            break;
    }
}

login_logout(true);

function getDeliveryGuy(){
    var div = document.getElementById('deliveryGuy').childNodes;
    var dg =  {
        name: div[1].value,
        phone: div[3].value,
        meansOfTransport: div[5].value,
        state: div[7].value
    };

    return JSON.stringify(dg);

}
