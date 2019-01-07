let myCheckButton = {}
myCheckButton = (function() {

    const checkButton = function(e) {
        e.preventDefault();
        let ok = true;
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
        if (hasInvalidClass(flyFromInput) || hasInvalidClass(flyToInput) || hasInvalidClass(dateGoInput) || hasInvalidClass(dateReturnInput)) {
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
            console.log(url);
            fetch(url)
                .then(response => response.json())
                .then(json => renderMarkup(json, isReturnNecessary))
                .catch(err => {
                    disableLoader();
                    divClusters.innerHTML = renderSystemError("Fuera de red. ", "Cheque su conexion");
                    console.log(err);
                })

        }
    }

    return {
        checkButton: checkButton
    };
});