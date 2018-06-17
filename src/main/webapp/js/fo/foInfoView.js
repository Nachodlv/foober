fillAddressInput();
function fillAddressInput(){
    var placeId = document.getElementById('address').value;
    var url = 'https://maps.googleapis.com/maps/api/place/details/json?placeid='+ placeId +'&key=AIzaSyDRtC9nTA8nx3D7jpH07HcU5SjpLhQgA6E';

    $.ajax({
        url: url,
        type: "GET",
        dataType: 'json',
        cache: false,
        success: function(response){
            document.getElementById('autocomplete').value = response.result.formatted_address;
        }
    });

}