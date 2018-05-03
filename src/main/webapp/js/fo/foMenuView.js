function end(id, issuedTime) {
    var time = new Date();
    var timeMillis = time.getTime();
    var timeDiff = timeMillis - issuedTime; //in ms
    // strip the ms
    timeDiff /= 1000;
    // get seconds
    var seconds = Math.round(timeDiff);
    var minutes = seconds/60;
    var correspondingId = "elapsedTime_" + id;
    document.getElementById(correspondingId).innerHTML = (" " + parseFloat(minutes).toFixed(0) + " minutes ago");
    setInterval(function() { end(id, issuedTime)}, 60000);
}