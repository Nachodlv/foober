var dgSocket = new WebSocket(getUrl('/dgOnline'));
var email;
var DGphone;
var DGname;
var deliveryGuys = [];
var MAX_DGS = 5;
getAllDGs();

dgSocket.onopen = function (ev) {
    dgSocket.onmessage = function (ev) {
        var deliveryGuy = JSON.parse(ev.data);
        if(deliveryGuy.fromFo) return;
        if (deliveryGuy.state === 'ONLINE_WAITING') {
            newDeliveryGuy(deliveryGuy);
        } else {
            deleteDeliveryGuy(deliveryGuy);
            deleteDeliveryGuyFromHtml(deliveryGuy);
        }
    };
    setInterval( requestPositions, 30000);

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

function addDeliveryGuyToHtml(deliveryGuy) {
    //check if it is already on the list
    if (document.getElementById(deliveryGuy.email)) return;

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
        email = deliveryGuy.email;
        DGname = deliveryGuy.name;
        DGphone = deliveryGuy.phone;

    });

    row.id = deliveryGuy.email;
    dgTable.appendChild(row);
    replaceMeansOfTransport();
}

function deleteDeliveryGuyFromHtml(deliveryGuy) {
    var dgRow = document.getElementById(deliveryGuy.email);
    //check if it is the list before removing
    if (dgRow) dgRow.parentNode.removeChild(dgRow);
}

function chooseDg() {
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
        foPhone: document.getElementById('foPhone').value,
        dgName: DGname,
        dgPhone: DGphone,
        clientName: document.getElementById('clientName').value,
        foAddress: document.getElementById('foAddress').value,
        accepted: false,
        position: {
            lat: 0,
            lng: 0
        }
    };
}

function onWindowClose(orderSocket) {
    //Close previous socket
    dgSocket.close();
    //Close order socket when window is closed
    window.onunload = window.onbeforeunload = orderSocketClose;

    function orderSocketClose() {
        orderSocket.close();
        window.onunload = window.onbeforeunload = undefined;
    }
}

function waitingForResponse(orderSocket) {
    document.getElementById('table').hidden = true;
    document.getElementById('waitingForResponse').hidden = false;
    document.getElementById('chooseDGLater').classList.add('disabled');

    orderSocket.onmessage = function (message) {
        var order = JSON.parse(message.data);
        if (order.fromFO) return;
        orderSocket.close();
        document.getElementById('chooseDGLater').classList.remove('disabled');
        if (order.accepted) {
            //order accepted
            saveOrder(order.dgEmail);
            var newHref = window.location.href.split('/');
            newHref[3] = 'foMenu?orderAccepted=true';
            window.location.href = newHref.join('/');
        } else {
            //order rejected
            document.getElementById('table').hidden = false;
            document.getElementById('waitingForResponse').hidden = true;
            errorCatcher('The delivery-guy rejected the order.');
        }
    }
}

function replaceMeansOfTransport() {
    var setTransport = document.getElementsByClassName("setMeansOfTransport");
    for (var i = 0; i < setTransport.length; i++) {
        switch (setTransport[i].innerHTML) {
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

function saveOrder(dgEmail) {
    var xhttp = new XMLHttpRequest();
    var url = window.location.href += '?dgEmail=' + dgEmail;
    xhttp.open("POST", url, true);
    xhttp.send("dgEmail=" + dgEmail);
}

function errorCatcher(error) {
    var div = document.getElementById('orderRejected');
    div.innerHTML = '<div class="alert alert-danger alert-dismissible fade show" role="alert">\n' +
        error + '\n' +
        '<button type="button" class="close" data-dismiss="alert" aria-label="Close">\n' +
        '<span aria-hidden="true">&times;</span>\n' +
        '</button>\n' +
        '</div>';
}

function closeDgSocket(dgEmail) {

}

function setRating(rating, total, div) {
    var html = '';
    if (total > 0) {
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
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var dgs = JSON.parse(this.responseText);
            createDgs(dgs);
            replaceMeansOfTransport();
        }
    };
    var url = window.location.href.split('/');
    url[3] = 'getDGs';
    var urlFinal = url.join('/');
    xhttp.open("GET", urlFinal, true);
    xhttp.send();
}

function createDgs(dgs){
    for(var i=0;i<MAX_DGS&&i<dgs.length;i++){
        var dg = dgs[i];
        deliveryGuys.push({
            info: {
                name: dg.name,
                phone: dg.phone,
                meansOfTransport: dg.meansOfTransport,
                rating: dg.rating,
                ratingQuantity: dg.ratingQuantity,
                email: dg.email,
                state: dg.state,
                fromFo: true,
                position: {
                    lat: undefined,
                    lng: undefined
                }
            },
            distance: undefined,
            marker: undefined
        });
    }
    requestPositions();
}

function setDistanceDg(deliveryGuy, position){
    distanceAndTime(position, document.getElementById('foAddress').value, deliveryGuy.info.meansOfTransport,
        function (response, status){
            if (status === google.maps.DirectionsStatus.OK) {
                deliveryGuy.distance = response.routes[0].legs[0].distance.value;
            }
            orderHtml();
            //TODO add or modify the marker of the dg
        });
}

function deleteDeliveryGuy(deliveryGuy){
   for(var i=0;i<deliveryGuys.length;i++){
       if(deliveryGuys[i].info.email === deliveryGuy.email){
           deliveryGuys.splice(i, 1);
       }
   }
}

function newDeliveryGuy(infoDeliveryGuy){
    var deliveryGuy = contains(infoDeliveryGuy);
    if(deliveryGuy){
        setDistanceDg(deliveryGuy, infoDeliveryGuy.position)
    } else {
        var length = deliveryGuys.push({
            info: infoDeliveryGuy,
            distance: undefined,
            marker: undefined
        });
        setDistanceDg(deliveryGuys[length-1], infoDeliveryGuy.position);
    }
}

function contains(deliveryGuyInfo) {
    for(var i=0;i<deliveryGuys.length;i++){
        if(deliveryGuyInfo.email === deliveryGuys[i].info.email)return deliveryGuys[i];
    }
    return undefined;
}

function orderHtml(){
//TODO order deliveryGuys array and modify the html
}

function requestPositions(){
    deliveryGuys.forEach(function (dg) {
        dg.info.fromFo = true;
        dgSocket.send(JSON.stringify(dg.info))
    })
}

disablePopovers = function(){
    closePopover('popoverChooseDGLater');
};

openPopovers = function() {
    var chooseDGLater = $("#popoverChooseDGLater");
    chooseDGLater.popover('enable');
    chooseDGLater.popover('show');
    document.getElementById('closeChooseDGLater').addEventListener('click', function (ev) {
        closePopover('popoverChooseDGLater')
    });
};
