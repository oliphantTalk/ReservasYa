const localParamsPanel = document.getElementById("localParams-panel");
const addUserPanel = document.getElementById("addUser-panel");
const editUserPanel = document.getElementById("editUser-panel");
const removeUserPanel = document.getElementById("removeUser-panel");
const addAirlinePanel = document.getElementById("addAirline-panel");
const editAirlinePanel = document.getElementById("editAirline-panel");
const removeAirlinePanel = document.getElementById("removeAirline-panel");
const addAgencyPanel = document.getElementById("addAgency-panel");
const editAgencyPanel = document.getElementById("editAgency-panel");
const removeAgencyPanel = document.getElementById("removeAgency-panel");
const addHotelPanel = document.getElementById("addHotel-panel");
const editHotelPanel = document.getElementById("editHotel-panel");
const removeHotelPanel = document.getElementById("removeHotel-panel");

const addFlightPanel = document.getElementById("addFlight-panel");
//const editFlightPanel = document.getElementById("editFlight-panel");
//const removeFlightPanel = document.getElementById("removeFlight-panel");
const addRoomPanel = document.getElementById("addRoom-panel");
//const editRoomPanel = document.getElementById("editRoom-panel");
//const removeRoomPanel = document.getElementById("removeRoom-panel");
const addCarPanel = document.getElementById("addCar-panel");
//const editCarPanel = document.getElementById("editCar-panel");
//const removeCarPanel = document.getElementById("removeCar-panel");

//addFlightPanel, addRoomPanel, addCarPanel,editFlightPanel, editRoomPanel, editCarPanel,removeFlightPanel, removeRoomPanel, removeCarPanel
const elementos = [localParamsPanel, addUserPanel, addAirlinePanel, addAgencyPanel, addHotelPanel,
     editUserPanel, editAirlinePanel, editAgencyPanel, editHotelPanel,
     removeUserPanel, addFlightPanel,
    removeAirlinePanel, removeAgencyPanel, removeHotelPanel, ];

document.getElementById("local-params").addEventListener("click", displayFragment(localParamsPanel, elementos).display);
document.getElementById("add-user").addEventListener("click", displayFragment(addUserPanel, elementos).display);
document.getElementById("add-airline").addEventListener("click", displayFragment(addAirlinePanel, elementos).display);
document.getElementById("add-agency").addEventListener("click", displayFragment(addAgencyPanel, elementos).display);
document.getElementById("add-hotel").addEventListener("click", displayFragment(addHotelPanel, elementos).display);
document.getElementById("add-flight").addEventListener("click", displayFragment(addFlightPanel, elementos).display);
/*document.getElementById("add-room").addEventListener("click", displayFragment(addRoomPanel, elementos).display);
document.getElementById("add-car").addEventListener("click", displayFragment(addCarPanel, elementos).display);*/
document.getElementById("edit-user").addEventListener("click", displayFragment(editUserPanel, elementos).display);
document.getElementById("edit-agency").addEventListener("click", displayFragment(editAgencyPanel, elementos).display);
document.getElementById("edit-hotel").addEventListener("click", displayFragment(editHotelPanel, elementos).display);
document.getElementById("edit-airline").addEventListener("click", displayFragment(editAirlinePanel, elementos).display);
/*document.getElementById("edit-flight").addEventListener("click", displayFragment(editFlightPanel, elementos).display);
document.getElementById("edit-room").addEventListener("click", displayFragment(editRoomPanel, elementos).display);
document.getElementById("edit-car").addEventListener("click", displayFragment(editCarPanel, elementos).display);*/
document.getElementById("remove-user").addEventListener("click", displayFragment(removeUserPanel, elementos).display);
document.getElementById("remove-agency").addEventListener("click", displayFragment(removeAgencyPanel, elementos).display);
document.getElementById("remove-hotel").addEventListener("click", displayFragment(removeHotelPanel, elementos).display);
document.getElementById("remove-airline").addEventListener("click", displayFragment(removeAirlinePanel, elementos).display);
/*document.getElementById("remove-flight").addEventListener("click", displayFragment(removeFlightPanel, elementos).display);
document.getElementById("remove-room").addEventListener("click", displayFragment(removeRoomPanel, elementos).display);
document.getElementById("remove-car").addEventListener("click", displayFragment(removeCarPanel, elementos).display);*/
