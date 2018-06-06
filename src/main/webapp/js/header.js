function openPopovers() {}
function disablePopovers(){}

onStart();

function onStart(){
    var noPopovers = localStorage.getItem("noPopovers");
    var checkbox = document.getElementById('tutorialCheckbox');
    if(!noPopovers) checkbox.checked = true;
}

function changeCheckbox(){
    var checkbox = document.getElementById('tutorialCheckbox');
    if(checkbox.checked) {
        localStorage.removeItem('noPopovers');
        if(openPopovers)openPopovers();
    }
    else {
        localStorage.setItem('noPopovers', 'true');
        if(disablePopovers) disablePopovers();
    }
}