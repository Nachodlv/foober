
const applicationServerPublicKey = 'BDqh7NooH81x3hSQcQe3nTeIcRuWd0H6JwdgNiW8dHlh23_Ulc9PR5_QC0ZAOC8_9gJvK_QDmSJWxm3NOeJpkf8';

const pushButton = document.querySelector('.js-push-btn');

var isSubscribed = false;
var swRegistration = null;

function urlB64ToUint8Array(base64String) {
    const padding = '='.repeat((4 - base64String.length % 4) % 4);
    const base64 = (base64String + padding)
        .replace(/-/g, '+')
        .replace(/_/g, '/');

    const rawData = window.atob(base64);
    const outputArray = new Uint8Array(rawData.length);

    for (var i = 0; i < rawData.length; ++i) {
        outputArray[i] = rawData.charCodeAt(i);
    }
    return outputArray;
}


if ('serviceWorker' in navigator && 'PushManager' in window) {

    navigator.serviceWorker.register('../../../js/dg/sw.js')
        .then(function(swReg) {
            swRegistration = swReg;
            initialiseUI();
        })
        .catch(function(error) {
            console.error('Service Worker Error', error);
        });
} else {
    console.warn('Push messaging is not supported');
    pushButton.textContent = 'Push Not Supported';
}

function initialiseUI() {
    if(!isSubscribed) subscribeUser();

    // Set the initial subscription value
    swRegistration.pushManager.getSubscription()
        .then(function(subscription) {
            isSubscribed = !(subscription === null);
        });
}

function subscribeUser() {
    const applicationServerKey = urlB64ToUint8Array(applicationServerPublicKey);
    swRegistration.pushManager.subscribe({
        userVisibleOnly: true,
        applicationServerKey: applicationServerKey
    })
        .then(function(subscription) {

            updateSubscriptionOnServer(subscription);

            isSubscribed = true;
        })
        .catch(function(err) {
            console.log('Failed to subscribe the user: ', err);
        });
}

function updateSubscriptionOnServer(subscription) {
    // TODO: Send subscription to application server

}
