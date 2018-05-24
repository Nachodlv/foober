
function starClick(starNumber){
    $('#rateModal').modal('hide');
    var div = document.getElementById('stars');
    changeOrderState('REVIEWED', order);
    setRating(order.deliveryGuy.rating + starNumber, order.deliveryGuy.ratingQuantity + 1, div, 1.5);
    newReview('REVIEWED', starNumber);
}

function newReview(state, review){
    var xhttp = new XMLHttpRequest();
    var url = window.location.href.split('/');
    url[3] = 'dgReview?dgEmail=' + order.deliveryGuy.email + '&review=' + review;
    xhttp.open("POST", url.join('/') , true);
    xhttp.send();
}

function setRating(rating, total, div, size) {
    var html = '';
    size = size || 1; //if size is undefined the size is 1rem
    if(total > 0) {
        var averageRating = rating / total;
        var absolutRating = Math.trunc(averageRating);
        var remaining = averageRating - absolutRating;
        var star = '<i class=\"fas fa-star\" style="font-size:' + size + 'rem"></i>';
        var halfStar = '<i class=\"fas fa-star-half\" style="font-size:' + size + 'rem"></i>';
        for (var i = 0; i < absolutRating; i++) {
            html += star
        }
        if (remaining >= 0.75) html += star;
        else if (remaining >= 0.25) html += halfStar;
    } else {
        html = 'no ratings yet'
    }
    div.innerHTML = html;
}