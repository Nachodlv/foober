onStart();

function onStart(){
    if (typeof(Storage) !== "undefined") {
        var noPopovers = localStorage.getItem("noPopovers");
        if(!noPopovers) openPopovers();
    }
}

function closePopover(popover){
    var element = $("#" + popover);
    element.popover('hide');
    element.popover('disable');
}