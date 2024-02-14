let buttons = document.getElementsByClassName("addCart");
let cartCounter = 0;

for(let i = 0; i < buttons.length; i++) {
    buttons[i].addEventListener('click', event => {
        let id = buttons[i].getAttribute('value');
        console.log("puta que rico: " + id);
        cartCounter++;
        updateCartCounter();
    })
}

function updateCartCounter(){
    let spanCartCounter = document.getElementById("cartCounter");
    spanCartCounter.textContent = cartCounter;
}

