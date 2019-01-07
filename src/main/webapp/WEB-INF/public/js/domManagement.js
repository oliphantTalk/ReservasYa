function addInvalidClass(element) {
    element.classList.add("-invalid");
}

function removeInvalidClass(element) {
    element.classList.remove('-invalid');
}

function hasInvalidClass(element) {
    return element.classList.contains('-invalid');
}



function showValidationMsg(element, msg) {
    element.innerHTML = msg;
}

function disableElement(element) {
    element.disabled = true;
}

function enableElement(element) {
    element.disabled = false;
}

function focusIn(input, tag) {
    input.addEventListener("focusin", function() {
        removeInvalidClass(tag);
    });
}