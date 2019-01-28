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
