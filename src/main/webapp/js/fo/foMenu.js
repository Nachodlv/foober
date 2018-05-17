var orders;
getOrders();


function minutesAgo() {
    var timeElapsed = document.getElementsByClassName("timeElapsed");
    var issuedTimes = document.getElementsByClassName("issuedTime");

    for(var i = 0; i < issuedTimes.length; i++) {
        var issuedTime = Number(issuedTimes[i].value);
        var time = new Date();
        var timeMillis = time.getTime();
        var timeDiff = timeMillis - issuedTime;
        // strip the ms
        timeDiff /= 1000;
        // get seconds
        var seconds = Math.round(timeDiff);
        var minutes = seconds/60;
        timeElapsed[i].innerHTML = (" " + parseFloat(minutes).toFixed(0) + " minutes ago");
        setInterval(function() { minutesAgo()}, 60000);
    }
}

function getOrders() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            orders = JSON.parse(this.responseText);
            setOrders(orders);
            minutesAgo();
        }
    };
    var url = "http://localhost:8080/orders?foID=" + document.getElementById("foID").value;
    xhttp.open("GET", url , true); //todo corregir url con window.etc
    xhttp.send();
}

function setOrders(orders) {
    var panel = document.getElementById("panel1");
    for (var i = 0; i < orders.length; i++) {
        var column = document.createElement('div');
        column.className = 'col-md-4 text-center';
        column.style = 'margin-bottom: 1rem;';

        var img = document.createElement('img');
        img.className = 'card-img-top rounded-circle';
        img.align = 'center';
        img.style = 'height: 8rem; width:8rem;';

        var title = document.createElement('h5');
        title.className = 'card-title';

        var order = orders[i];
        if (order.deliveryGuy === undefined) {
            img.src = '../../images/anonymus.png';
            title.innerHTML = 'undefined';
        } else {
            img.src = 'http://localhost:8080/images/' + order.deliveryGuy.email + '.png';
            title.innerHTML = order.deliveryGuy.name;
        }

        var issuedTime = document.createElement('input');
        issuedTime.className = 'issuedTime';
        issuedTime.hidden = true;
        issuedTime.value = order.issuedTime;

        var timeIcon = document.createElement('i');
        timeIcon.className = 'far fa-clock';

        var time = document.createElement('p');
        time.className = 'card-text timeElapsed';

        var clientIcon = document.createElement('i');
        clientIcon.className = 'far fa-user';

        var client = document.createElement('p');
        client.className = 'card-text';
        client.innerHTML = order.client.email;

        column.appendChild(img);
        column.appendChild(title);
        column.appendChild(issuedTime);
        column.appendChild(timeIcon);
        column.appendChild(time);
        column.appendChild(clientIcon);
        column.appendChild(client);

        panel.appendChild(column);
    }
}

function getFoUrl() {

}
