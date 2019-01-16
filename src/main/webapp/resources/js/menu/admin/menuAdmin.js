const localParamsPanel = document.getElementById("localParams-panel");
const addUserPanel = document.getElementById("addUser-panel");
const editUserPanel = document.getElementById("editUser-panel");
const removeUserPanel = document.getElementById("removeUser-panel");
const addEntityPanel = document.getElementById("addEntity-panel");
const editEntityPanel = document.getElementById("editEntity-panel");
const removeEntityPanel = document.getElementById("removeEntity-panel");
const addFlightPanel = document.getElementById("addFlight-panel");
const editFlightPanel = document.getElementById("editFlight-panel");
const removeFlightPanel = document.getElementById("removeFlight-panel");
const addRoomPanel = document.getElementById("addRoom-panel");
const editRoomPanel = document.getElementById("editRoom-panel");
const removeRoomPanel = document.getElementById("removeRoom-panel");
const addCarPanel = document.getElementById("addCar-panel");
const editCarPanel = document.getElementById("editCar-panel");
const removeCarPanel = document.getElementById("removeCar-panel");
/*const addPanel = document.getElementById("add-panel");
const editPanel = document.getElementById("edit-panel");
const removePanel = document.getElementById("remove-panel");*/

const elementos = [localParamsPanel, addUserPanel, addEntityPanel,
    addFlightPanel, addRoomPanel, addCarPanel, editUserPanel, editEntityPanel,
    editFlightPanel, editRoomPanel, editCarPanel, removeUserPanel,
    removeEntityPanel, removeFlightPanel, removeRoomPanel, removeCarPanel];

document.getElementById("local-params").addEventListener("click", displayFragment(localParamsPanel, elementos).display);
document.getElementById("add-user").addEventListener("click", displayFragment(addUserPanel, elementos).display);
document.getElementById("add-entity").addEventListener("click", displayFragment(addEntityPanel, elementos).display);
document.getElementById("add-flight").addEventListener("click", displayFragment(addFlightPanel, elementos).display);
document.getElementById("add-room").addEventListener("click", displayFragment(addRoomPanel, elementos).display);
document.getElementById("add-car").addEventListener("click", displayFragment(addCarPanel, elementos).display);
document.getElementById("edit-user").addEventListener("click", displayFragment(editUserPanel, elementos).display);
document.getElementById("edit-entity").addEventListener("click", displayFragment(editEntityPanel, elementos).display);
document.getElementById("edit-flight").addEventListener("click", displayFragment(editFlightPanel, elementos).display);
document.getElementById("edit-room").addEventListener("click", displayFragment(editRoomPanel, elementos).display);
document.getElementById("edit-car").addEventListener("click", displayFragment(editCarPanel, elementos).display);
document.getElementById("remove-user").addEventListener("click", displayFragment(removeUserPanel, elementos).display);
document.getElementById("remove-entity").addEventListener("click", displayFragment(removeEntityPanel, elementos).display);
document.getElementById("remove-flight").addEventListener("click", displayFragment(removeFlightPanel, elementos).display);
document.getElementById("remove-room").addEventListener("click", displayFragment(removeRoomPanel, elementos).display);
document.getElementById("remove-car").addEventListener("click", displayFragment(removeCarPanel, elementos).display);
