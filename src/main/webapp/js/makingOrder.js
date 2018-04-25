var htmlElements = document.getElementsByClassName("product-row");
for(var i=0;i<htmlElements.length ; i++){
    var elements = htmlElements[i];
    elements.addEventListener("change", function (elements) {
        var elementArray = elements.currentTarget.childNodes;
        var price = parseInt( elementArray[5].getAttribute('price'));
        var quantity = elementArray[7].childNodes[0].value;
        elementArray[9].innerHTML = price * quantity + ' $';
    })
}
// forEach(function (row) {
//     var elements = row.childNodes;
//     elements[7].addEventListener("change", function () {
//         var price = parseInt(elements[0].id);
//         var quantity = elements[7].childNodes[0].value;
//         elements[9].innerHtml = price * quantity;
//     })
// });