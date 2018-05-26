var dgSocket = new WebSocket(getUrl('/dgOnline'));
var email;
var dgs;
getAllDGs();

dgSocket.onopen = function (ev) {
    dgSocket.onmessage = function (ev) {
        var deliveryGuy = JSON.parse(ev.data);
        if(deliveryGuy.state === 'ONLINE_WAITING'){
            newDeliveryGuy(deliveryGuy);
        }else{
            deleteDeliveryGuy(deliveryGuy);
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
    setRating(deliveryGuy.rating, deliveryGuy.ratingQuantity, rating);

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
        clientPhone: document.getElementById('clientPhone').value,
        clientAddress: document.getElementById('clientAddress').value,
        clientEmail: document.getElementById('clientEmail').value,
        foPhone: document.getElementById('foPhone').value
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
        orderSocket.close();
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
            errorCatcher('The delivery-guy rejected the order.');
        }
    }

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

function errorCatcher(error){
    var div = document.getElementById('orderRejected');
    div.innerHTML = '<div class="alert alert-danger alert-dismissible fade show" role="alert">\n' +
        error + '\n' +
        '<button type="button" class="close" data-dismiss="alert" aria-label="Close">\n' +
        '<span aria-hidden="true">&times;</span>\n' +
        '</button>\n' +
        '</div>';
}

function closeDgSocket(dgEmail){

}

function setRating(rating, total, div) {
    var html = '';
    if(total > 0) {
        var averageRating = rating / total;
        var absolutRating = Math.trunc(averageRating);
        var remaining = averageRating - absolutRating;
        for (var i = 0; i < absolutRating; i++) {
            html += '<i class=\"fas fa-star\"></i>';
        }
        if (remaining >= 0.75) html += '<i class=\"fas fa-star\"></i>';
        else if (remaining >= 0.25) html += '<i class=\"fas fa-star-half\"></i>';
    } else {
        html = 'no ratings yet'
    }
    div.innerHTML = html;
}

function getAllDGs() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            dgs = JSON.parse(this.responseText);
            setDgs();
            replaceMeansOfTransport();
        }
    };
    var url = window.location.href.split('/');
    url[3] = 'getDGs';
    var urlFinal = url.join('/');
    xhttp.open("GET", urlFinal , true);
    xhttp.send();
}

function setDgs() {
    for (var i = 0; i < dgs.length; i++) {
        newDeliveryGuy(dgs[i]);
    }
}