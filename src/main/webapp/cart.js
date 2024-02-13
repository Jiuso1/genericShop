var buttons = document.getElementsByClassName("addCart");

for(let i = 0; i < buttons.length; i++) {
    buttons[i].addEventListener('click', event => {
        var id = buttons[i].getAttribute('value');
        console.log("puta que rico: " + id);
    })
}



