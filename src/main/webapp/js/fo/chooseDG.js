var dgSocket = new WebSocket(getUrl('/dgOnline'));
var email;
replaceMeansOfTransport();

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
    var setMeansOfTransport = document.createElement('td');
    var phone = document.createElement('td');
    var rating = document.createElement('td');

    name.innerHTML = deliveryGuy.name;
    state.innerHTML = "<td><i class=\"fa fa-circle\" aria-hidden=\"true\" style=\"color:green;\"></i></td>";
    setMeansOfTransport.className = "setMeansOfTransport";
    setMeansOfTransport.innerHTML = deliveryGuy.meansOfTransport;
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
        id: document.getElementById('id').value,
        foName: document.getElementById('foName').value,
        elaborationTime: document.getElementById('elaborationTime').value,
        stateOrder: 'WAITING',
        fromFO: true,
        dgEmail: email,
        totalPrice: document.getElementById('totalPrice').value,
        tippingPercentage: document.getElementById('tippingPercentage').value,
        clientPhone: document.getElementById('clientPhone').value
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
    document.getElementById('table').hidden = true;
    document.getElementById('waitingForResponse').hidden = false;

    orderSocket.onmessage = function (message) {
        var order = JSON.parse(message.data);
        if(order.fromFO) return;
        if(order.stateOrder === 'DELIVERING'){
            //order accepted
            saveOrder(order.dgEmail);
            var newHref = window.location.href.split('/');
            newHref[3] = 'foMenu?orderAccepted=true';
            window.location.href = newHref.join('/');
        }else{
            //order rejected
            document.getElementById('table').hidden = false;
            document.getElementById('waitingForResponse').hidden = true;
            document.getElementById('orderRejected').hidden = false;
        }
        //add timeout
        orderSocket.close();
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
    var setTransport = document.getElementsByClassName("setMeansOfTransport");
    for(var i = 0; i < setTransport.length; i++) {
        switch(setTransport[i].innerHTML) {
            case '1':
                setTransport[i].innerHTML = '<i class=\"fa fa-bicycle\" aria-hidden=\"true\"></i>';
                break;
            case '2':
                setTransport[i].innerHTML = '<i class="fa fa-child" aria-hidden=\"true\"></i>';
                break;
            case '3':
                setTransport[i].innerHTML = '<i class="fa fa-car" aria-hidden=\"true\"></i>';
                break;
        }
    }
}

function saveEmail(email) {
    this.email = email;
}

function saveOrder(dgEmail){
    var xhttp = new XMLHttpRequest();
    var url = window.location.href += '?dgEmail=' + dgEmail;
    xhttp.open("POST", url, true);
    xhttp.send("dgEmail=" + dgEmail);
}