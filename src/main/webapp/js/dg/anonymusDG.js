function replaceSrc() {

    var images = document.getElementsByTagName('img');

    for(var i = 0; i < images.length; i++)
    {
        var img = images[i];

        if(!img.complete || typeof img.naturalWidth !== "undefined" && img.naturalWidth === 0)
        {
            img.src = '../images/anonymus.png';
        }
    }
}

window.onload = replaceSrc;