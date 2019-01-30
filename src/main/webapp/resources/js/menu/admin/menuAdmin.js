let localParamsPanel = document.getElementById("localParams-panel");
let addUserPanel = document.getElementById("addUser-panel");
let editUserPanel = document.getElementById("editUser-panel");
let removeUserPanel = document.getElementById("removeUser-panel");
let addAirlinePanel = document.getElementById("addAirline-panel");
let editAirlinePanel = document.getElementById("editAirline-panel");
let removeAirlinePanel = document.getElementById("removeAirline-panel");
let addAgencyPanel = document.getElementById("addAgency-panel");
let editAgencyPanel = document.getElementById("editAgency-panel");
let removeAgencyPanel = document.getElementById("removeAgency-panel");
let addHotelPanel = document.getElementById("addHotel-panel");
let editHotelPanel = document.getElementById("editHotel-panel");
let removeHotelPanel = document.getElementById("removeHotel-panel");
let addFlightPanel = document.getElementById("addFlight-panel");
let removeFlightPanel = document.getElementById("removeFlight-panel");
let addRoomPanel = document.getElementById("addRoom-panel");
let removeRoomPanel = document.getElementById("removeRoom-panel");
let addCarPanel = document.getElementById("addCar-panel");
let removeCarPanel = document.getElementById("removeCar-panel");

let elementos = [addAirlinePanel, addAgencyPanel, addHotelPanel,
      editAirlinePanel, editAgencyPanel, editHotelPanel,
      addFlightPanel, removeFlightPanel, addCarPanel, removeCarPanel, addRoomPanel, removeRoomPanel,
    removeAirlinePanel, removeAgencyPanel, removeHotelPanel];

let localParams = document.getElementById("local-params")
if(localParams != null) {
    elementos.push(localParamsPanel);
    localParams.addEventListener("click", displayFragment(localParamsPanel, elementos).display);
}
let addUser = document.getElementById("add-user");
if(addUser != null) {
    elementos.push(addUserPanel);
    addUser.addEventListener("click", displayFragment(addUserPanel, elementos).display);
}
document.getElementById("add-airline").addEventListener("click", displayFragment(addAirlinePanel, elementos).display);
document.getElementById("add-agency").addEventListener("click", displayFragment(addAgencyPanel, elementos).display);
document.getElementById("add-hotel").addEventListener("click", displayFragment(addHotelPanel, elementos).display);
document.getElementById("add-flight").addEventListener("click", displayFragment(addFlightPanel, elementos).display);
document.getElementById("add-room").addEventListener("click", displayFragment(addRoomPanel, elementos).display);
document.getElementById("add-car").addEventListener("click", displayFragment(addCarPanel, elementos).display);
let editUser = document.getElementById("edit-user")
if(editUser != null) {
    elementos.push(editUserPanel);
    editUser.addEventListener("click", displayFragment(editUserPanel, elementos).display);
}
document.getElementById("edit-agency").addEventListener("click", displayFragment(editAgencyPanel, elementos).display);
document.getElementById("edit-hotel").addEventListener("click", displayFragment(editHotelPanel, elementos).display);
document.getElementById("edit-airline").addEventListener("click", displayFragment(editAirlinePanel, elementos).display);
let deleteUser = document.getElementById("remove-user");
if(deleteUser != null) {
    elementos.push(removeUserPanel);
    deleteUser.addEventListener("click", displayFragment(removeUserPanel, elementos).display);
}
document.getElementById("remove-agency").addEventListener("click", displayFragment(removeAgencyPanel, elementos).display);
document.getElementById("remove-hotel").addEventListener("click", displayFragment(removeHotelPanel, elementos).display);
document.getElementById("remove-airline").addEventListener("click", displayFragment(removeAirlinePanel, elementos).display);
document.getElementById("remove-flight").addEventListener("click", displayFragment(removeFlightPanel, elementos).display);
document.getElementById("remove-room").addEventListener("click", displayFragment(removeRoomPanel, elementos).display);
document.getElementById("remove-car").addEventListener("click", displayFragment(removeCarPanel, elementos).display);
