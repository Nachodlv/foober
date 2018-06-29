var directionsDisplay;
function initMap() {
    directionsDisplay = new google.maps.DirectionsRenderer();
    var mapOptions = {
        zoom:7,
        center: {lat: 0, lng: 0}
    };
    var map = new google.maps.Map(document.getElementById('map'), mapOptions);
    directionsDisplay.setMap(map);
}

function setDirections(origin, waypoint, destination, meansOfTransport){
    meansOfTransport = getMeansOfTransport(meansOfTransport);
    var directionsService = new google.maps.DirectionsService();
    var request = {
        origin: origin,
        destination: {placeId: destination},
        waypoints: [{location: {placeId: waypoint}}],
        travelMode: meansOfTransport
    };
    directionsService.route(request, function(result, status) {
        if (status === 'OK') {
            directionsDisplay.setDirections(result);
        } else if(status === 'ZERO_RESULTS') {
            document.getElementById('map').hidden = true;
        } else {
            console.error(status);
        }
    });
    return encodeURI('https://www.google.com/maps/dir/?api=1&destination=.&destination_place_id=' + destination +
        '&waypoints=.&waypoint_place_ids=' + waypoint +
        '&travelmode=' + meansOfTransport.toLowerCase());

}

function getMeansOfTransport(meansOfTransport){
    switch (meansOfTransport) {
        case '1': return 'BICYCLING';
        case '2': return 'WALKING';
        default: return 'DRIVING';
    }
}
