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
    //Let's go to the web!
    window.location.href = "http://localhost:8080/genericShop/do/viewCart";
}

function sendCartToJava(cart) {
    // Enviar la estructura de datos al servidor (Java) con AJAX utilizando jQuery
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/genericShop/do/sendCart",
        contentType: "application/json",
        data: JSON.stringify({ cart: cart }),
        success: function(response) {
            console.log("Success sending data!");
            // Puedes manejar la respuesta del servidor si es necesario
        },
        error: function(error) {
            console.error("Failed sending data :(");
        }
    });
}