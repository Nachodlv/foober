var online = document.getElementById("online");
var offline = document.getElementById("offline");

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