var autocomplete;
var fillIn;

function initAutocomplete(autocompleteId, fillInId) {
    autocompleteId = autocompleteId || 'autocomplete';
    fillIn = fillInId || 'address';
    // Create the autocomplete object, restricting the search to geographical
    // location types.
    var input = document.getElementById(autocompleteId);
    autocomplete = new google.maps.places.Autocomplete(input,{types: []});

    google.maps.event.addDomListener(input, 'keydown', function(event) {
        if (event.keyCode === 13) {
            event.preventDefault();
        }
    });

    // When the user selects an address from the dropdown, populate the address
    // fields in the form.
    autocomplete.addListener('place_changed', fillInAddress);
}

function fillInAddress() {
    // Get the place details from the autocomplete object.
    document.getElementById(fillIn).value = autocomplete.getPlace().place_id;
}

function geolocate() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var geolocation = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            var circle = new google.maps.Circle({
                center: geolocation,
                radius: position.coords.accuracy
            });
            autocomplete.setBounds(circle.getBounds());
        });
    }
}

function fillAddressInput(placeId, input, element, small) {

    var service = new google.maps.places.PlacesService(input || element);
    service.getDetails({
        placeId: placeId
    },function (response, status) {
        if(status === 'INVALID_REQUEST') {
            console.error(response.status);
            if(input) input.value = 'Undefined address';
            else element.innerHTML = 'Undefined address';
            return;
        }
        if(small){
            if(input)input.value = response.name;
            else element.innerHTML = response.name;
        }else {
            if(input)input.value = response.formatted_address;
            else element.innerHTML = response.formatted_address;
        }
    } );
}

function callback(results, status){
    console.log(results);
}