function errorCatcher(){
    var url = new URL(window.location.href);
    var error = url.searchParams.get('error');
    if (error == null) return;
    var div = document.getElementsByClassName('errorCatcher');
    div[0].innerHTML += '<div class="alert alert-danger alert-dismissible fade show" role="alert">\n' +
         error + '\n' +
        '<button type="button" class="close" data-dismiss="alert" aria-label="Close">\n' +
        '<span aria-hidden="true">&times;</span>\n' +
        '</button>\n' +
        '</div>';
}

errorCatcher();