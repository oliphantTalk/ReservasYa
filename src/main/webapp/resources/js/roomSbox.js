let roomTo = document.getElementById("hotel-to");

function fillR(){
    let select = document.getElementById("sRoomId");
    let roomText = select.options[select.selectedIndex].text.split(",");
    roomTo.value = "";
    if(roomText.length > 1) {
        roomTo.value = roomText[1].split("=")[1];
    }
}

let btnCompleteR = document.getElementById("btn-complete-room");
btnCompleteR.addEventListener("click", function () {fillR()});