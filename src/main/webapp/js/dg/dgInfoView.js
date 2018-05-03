function selectMeansOfTransport(){
    var meansOfTransport = document.getElementById("meansOfTransport");
    document.getElementById(meansOfTransport.value).setAttribute("checked", "");

}

selectMeansOfTransport();