var directionsService = new google.maps.DirectionsService();
//Esta inicializacion se DEBE hacer en el metodo que se llame en el CALLBACK del .jsp


function distanceAndTime(from, to, mode) {
    var rslt = new Array(2);
    var request = {
        origin: from,           //LatLng | String | google.maps.Place
        destination: to,        //LatLng | String | google.maps.Place
        travelMode: mode,       //google.maps.DirectionsTravelMode.DRIVING
        unitSystem: google.maps.UnitSystem.METRIC
    };

    directionsService.route(request, function (response, status) {
        if (status === google.maps.DirectionsStatus.OK) {
            rslt[0] = response.routes[0].legs[0].distance.value + " meters";
            rslt[1] = (response.routes[0].legs[0].duration.value)*60 + " minutes";
        }
    });
    return rslt;
}