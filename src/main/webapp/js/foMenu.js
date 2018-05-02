function end() {
    var timeElapsed = document.getElementsByClassName("timeElapsed");
    var issuedTimes = document.getElementsByClassName("issuedTime");

    for(var i = 0; i < issuedTimes.length; i++) {
        var issuedTime = Number(issuedTimes[i].value);
        var time = new Date();
        var timeMillis = time.getTime();
        var timeDiff = timeMillis - issuedTime;
        // strip the ms
        timeDiff /= 1000;
        // get seconds
        var seconds = Math.round(timeDiff);
        var minutes = seconds/60;
        timeElapsed[i].innerHTML = (" " + parseFloat(minutes).toFixed(0) + " minutes ago");
        setInterval(function() { end()}, 60000);
    }
}

end();