var startTime = document.getElementById("initialTime").value;

function end() {
    var time = new Date();
    var timeMillis = time.getTime();
    var timeDiff = timeMillis - startTime; //in ms
    // strip the ms
    timeDiff /= 1000;
    // get seconds
    var seconds = Math.round(timeDiff);
    var minutes = seconds/60;
    document.getElementById("elapsedTime").innerHTML = (" " + parseFloat(minutes).toFixed(0) + " minutes ago");
}


end();
setInterval(function(){ end() }, 60000);