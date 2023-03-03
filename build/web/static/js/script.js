
function addInCart(id){
    event.preventDefault();
//    console.log(id);
    
    var cartList = document.getElementById("cart-item-count");

    var xhr = new XMLHttpRequest();


    xhr.open("GET", `http://localhost:9494/mateShop/add-to-cart?id=${id}`, "true");

    xhr.onload = function(){

        if(this.status === 200){
            console.log(cartList);
            console.log(this.responseText);
        }      
    };
    xhr.send();

}