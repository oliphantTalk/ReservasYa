
const fsb = document.getElementById("fly-searchbox");
const csb = document.getElementById("car-searchbox");
const hsb = document.getElementById("hotel-searchbox");

const elementos = [fsb, csb, hsb];

document.getElementById("link-autos").addEventListener("click", displayFragment(csb, elementos).display);
document.getElementById("link-hoteles").addEventListener("click", displayFragment(hsb, elementos).display);
document.getElementById("link-vuelos").addEventListener("click", displayFragment(fsb, elementos).display);

