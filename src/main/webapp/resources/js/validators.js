let myCheckIATA = {}
myCheckIATA = (function(id) {
    const input = document.getElementById(id);
    const iataReg = /^[A-Z]{3}$/i;
    const checkIata = function checkIata() {
        const value = input.value;
        if (value !== "") {
            if (value.match(iataReg)) {
                removeInvalidClass(document.getElementById(id + '-input'));
                input.value = value.toUpperCase();
            } else {
                addInvalidClass(document.getElementById(id + '-input'));
                showValidationMsg(document.getElementById(id + '-msg'), "Se requieren 3 letras");
            }
        }
    }
    return {
        checkIata: checkIata
    }
});


let myCheckDate = {};
myCheckDate = (function() {
    const dateIsValid = function dateIsValid() {
        const date = parseDate(dateGo.value);
        const dateTo = parseDate(dateReturn.value);
        if (date !== "") {
            addInvalidClass(dateGoInput);
            if (!validFormatDate(date)) {
                showValidationMsg(dateGoMsg, "Ingresa una fecha valida");
            } else if (isNotBisiestoYear(date)) {
                showValidationMsg(dateGoMsg, "Fecha invalida, no es año bisiesto");
            } else if (!isActualDate(date)) {
                showValidationMsg(dateGoMsg, "La fecha ingresada es pasado!");
            } else if (!dateBeforeNextYear(date)) {
                showValidationMsg(dateGoMsg, "La fecha ingresada supera el año!");
            } else {
                removeInvalidClass(dateGoInput);
            }
        }
        if (dateTo !== "") {
            addInvalidClass(dateReturnInput);
            if (!validFormatDate(dateTo)) {
                showValidationMsg(dateReturnMsg, "Ingresa una fecha valida");
            } else if ((isNotBisiestoYear(dateTo))) {
                showValidationMsg(dateReturnMsg, "Fecha invalida, no es año bisiesto");
            } else if (!isActualDate(dateTo)) {
                showValidationMsg(dateReturnMsg, "La fecha ingresada es pasado!");
            } else if (!dateBeforeNextYear(dateTo)) {
                showValidationMsg(dateReturnMsg, "La fecha ingresada supera el año!");
            } else if (date !== "" && !dateBeforeAnother(date, dateTo)) {
                showValidationMsg(dateReturnMsg, "Fecha destino anterior a origen");
            } else {
                removeInvalidClass(dateReturnInput);
            }
        }
    }
    return {
        dateIsValid: dateIsValid,
    }
});

let myCheckUnknownDates = {}
myCheckUnknownDates = (function() {
    const checkbox = dontKnowDate;
    const checkU = function() {
        if (checkbox.checked) {
            disableElement(dateGo);
            removeInvalidClass(dateGoInput);
            disableElement(dateReturn);
            removeInvalidClass(dateReturnInput);
            disableElement(selectPassenger);
        } else {
            enableElement(dateGo);
            enableElement(selectPassenger);
            if (!wayGo.checked) {
                enableElement(dateReturn);
            }
        }
    }
    return {
        checkU: checkU
    }
});

let myCheckFlyWay = {}
myCheckFlyWay = (function(id) {
    const radio = document.getElementById(id);
    const checkFlyWay = function() {
        const id = radio.id;
        if (!dontKnowDate.checked) {
            switch (id) {
                case "fly-way-goReturn":
                case "fly-way-multi":
                    enableElement(dateGo);
                    enableElement(dateReturn);
                    enableElement(selectPassenger);
                    break;

                case "fly-way-go":
                    enableElement(dateGo);
                    disableElement(dateReturn);
                    dateReturn.value = "";
                    removeInvalidClass(dateReturnInput);
                    enableElement(selectPassenger);
                    break;

                default:
                    break;
            }

        }
    }
    return {
        checkFlyWay: checkFlyWay
    }
});

let myCheckButton = {}
myCheckButton = (function() {
    const ok = true;
    if (flyFrom.value === "" && !flyFrom.disabled) {
        addInvalidClass(flyFromInput);
        showValidationMsg(flyFromMsg, "Debes completar este campo");
        ok = false;
    }
    if (flyTo.value === "" && !flyTo.disabled) {
        addInvalidClass(flyToInput);
        showValidationMsg(flyToMsg, "Debes completar este campo");
        ok = false;
    }
    if (dateGo.value === "" && !dateGo.disabled) {
        addInvalidClass(dateGoInput);
        showValidationMsg(dateGoMsg, "Debes completar este campo");
        ok = false;
    }
    if (dateReturn.value === "" && !dateReturn.disabled) {
        addInvalidClass(dateReturnInput);
        showValidationMsg(dateReturnMsg, "Debes completar este campo");
        ok = false;
    }
    if (ok) {
        renderLoader();
        let returnDateParam = "";
        let isReturnNecessary = false;
        const from = flyFrom.value;
        const to = flyTo.value;
        const departureDate = parseDateBusquets(dateGo.value);
        const adults = selectPassenger.value;
        if (dateReturn.value !== "") {
            const returnDate = parseDateBusquets(dateReturn.value);
            returnDateParam = `/returnDate=${returnDate}`
            isReturnNecessary = true;
        }
        const url = `http://localhost:3200/from=${from}/to=${to}/departureDate=${departureDate}${returnDateParam}/adults=${adults}`;
        fetch(url)
            .then(response => response.json())
            .then(json => renderMarkup(json, isReturnNecessary))
            .catch(err => console.log(err))
    }
    return ok;
});

function validFormatDate(date) {
    const date_regex = /^(19|20)\d{2}\/([1-9]|0[1-9]|1[0-2])\/([1-9]|0[1-9]|1\d|2\d|3[01])$/;
    return (date_regex.test(date));
}

function isNotBisiestoYear(date) {
    const dateYear = parseInt(date.split("/")[0]);
    const dateMonth = parseInt(date.split("/")[1]);
    const dateDay = parseInt(date.split("/")[2]);
    return (!((dateYear % 4 == 0) && ((dateYear % 100 != 0) || (dateYear % 400 == 0))) && dateDay == 29 && dateMonth == 2);
}

function isActualDate(date) {
    return (new Date() <= new Date(date));
}

function dateBeforeNextYear(date) {
    return (new Date(new Date().setFullYear(new Date().getFullYear() + 1)) >= new Date(date));
}

function dateBeforeAnother(date, otherDate) {
    return (new Date(date) <= new Date(otherDate));
}

function addInvalidClass(element) {
    element.classList.add("-invalid");
}

function removeInvalidClass(element) {
    element.classList.remove('-invalid');
}

function parseDate(dateString) {
    return dateString.split("/").reverse().join("/");
}

function parseDateBusquets(dateString) {
    return (new Date(dateString.split("/").reverse().join("/"))).toISOString().slice(0, 10);
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
module.exports = {
    validFormatDate: validFormatDate,
    isNotBisiestoYear: isNotBisiestoYear,
    isActualDate: isActualDate,
    dateBeforeNextYear: dateBeforeNextYear,
    dateBeforeAnother: dateBeforeAnother
}