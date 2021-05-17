// Close the dropdown if the user clicks outside of it
window.addEventListener("click", (event) => {
    let dropdowns = document.getElementsByClassName("dropdown-content");
    if (!dropdowns.contains(event.target)) {
        hideDropdowns();
    }
});

signInBtn.onclick = function () {
    hideDropdowns("signInDropdown");
    document.getElementById("signInDropdown").classList.toggle("dropdownShow");
}

function hideDropdowns(exception) {
    let dropdowns = document.getElementsByClassName("dropdown-content");

    let i;
    for (i = 0; i < dropdowns.length; i++) {
        let openDropdown = dropdowns[i];

        if (openDropdown.classList.contains('dropdownShow') && openDropdown.id !== exception) {
            openDropdown.classList.remove('dropdownShow');
        }
    }
}
