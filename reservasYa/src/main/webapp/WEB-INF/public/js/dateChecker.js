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


function parseDate(dateString) {
    return dateString.split("/").reverse().join("/");
}

function parseDateBusquets(dateString) {
    return (new Date(dateString.split("/").reverse().join("/"))).toISOString().slice(0, 10);
}

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

module.exports = {
    validFormatDate: validFormatDate,
    isNotBisiestoYear: isNotBisiestoYear,
    isActualDate: isActualDate,
    dateBeforeAnother: dateBeforeAnother,
    dateBeforeNextYear: dateBeforeNextYear
}