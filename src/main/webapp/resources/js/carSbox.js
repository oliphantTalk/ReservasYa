let carFrom = document.getElementById("car-from");
let carTo = document.getElementById("car-to");

function fillC(){
    let select = document.getElementById("sCarId");
    let carText = select.options[select.selectedIndex].text.split(",");
    carFrom.value = "";
    carTo.value = "";
    if(carText.length > 1) {
        carFrom.value = carText[1].split("=")[1];
        carTo.value = "CUALQUIER CIUDAD";
    }
}

let btnCompleteC = document.getElementById("btn-complete-car");
btnCompleteC.addEventListener("click", function () {fillC()});