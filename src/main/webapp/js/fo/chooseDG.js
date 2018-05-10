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

window.addEventListener("beforeunload", function(e){
    dgSocket.close();
}, false);

function newDeliveryGuy(deliveryGuy){
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
    dgRow.parentNode.removeChild(dgRow);
}

function chooseDg(){
    var orderSocket = new WebSocket(getUrl('/orderSender'));
    orderSocket.onopen = function (ev) {
        orderSocket.send(getOrder());
        orderSocket.close();
    };
    $("#modalConfirmDG").modal('hide');
}

function getOrder() {
    //var div = document.getElementById('order').childNodes;
    //console.log(order);

    //TODO sacar hardcode
    var order =  {
        elaborationTime: '10',
        status: 'WAITING',
        fromFO: true,
        dgEmail: 'gonzalo.deachaval@ing.austral.edu.ar'
    };

    return JSON.stringify(order);
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