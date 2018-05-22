getDeliveryGuy();

function getDeliveryGuy() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            var deliveryGuy = JSON.parse(this.responseText);
        }
    };
    var url = window.location.href.split('/');
    debugger;
    url[3] = 'orderInfo' + url[3].match('?*');
    url = url.join('/');
    xhttp.open("GET", url , true);
    xhttp.send();
}