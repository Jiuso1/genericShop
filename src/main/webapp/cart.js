let buttons = document.getElementsByClassName("addCart");
let cart = [];
let cartCounter = document.getElementById("cartCounter");

for (let i = 0; i < buttons.length; i++) {
    buttons[i].addEventListener('click', event => {
        let id = buttons[i].getAttribute('value');
        if (!cart.includes(id)) {
            cart.push(id);
            updateCartCounter();
        }
    })
}

function updateCartCounter() {
    let spanCartCounter = document.getElementById("cartCounter");
    spanCartCounter.textContent = cart.length;
}

cartCounter.onclick = function () {
    sendCartToJava(cart);
}

function sendCartToJava(cart) {
    // Utilizando jQuery AJAX para enviar datos
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/genericShop/do/sendCart",
        data: {cart: cart},
        success: function (response) {
            console.log("Success sending data!");
            window.location.href = "http://localhost:8080/genericShop/do/viewCart";
        },
        error: function (error) {
            console.error("Failed sending data :(", error);
        }
    });
}
