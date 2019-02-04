const fFrom = document.getElementById("fly-from");
const fTo = document.getElementById("fly-to");
const dateGo = document.getElementById("fly-dateGo");
const dateReturn = document.getElementById("fly-dateReturn");
const selectPassenger = document.getElementById("select-passenger");
function disableElement(element) {
    element.disabled = true;
}

function enableElement(element) {
    element.disabled = false;
}
let myCheckFlyWay = {}
myCheckFlyWay = (function(id) {
    const radio = document.getElementById(id);
    const checkFlyWay = function() {
        const id = radio.id;
        switch (id) {
            case "fly-way-goReturn":
                enableElement(dateGo);
                enableElement(dateReturn);
                enableElement(selectPassenger);
                break;

            case "fly-way-go":
                enableElement(dateGo);
                disableElement(dateReturn);
                dateReturn.value = "";
                enableElement(selectPassenger);
                break;

            default:
                break;
        }
    }
    return {
        checkFlyWay: checkFlyWay
    }
});
let wayGoReturn = document.getElementById("fly-way-goReturn");
let wayGo = document.getElementById("fly-way-go");
wayGoReturn.addEventListener("click", myCheckFlyWay("fly-way-goReturn").checkFlyWay);
wayGo.addEventListener("click", myCheckFlyWay("fly-way-go").checkFlyWay);

function fillF(){
    let select = document.getElementById("sflightId");
    let flyText = select.options[select.selectedIndex].text.split(",");
    fFrom.value = "";
    fTo.value = "";
    dateGo.value = "";
    dateReturn.value = "";
    if(flyText.length > 1) {
        fFrom.value = flyText[0].split("=")[1];
        fTo.value = flyText[1].split("=")[1];
        dateGo.value = flyText[2].split("=")[1];
        dateReturn.value = flyText[3].split("=")[1];
    }
}

let btnCompleteF = document.getElementById("btn-complete-fly");
btnCompleteF.addEventListener("click", function () {fillF()});