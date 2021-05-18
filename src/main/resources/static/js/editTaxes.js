function deleteTaxUnderSimplifiedSystem(taxId, index) {
    let url = '/admin/taxRatesUnderSimplifiedSystem/delete';
    return fetch(url + '/' + taxId, {
        method: 'delete'
    }).then(response => deleteFromDOM(response, index))
}

function deleteFromDOM(response, index) {
    let urlParts = response.url.split('/');
    let taxId = urlParts[6];
    let divs = document.querySelectorAll('.taxes tr');
    let currentElement;
    console.log(taxId);
    for (let i = 0; i < divs.length; i++) {
        let button = divs[i].children[4];
        console.log(button.value);
        if (button.value === taxId) {
            currentElement = divs[i];
            break;
        }
    }
    currentElement.remove();
}

var tx = document.getElementsByTagName('textarea');

for (var i = 0; i < tx.length; i++) {

    tx[i].setAttribute('style', 'height:' + (tx[i].scrollHeight) + 'px;overflow-y:hidden;');

    tx[i].addEventListener("input", OnInput, false);

}

function OnInput() {

    this.style.height = 'auto';

    this.style.height = (this.scrollHeight) + 'px';

}