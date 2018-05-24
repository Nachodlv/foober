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