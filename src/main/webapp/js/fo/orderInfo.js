var order;
getOrder();


function getOrder() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            order = JSON.parse(this.responseText);
            setOrder();
            setProducts();
            if(order.stateOrder === 'DELIVERING') openWebSocket();
            else if(order.stateOrder === 'DELIVERED') $('#rateModal').modal();
        }
    };
    var url = window.location.href.split('/');
    var orderId = window.location.href.split('?');
    url[3] = 'getOrder?' + orderId[1];
    var urlFinal = url.join('/');
    xhttp.open("GET", urlFinal , true);
    xhttp.send();
}

function setOrder() {
    document.getElementById("order").innerHTML = '#' + order.id;
    var imgDG = document.getElementById("imgDG");
    if(order.deliveryGuy === undefined) {
        imgDG.src = '../../images/anonymus.png';
        addLink(imgDG);
        document.getElementById("dgName").innerHTML = 'not assigned';
        document.getElementById("dgPhone").innerHTML = 'not assigned';
        document.getElementById("dgEmail").innerHTML = 'not assigned';
        var button = document.getElementById("but");
        button.innerHTML = 'Choose DG';
        button.hidden = false;
        addLink(button);
    } else {
        var url = window.location.href.split('/');
        url[3] = 'images/' + order.deliveryGuy.email + '.png';
        imgDG.src = url.join('/');

        document.getElementById("dgName").innerHTML = order.deliveryGuy.name;
        document.getElementById("dgPhone").innerHTML = order.deliveryGuy.phone;
        document.getElementById("dgEmail").innerHTML = order.deliveryGuy.email;

    }
    document.getElementById("clientName").innerHTML = order.client.name;
    document.getElementById("clientPhone").innerHTML = order.client.phone;
    document.getElementById("clientEmail").innerHTML = order.client.email;
    document.getElementById("clientAddress").innerHTML = order.client.address;

    document.getElementById("totalCost").innerHTML = 'Total cost: ' + getTotalCost(order.orderedProducts) + '$';
}

function setProducts() {
    var productTable = document.getElementById("productsTBody");

    var orderedProducts = order.orderedProducts;
    for (var i = 0; i < orderedProducts.length; i++) {
        var orderedProduct = orderedProducts[i];
        var tr = document.createElement('tr');
        var td = document.createElement('td');
        td.class = 'col';
        td.style = 'width:20%; padding: 0.6rem;';
        var img = document.createElement('img');
        img.width = 100;
        img.height = 100;
        var url = window.location.href.split('/');
        url[3] = 'images/' + orderedProduct.product.id + '.png';
        img.src = url.join('/');
        td.appendChild(img);
        var td2 = document.createElement('td');
        td2.class = 'col';
        td2.style = 'width:40%;';
        var p2 = document.createElement('p');
        var b2 = document.createElement('b');
        b2.innerHTML = orderedProduct.product.name + ' x' + orderedProduct.quantity;
        p2.appendChild(b2);
        td2.appendChild(p2);
        var td3 = document.createElement('td');
        td3.class = 'col';
        td3.style = 'width:40%;';
        var p3 =  document.createElement('p');
        var comment = orderedProduct.comment;
        if (comment === '') {
            var i3 = document.createElement('i');
            i3.innerHTML = 'no comment';
            p3.appendChild(i3);
        } else {
            p3.innerHTML = comment;
        }
        td3.appendChild(p3);

        tr.appendChild(td);
        tr.appendChild(td2);
        tr.appendChild(td3);
        productTable.appendChild(tr);
    }
 }

function getTotalCost(orderedProducts) {
    var totalCost = 0;
    for(var i = 0; i < orderedProducts.length; i++) {
        totalCost += orderedProducts[i].quantity * orderedProducts[i].product.price;
    }
    return totalCost;
}

function addLink(img){
    img.onclick = function() {
        var url = window.location.href.split('/');
        url[3] = 'chooseDG';
        var finalUrl = url.join('/');
        window.open(finalUrl, "_self")
    };
}

function openWebSocket(){
    var orderSocket = new WebSocket(getUrl('/orderSender/' + order.deliveryGuy.email));
    orderSocket.onmessage = function (ev) {
        var orderReceived = JSON.parse(ev.data);
        if(!orderReceived.fromFO && orderReceived.stateOrder === 'DELIVERED') {
            $('#deliveredModal').modal({
                backdrop: false
            });
        }
    };
}

function closeDeliveredModal(){
    $('#deliveredModal').modal('hide');
    $('#rateModal').modal();
}

