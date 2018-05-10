var dgSocket = new WebSocket(getUrl('/dgOnline'));
var email;

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

//When the window is closed, the socket closes
window.onbeforeunload = closeSocket;
window.onunload = closeSocket;

function closeSocket(event) {
    dgSocket.close();
    //so the method doesn't execute twice
    window.onbeforeunload = undefined;
    window.onunload = undefined;
}

function newDeliveryGuy(deliveryGuy){
    //check if it is already on the list
    if(document.getElementById(deliveryGuy.email)) return;

    var dgTable = document.getElementById('dgTable');
    var row = document.createElement('tr');
    row.classList.add('dgs', 'text-center');


    var name = document.createElement('td');
    var state = document.createElement('td');
    var meansOfTransport = document.createElement('td');
    var setMeansOfTransport = document.createElement('td');
    var phone = document.createElement('td');
    var rating = document.createElement('td');

    name.innerHTML = deliveryGuy.name;
    state.innerHTML = "<td><i class=\"fa fa-circle\" aria-hidden=\"true\" style=\"color:green;\"></i></td>";
    meansOfTransport.hidden = true;
    meansOfTransport.value = deliveryGuy.meansOfTransport;
    setMeansOfTransport.id = "setMeansOfTransport";
    phone.innerHTML = deliveryGuy.phone;

    row.appendChild(name);
    row.appendChild(state);
    row.appendChild(setMeansOfTransport);
    row.appendChild(phone);
    row.appendChild(rating);

    row.addEventListener('mousedown', function () {
        $("#modalConfirmDG").modal();
        saveEmail(deliveryGuy.email);
    });

    row.id = deliveryGuy.email;
    dgTable.appendChild(row);
    replaceMeansOfTransport();
}

function deleteDeliveryGuy(deliveryGuy) {
    var dgRow = document.getElementById(deliveryGuy.email);
    //check if it is the list before removing
    if(dgRow) dgRow.parentNode.removeChild(dgRow);
}

function chooseDg(){
    var order = getOrder();
    var orderSocket = new WebSocket(getUrl('/orderSender/' + order.dgEmail));
    orderSocket.onopen = function (ev) {
        orderSocket.send(JSON.stringify(order));
        $("#modalConfirmDG").modal('hide');
        onWindowClose(orderSocket);
        waitingForResponse(orderSocket);
    };
}

function getOrder() {
    return {
        elaborationTime: document.getElementById('elaborationTime').value,
        stateOrder: 'WAITING',
        fromFO: true,
        dgEmail: email,
        totalPrice: document.getElementById('totalPrice').value,
        tippingPercentage: document.getElementById('tippingPercentage').value,
        clientEmail: document.getElementById('clientEmail').value
    };
}

function onWindowClose(orderSocket){
    //Close previous socket
    dgSocket.close();
    //Close order socket when window is closed
    window.onunload = window.onbeforeunload = orderSocketClose;
    function orderSocketClose(){
        orderSocket.close();
        window.onunload = window.onbeforeunload = undefined;
    }
}

function waitingForResponse(orderSocket){
    document.getElementById('dgTable').hidden = true;
    document.getElementById('waitingForResponse').hidden = false;

    orderSocket.onmessage = function (message) {
        console.log(message);
        //check if it is accepted or refused
        //add timeout
    }

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

function replaceMeansOfTransport() {
    var meansOfTransport = document.getElementById("meansOfTransport").value;
    var setTransport = document.getElementById("setMeansOfTransport");
    switch(meansOfTransport) {
        case '1':
            setTransport.innerHTML = '<i class=\"fa fa-bicycle\" aria-hidden=\"true\"></i>';
            break;
        case '2':
            setTransport.innerHTML = '<i class="fa fa-child" aria-hidden="true"></i>';
            break;
        case '3':
            setTransport.innerHTML = '<i class="fa fa-car" aria-hidden="true"></i>';
            break;
    }
}

function saveEmail(email) {
    this.email = email;
}

replaceMeansOfTransport();