const message = document.querySelector(".message");

message.addEventListener('click', hide);

function hide(){
    message.innerHTML = "";
}