var socket = new WebSocket("ws://localhost:8080/dgOnline");
socket.onopen = function (ev) {
    socket.onmessage = function (ev) {
        console.log(ev.data);
    };
};

