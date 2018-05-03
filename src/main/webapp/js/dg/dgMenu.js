var online = document.getElementById("online");
var offline = document.getElementById("offline");
var socket = new WebSocket("ws://localhost:8080/dgOnline");

socket.onopen = function (event) {
    console.log("open");
    socket.send("hola");
};

socket.onmessage = function (ev) {
    console.log(ev.data);
};

function login_logout() {
    switch (document.getElementById("dgStatus").value) {
        case 'ONLINE_WAITING':
            online.disabled = true;
            offline.disabled = false;
            break;
        case 'OFFLINE':
            offline.disabled = true;
            online.disabled = false;
            break;
    }
}

login_logout();
online.addEventListener("click", login_logout());
offline.addEventListener("click", login_logout());
