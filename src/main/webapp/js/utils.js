function changeOrderState(state, order){
    var xhttp = new XMLHttpRequest();
    var url = window.location.href.split('/');
    url[3] = 'order';
    url = url.join('/');
    url += '?orderId=' + order.id;
    url += '&state=' + state;
    xhttp.open("POST", url , true);
    xhttp.send();
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