priceProduct();

function priceProduct() {
    var htmlElements = document.getElementsByClassName("product-row");
    for (var i = 0; i < htmlElements.length; i++) {
        var elements = htmlElements[i];
        elements.addEventListener("input", function (elements) {
            var elementArray = elements.currentTarget.childNodes;
            var price = parseInt(elementArray[5].getAttribute('price'));
            var quantity = elementArray[7].childNodes[0].value;
            elementArray[9].innerHTML = price * quantity + ' $';

            //calculate total order
            var products = document.getElementsByClassName("totalPriceProduct");
            var totalButton = document.getElementById("totalPrice");
            var total = 0;
            for(var i=0; i<products.length; i++){
                total += parseInt(products[i].innerHTML);
            }
            totalButton.childNodes[0].innerHTML = "Make order - " + total + " $";
            document.getElementById("total").value = total;
        })
    }
}

function completeOrder(){
    var htmlElements = document.getElementsByClassName("product-row");
    var products = document.getElementById("products");
    var added = false;

    erasePreviousOrder(products);
    completeTotals();

    for(var i=0; i<htmlElements.length; i++){
        var row = htmlElements[i];
        var columnsRow = row.childNodes;
        var quantity = columnsRow[7].childNodes[0].value;
        if(quantity > 0){
            added = true;
            var rowCloned = row.cloneNode(true);
            rowCloned.class = 'product-row-cloned';

            var rowClonedNodes = rowCloned.childNodes;
            //transform quantity from input to text
            rowClonedNodes[7].innerHTML = rowClonedNodes[7].childNodes[0].value;

            //transform comment from input to text
            var newComment = document.createElement('p');
            newComment.classList.add('comment-product');
            newComment.innerHTML = rowClonedNodes[11].childNodes[0].value;
            rowClonedNodes[11].appendChild(newComment);
            rowClonedNodes[11].removeChild(rowClonedNodes[11].childNodes[0]);
            products.appendChild(rowCloned);
        }
    }

    var submitButton = document.getElementById('make-order-submit');
    submitButton.disabled = !added;
}

function erasePreviousOrder(products){
    //index 0 = text and 1 = tbody
    //index > 1 product rows
    while(products.childNodes.length > 2){
        products.removeChild(products.childNodes[2])
    }
}

function completeTotals(){
    var total = Number(document.getElementById("total").value);
    var totalCheck = document.getElementById("total-price-check");
    var tippingPercentage = Number(document.getElementById("tipping-percentage").value);
    var deliveryManTip = document.getElementById("delivery-man-tip");

    totalCheck.innerHTML = "Total: " + total + " $";
    deliveryManTip.innerHTML = "Delivery-man tip: " + (total * tippingPercentage)/100 + " $";
}