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