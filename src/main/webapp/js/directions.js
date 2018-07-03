var directionsDisplay;
var map;
function initMap() {
    directionsDisplay = new google.maps.DirectionsRenderer();
    var mapOptions = {
        zoom:8,
        center: {lat: 0, lng: 0}
    };
    map = new google.maps.Map(document.getElementById('map'), mapOptions);
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

function setMarker(position, name, label){
    var marker = new google.maps.Marker({
        position: position,
        map: map,
        label: label
    });

    var description = generateDescriptionMarker(name);
    if(description) {
        var infowindow = new google.maps.InfoWindow({
            content: description
        });
        marker.addListener('click', function() {
            infowindow.open(map, marker);
        });
    }

    return marker;
}

function transformPlaceIdToPosition(placeId, promiseFunction){
    var service = new google.maps.places.PlacesService(map);
    service.getDetails({
        placeId: placeId
    }, promiseFunction);
}

function centerMap(position){
    if(!position && navigator.geolocation){
        navigator.geolocation.getCurrentPosition(function (position) {
            map.setCenter({
                lat: position.coords.latitude,
                lng: position.coords.longitude
            });
        })
    }else {
        transformPlaceIdToPosition(position, function (result) {
            position = result.geometry.location;
            map.setCenter(position);
        })
    }
}

function generateDescriptionMarker(name) {
    return '<div id="content">'+
        '<div id="siteNotice">'+
        '</div>'+
        '<div id="bodyContent">'+
        '<span>' + name + '</span>' +
        '</div>';
}
