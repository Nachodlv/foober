function getTime(issuedTime) {
    var time = new Date();
    var timeMillis = time.getTime();
    var timeDiff = timeMillis - issuedTime;
    timeDiff /= 1000;
    var seconds = Math.round(timeDiff);
    var minutes = parseFloat(seconds / 60);
    var hours = minutes / 60;
    var days = hours / 24;
    if (minutes < 60) {
        if (minutes<1.5) {
            return minutes.toFixed(0) + " minute ago";
        }
        return minutes.toFixed(0) + " minutes ago";
    }
    else if (minutes >= 60 && minutes < 60 * 24) {
        if (hours < 1.5) {
            return hours.toFixed(0) + " hour ago";
        }
        return hours.toFixed(0) + " hours ago";
    }
    else if(days < 1.5){
        return days.toFixed(0) + " day ago";
    } else return days.toFixed(0) + " days ago";
}

function sortTable(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("table");
    switching = true;
    // Set the sorting direction to ascending:
    dir = "asc";
    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
        // Start by saying: no switching is done:
        switching = false;
        rows = table.getElementsByTagName("tr");
        /* Loop through all table rows (except the
        first, which contains table headers): */
        for (i = 1; i < (rows.length - 1); i++) {
            // Start by saying there should be no switching:
            shouldSwitch = false;
            /* Get the two elements you want to compare,
            one from current row and one from the next: */
            x = rows[i].getElementsByTagName("td")[n];
            y = rows[i + 1].getElementsByTagName("td")[n];
            /* Check if the two rows should switch place,
            based on the direction, asc or desc: */
            if (dir === "asc") {
                if (compare(y.innerHTML, x.innerHTML)) {
                    // If so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            } else if (dir === "desc") {
                if (compare(x.innerHTML, y.innerHTML)) {
                    // If so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            /* If a switch has been marked, make the switch
            and mark that a switch has been done: */
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            // Each time a switch is done, increase this count by 1:
            switchcount++;
        } else {
            /* If no switching has been done AND the direction is "asc",
            set the direction to "desc" and run the while loop again. */
            if (switchcount === 0 && dir === "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}

function compare(x, y) {
    return x.toLowerCase() < y.toLowerCase()
}