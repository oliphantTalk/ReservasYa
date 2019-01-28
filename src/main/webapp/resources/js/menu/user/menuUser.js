const editUserSettings = document.getElementById("editProfile-panel");
const removeUserAccount = document.getElementById("removeProfile-panel");
const elementos2 = [editUserSettings, removeUserAccount];
document.getElementById("edit-profile").addEventListener("click", displayFragment(editUserSettings, elementos2).display);
document.getElementById("remove-profile").addEventListener("click", displayFragment(removeUserAccount, elementos2).display);