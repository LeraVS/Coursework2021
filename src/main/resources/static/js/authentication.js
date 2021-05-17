const signInBtn = document.getElementById("signInBtn");
const signUpBtn = document.getElementById("signUpBtn");
const container = document.querySelector(".container");
const errorMessage = document.querySelector(".error-message");
const signUp = document.querySelector('#confirmSignUpBtn');
const entrance = document.querySelector('#confirmSignInBtn');

errorMessage.addEventListener('click', hide);

signUp.addEventListener('click', saveAtLocalStorage);
entrance.addEventListener('click', saveAtLocalStorage);

function hide(){
    errorMessage.style.display = 'none';
}

function saveAtLocalStorage(e){
    e.preventDefault();
    if(e.target===signUp){

        localStorage.setItem('login',document.querySelector('#emailInput').value)
        document.forms[1].submit()
    }else if(e.target===entrance){

        localStorage.setItem('login',document.querySelector('#signInEmail').value)
        document.forms[0].submit()
    }

}