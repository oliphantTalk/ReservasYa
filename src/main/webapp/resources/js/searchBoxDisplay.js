
const fsb = document.getElementById("fly-searchbox");
const csb = document.getElementById("car-searchbox");
const hsb = document.getElementById("hotel-searchbox");

const elementos = [fsb, csb, hsb];

let linkCar = document.getElementById("link-autos");
let linkHotel = document.getElementById("link-hoteles");
let linkFly = document.getElementById("link-vuelos");
if(linkCar != null) {
    linkCar.addEventListener("click", displayFragment(csb, elementos).display);
}
if(linkHotel != null){
    linkHotel.addEventListener("click", displayFragment(hsb, elementos).display);
}
if(linkFly != null) {
    linkFly.addEventListener("click", displayFragment(fsb, elementos).display);
}

