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

function setDirections(origin, waypoint, destination){
    var directionsService = new google.maps.DirectionsService();
    var request = {
        origin: origin,
        destination: {placeId: destination},
        waypoints: [{location: {placeId: waypoint}}],
        travelMode: 'DRIVING'
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
}
