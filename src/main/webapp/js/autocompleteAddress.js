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

function fillAddressInput(placeId, input, element, small){
    var url = 'https://maps.googleapis.com/maps/api/place/details/json?placeid='+ placeId +'&key=AIzaSyDRtC9nTA8nx3D7jpH07HcU5SjpLhQgA6E';

    $.ajax({
        url: url,
        type: "GET",
        dataType: 'json',
        cache: false,
        success: function(response){
            if(response.status === 'INVALID_REQUEST') {
                console.error(response.status);
                if(input) input.value = 'Undefined address';
                else element.innerHTML = 'Undefined address';
                return;
            }
            if(small){
                if(input)input.value = response.result.name;
                else element.innerHTML = response.result.name;
            }else {
                if(input)input.value = response.result.formatted_address;
                else element.innerHTML = response.result.formatted_address;
            }
        }
    });
}