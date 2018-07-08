var directionsService;
//Esta inicializacion se DEBE hacer en el metodo que se llame en el CALLBACK del .jsp


function initMap() {
    //TODO initialize map with the center in the fo address
    directionsService = new google.maps.DirectionsService();
}

function distanceAndTime(from, to, mode, responseFunction) {
    mode = getMeansOfTransport(mode);

    var rslt = new Array(2);
    var request = {
        origin: from,           //LatLng | String | google.maps.Place
        destination: {placeId: to},        //LatLng | String | google.maps.Place
        travelMode: mode,       //google.maps.DirectionsTravelMode.DRIVING
        unitSystem: google.maps.UnitSystem.METRIC
    };

    directionsService.route(request, responseFunction);
    return rslt;
}

function getMeansOfTransport(meansOfTransport){
    switch (meansOfTransport) {
        case '1': return 'BICYCLING';
        case '2': return 'WALKING';
        default: return 'DRIVING';
    }
}