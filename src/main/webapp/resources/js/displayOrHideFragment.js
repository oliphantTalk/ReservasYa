let displayFragment = {};
displayFragment = (function(fragment, elem) {
    const display = function display() {
        elem.forEach(function(value) {
            hideFragment(value);
            if(fragment.id === value.id){
                showFragment(value)
            }
        })
    };
    return {
        display: display
    }
});

function hideFragment(fragment) {
    fragment.style.display = "none";
}

function showFragment(fragment) {
    fragment.style.display = "inherit";
}