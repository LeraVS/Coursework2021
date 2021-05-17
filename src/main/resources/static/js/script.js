window.addEventListener("click", (event) => {
    let dialogBackground = document.getElementById("dialogBackground");
    if (dialogBackground == null) return;

    if (event.target == dialogBackground) {
        closeAllDialogs();
    }
});

function openRegistrationDialog() {
    dialogBackground.style.display = "block";

    registrationDialog.style.display = "block";
}

function saveChanges() {
    closeAllDialogs();
}

function closeAllDialogs() {
    dialogBackground.style.display = "none";

    registrationDialog.style.display = "none";
}