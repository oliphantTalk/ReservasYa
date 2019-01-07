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