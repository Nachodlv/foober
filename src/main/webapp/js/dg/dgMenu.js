var email = document.getElementById('deliveryGuy').childNodes[7].value;
var orderSocket = new WebSocket(getUrl('/orderSender/' + email));
var order;
var waitingResponse = false;
var timeOutQuantity = 0;
var delivering = false;
var timeOut;
showRating();

orderSocket.onopen = function (ev) {
    onlineWorking();
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
    $('#clientAddress').html(order.clientAddress);
    $('#clientPhone').html(order.clientPhone);
    $('#totalPrice').html(order.totalPrice + '$');
    $('#tip').html(getTip(order.tippingPercentage, order.totalPrice) + '$');
    $("#options").modal({
        backdrop: false
    });
    timeOut = setTimeout(function(){
        timeOutQuantity ++;
        refuseOrder();
        if(timeOutQuantity >= 3){
            forceLogOut();
            timeOutQuantity = 0;
        }
    }, 60000);
}

function hideOrder(){
    $("#options").modal('hide');
    waitingResponse = false;
}

function acceptOrder(){
    clearTimeout(timeOut);
    order.stateOrder = 'DELIVERING';
    order.fromFO = false;
    orderSocket.send(JSON.stringify(order));
    delivering = true;
    changeState('ONLINE_WORKING');
    onlineWorking(true);
    hideOrder();
}

function refuseOrder(){
    clearTimeout(timeOut);
    order.fromFO = false;
    orderSocket.send(JSON.stringify(order));
    hideOrder();
}

function login_logout(state) {
    var online = document.getElementById("online");
    var offline = document.getElementById("offline");
    var spinner = document.getElementById('spinner');
    switch (state) {
        case 'ONLINE_WAITING':
            online.disabled = true;
            offline.disabled = false;
            spinner.hidden = false;
            break;
        case 'OFFLINE':
            offline.disabled = true;
            online.disabled = false;
            spinner.hidden = true;
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
        rating: document.getElementById("rating").value,
        ratingQuantity: document.getElementById("ratingQuantity").value,
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
    hideOrderToDeliver();
    document.getElementById('offline').disabled = false;
    document.getElementById('spinner').hidden = false;

    order.stateOrder = 'DELIVERED';
    orderSocket.send(JSON.stringify(order));

    changeState('ONLINE_WAITING');
    changeOrderState('DELIVERED', order);
}

function onlineWorking(startWorking){
    var state = document.getElementById('dgState').value;
    if(state === 'ONLINE_WORKING' || startWorking) {
        //if order is undefined, getOrder (if the page reloads)
        if(!startWorking) getActiveOrder();
        else showOrderToDeliver();
        delivering = true;

        document.getElementById('spinner').hidden = true;
        document.getElementById('offline').disabled = true;
        document.getElementById('online').disabled = true;
    }
}

function getActiveOrder() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            order = JSON.parse(this.responseText);
            showOrderToDeliver();
        }
    };
    var url = window.location.href.split('/');
    url[3] = 'order';
    url = url.join('/');
    url += '?dgEmail=' + email;
    xhttp.open("GET", url , true);
    xhttp.send();
}

function showOrderToDeliver(){
    document.getElementById('order').hidden = false;
    document.getElementById('tableOrder').hidden = false;
    document.getElementById('finishDelivering').hidden = false;
    document.getElementById('foNameDeliver').innerHTML = order.foName;
    document.getElementById('foPhoneDeliver').innerHTML = order.foPhone;
    document.getElementById('elaborationTimeDeliver').innerHTML = order.elaborationTime + ' minutes';
    document.getElementById('clientAddressDeliver').innerHTML = order.clientAddress;
    document.getElementById('clientPhoneDeliver').innerHTML = order.clientPhone;
    document.getElementById('totalPriceDeliver').innerHTML = order.totalPrice + '$';
    document.getElementById('tipDeliver').innerHTML = ((order.tippingPercentage * order.totalPrice) / 100).toString() + '$';
}

function hideOrderToDeliver(){
    document.getElementById('order').hidden = true;
    document.getElementById('tableOrder').hidden = true;
    document.getElementById('finishDelivering').hidden = true;
}

function forceLogOut(){
    login_logout('OFFLINE');
    $("#timeOutModal").modal();
}

function showRating() {
    var ratingDiv = document.getElementById("ratingTitle");
    var ratingAmt = document.getElementById("ratingQuantity").value;
    var rating = document.getElementById("rating").value;
    setRating(rating, ratingAmt, ratingDiv, 1.5);
}
