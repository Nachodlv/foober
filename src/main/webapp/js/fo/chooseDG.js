var dgSocket = new WebSocket(getUrl('/dgOnline'));
var email;
var DGphone;
var DGname;
var deliveryGuys = [];
var MAX_DGS = 5;
var UPDATE_TIME = 30000; //time in ms
var markerColors = ['green', 'blue', 'yellow', 'orange', 'purple'];
var markerIndex = -1;

dgSocket.onopen = function (ev) {
    dgSocket.onmessage = function (ev) {
        var deliveryGuy = JSON.parse(ev.data);
        if(deliveryGuy.fromFo) return;
        if (deliveryGuy.state === 'ONLINE_WAITING') {
            newDeliveryGuy(deliveryGuy);
        } else {
            deleteDeliveryGuyFromHtml(deliveryGuy);
            deleteDeliveryGuy(deliveryGuy);
        }
    };
    setInterval( requestPositions, UPDATE_TIME);

};

function initializeMap() {
    initMap(document.getElementById('foAddress').value);
    getAllDGs();
}

//When the window is closed, the socket closes
window.onbeforeunload = closeSocket;
window.onunload = closeSocket;

function closeSocket(event) {
    dgSocket.close();
    //so the method doesn't execute twice
    window.onbeforeunload = undefined;
    window.onunload = undefined;
}

function addDeliveryGuyToHtml(deliveryGuy, i) {
    if(i >= MAX_DGS) return;

    var dgTable = document.getElementById('dgTable');
    var row = document.createElement('tr');
    row.classList.add('dgs', 'text-center');


    var name = document.createElement('td');
    var marker = document.createElement('td');
    var setMeansOfTransport = document.createElement('td');
    var phone = document.createElement('td');
    var rating = document.createElement('td');
    var distance = document.createElement('td');

    name.innerHTML = deliveryGuy.info.name;
    marker.innerHTML = "<td><i class=\"fa fa-circle\" aria-hidden=\"true\" style=\"color:" + deliveryGuy.markerInfo.color + ";\"></i></td>";
    setMeansOfTransport.className = "setMeansOfTransport";
    setMeansOfTransport.innerHTML = deliveryGuy.info.meansOfTransport;
    phone.innerHTML = deliveryGuy.info.phone;
    setRating(deliveryGuy.info.rating, deliveryGuy.info.ratingQuantity, rating);
    distance.innerHTML = deliveryGuy.distance + 'km';

    row.appendChild(name);
    row.appendChild(marker);
    row.appendChild(setMeansOfTransport);
    row.appendChild(phone);
    row.appendChild(rating);
    row.appendChild(distance);

    row.addEventListener('mousedown', function () {
        $("#modalConfirmDG").modal();
        email = deliveryGuy.info.email;
        DGname = deliveryGuy.info.name;
        DGphone = deliveryGuy.info.phone;

    });

    row.id = deliveryGuy.info.email;
    var rows = dgTable.childNodes;
    if(rows.length === i) dgTable.appendChild(row);
    else dgTable.replaceChild(row, dgTable.childNodes[i]);
    replaceMeansOfTransport();
}

function deleteDeliveryGuyFromHtml(deliveryGuyInfo) {
    var dgRow = document.getElementById(deliveryGuyInfo.email);
    //check if it is in the list before removing
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
            saveOrder(order.dgEmail, order.position);
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

function saveOrder(dgEmail, dgLocation) {
    var lat = dgLocation.lat;
    var lng = dgLocation.lng;
    var xhttp = new XMLHttpRequest();
    var url = window.location.href += '?dgEmail=' + dgEmail + '&dgLocation=' + lat + ',' + lng;
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
    for(var i=0;i<dgs.length;i++){
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
            markerInfo: {
                marker: undefined,
                color: undefined
            }
        });
    }
    requestPositions();
}

function setDistanceDg(deliveryGuy, position){
    distanceAndTime(position, document.getElementById('foAddress').value, deliveryGuy.info.meansOfTransport,
        function (response, status){
            if (status === google.maps.DirectionsStatus.OK) {
                deliveryGuy.distance = response.routes[0].legs[0].distance.value / 1000;
            }
            orderHtml(deliveryGuy);
        });
}

function deleteDeliveryGuy(deliveryGuy){
   for(var i=0;i<deliveryGuys.length;i++){
       if(deliveryGuys[i].info.email === deliveryGuy.email){
           deliveryGuys[i].markerInfo.marker.setMap(null);
           deliveryGuys[i].markerInfo.marker = undefined;
           deliveryGuys.splice(i, 1);
       }
   }
}

function newDeliveryGuy(infoDeliveryGuy){
    var deliveryGuy = {
        info: infoDeliveryGuy,
        distance: undefined,
        markerInfo: {
            color: undefined,
            marker: undefined
        }
    };
    setDistanceDg(deliveryGuy, infoDeliveryGuy.position);
}

function remove(deliveryGuy) {
    for(var i=0;i<deliveryGuys.length;i++){
        if(deliveryGuy.info.email === deliveryGuys[i].info.email){
            deliveryGuys.splice(i,1);
            deleteDeliveryGuyFromHtml(deliveryGuy.info);
        }
    }
}

function orderHtml(deliveryGuy){
    var added = false;
    var previousMarkerInfo = {marker: undefined, color:undefined};
    for(var i=0;i<deliveryGuys.length;i++){
        var dg = deliveryGuys[i];
        //found his row, erase it
        if(dg.info.email === deliveryGuy.info.email) {
            previousMarkerInfo = dg.markerInfo;
            deliveryGuys.splice(i, 1);
            deleteDeliveryGuyFromHtml(dg.info);
            i--;
        }
        //Add the deliveryGuy in his corresponding position. (sorted by distance, undefined positions are the last ones)
        else if((!dg.distance || dg.distance > deliveryGuy.distance) && !added){
            added = true;
            updateDeliveryGuy(deliveryGuy, i, previousMarkerInfo);
            deliveryGuys.splice(i, 0, deliveryGuy);
        }
        //If the deliveryGuy was added, then it moves all rows one position below
        else if (added){
            addDeliveryGuyToHtml(dg, i);
        }
    }
    //If the delivery wasn't added, then it adds it to the last position
    if(!added) {
        deliveryGuys.push(deliveryGuy);
        updateDeliveryGuy(deliveryGuy, deliveryGuys.length - 1, previousMarkerInfo);
    }
}

function updateDeliveryGuy(deliveryGuy, index, previousMarkerInfo){
    if(previousMarkerInfo.marker){
        previousMarkerInfo.marker.setPosition(deliveryGuy.info.position);
        deliveryGuy.markerInfo = previousMarkerInfo;
    }else{
        var color = getColor();
        deliveryGuy.markerInfo.marker = setMarker(deliveryGuy.info.position, deliveryGuy.info.name, undefined, color);
        deliveryGuy.markerInfo.color = color;
    }
    addDeliveryGuyToHtml(deliveryGuy, index);
}

function requestPositions(){
    deliveryGuys.forEach(function (dg) {
        dg.info.fromFo = true;
        dgSocket.send(JSON.stringify(dg.info))
    })
}

function getColor(){
    markerIndex = markerIndex===4 ? -1: markerIndex;
    markerIndex++;
    return markerColors[markerIndex];
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
