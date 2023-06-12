
function renderPrice(data) {
    return addZeroes(Math.round(data * 100) / 100) + " €";
}

function addZeroes(num) {
    return num.toLocaleString("fr", {useGrouping: false, minimumFractionDigits: 2})
}

function renderTotalPrice(priceUnit, quantity) {
    return renderPrice(Number(priceUnit * quantity));
}