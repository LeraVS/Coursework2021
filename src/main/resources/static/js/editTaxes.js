function deleteTaxUnderSimplifiedSystem(taxId, index) {
    let url = '/admin/taxRatesUnderSimplifiedSystem/delete';
    return fetch(url + '/' + taxId, {
        method: 'delete'
    }).then(response => {
        window.location.reload(true);
    })
}

function deleteIncomeTax(taxId, index) {
    let url = '/admin/incomeTaxRates/delete';
    return fetch(url + '/' + taxId, {
        method: 'delete'
    }).then(response => {
        window.location.reload(true);
    })
}

function deleteSingleTax(taxId, index) {
    let url = '/admin/singleTaxRates/delete';
    return fetch(url + '/' + taxId, {
        method: 'delete'
    }).then(response => {
        window.location.reload(true);
    })
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