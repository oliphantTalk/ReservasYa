function renderMessage400() {
    return `<div class="eva-3-message -warning"> 
  <div class="message-icon-container"><i class="message-icon eva-3-icon-info-circle"></i></div> 
  <div class="message-header"> 
    <h5 class="message-title eva-3-h5">No hemos podido interpretar la ciudad a la que quiere volar.</h5> 
  </div> 
  <div class="message-body">
    <p class="message-text eva-3-p"> Verifique que el nombre de la ciudad esté bien escrito. Realice una nueva búsqueda a un destino cercano o a una ciudad más importante. </p> 
  </div> 
</div>
<div style='display:none' id="error400" class="error400"></div>`
}

function renderMessage430() {
    return `<div class="eva-3-message -warning"> 
  <div class="message-icon-container"><i class="message-icon eva-3-icon-info-circle"></i></div> 
  <div class="message-header"> 
    <h5 class="message-title eva-3-h5">Ciudad destino igual a origen</h5> 
  </div> 
  <div class="message-body">
    <p class="message-text eva-3-p"> No puede volarse a la misma ciudad de salida</p> 
  </div> 
</div><div style='display:none' id="error430" class="error430"></div>`
}

function renderMessageNoFly() {
    return `<div class="eva-3-message -info"> 
    <div class="message-icon-container"><i class="message-icon eva-3-icon-info-circle"></i></div> 
    <div class="message-header"> 
      <h5 class="message-title eva-3-h5">¡Ups! No encontramos vuelos a ${flyTo.value}</h5> 
    </div> 
    <div class="message-body"> 
      <p class="message-text eva-3-p">Puede que no haya lugar en los vuelos o que no haya vuelos en esos días.

      Puedes encontrar vuelos otro día o intentar la búsqueda en un aeropuerto cercano.</p> 
    </div> 
  </div><div style='display:none' id="errorNoFly" class="errorNoFly"></div>`
}

function renderSystemError(msg, submsg) {
    return `<div class="eva-3-message -error"> 
    <div class="message-icon-container"><i class="message-icon eva-3-icon-info-circle"></i></div> 
    <div class="message-header"> 
      <h5 class="message-title eva-3-h5">${msg}</h5> 
    </div> 
    <div class="message-body"> 
      <p class="message-text eva-3-p">${submsg}</p> 
    </div> 
  </div><div style='display:none' id="error500" class="error500"></div>`
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function flyDaysDifference(string) {
    if (string === 0) {
        return "";
    }
    return `+${string}`;
}

function flyTotalDuration(string) {
    let time = string.split(":");
    time[0] += 'h';
    time[1] += 'm';
    return time.join(" ");
}

function priceWithPoint(amount) {

    return amount.slice(0, amount.length - 3) + '.' + amount.toString().slice(amount.length - 3, amount.length)
}

function checkAirportChange(route, i) {
    if (route.segments[i].segmentConnectionType == "AIRPORT_CHANGE") {
        return "blue";
    }
    return "white";
}

function renderScales(route) {
    const stopsCount = route.stopsCount;
    let scales = `${stopsCount} escalas`;
    let evaColor = `-blue`;
    let lineColor = `-blue-line`;
    //let divScaleCircle = "";
    switch (stopsCount) {
        case 0:
            evaColor = "-green";
            scales = "Directo";
            lineColor = "-green-line";
            break;
        case 1:
            scales = "1 escala";
            //divScaleCircle = `<div class="border-btn-circle border-btn-1-circle border-btn-bgr-${checkAirportChange(route, 0)}"></div>`
            break;
        case 2:
            //divScaleCircle = `<div class="border-btn-circle border-btn-2-circle-left border-btn-bgr-${checkAirportChange(route, 0)}"></div> 
            //<div class="border-btn-circle border-btn-2-circle-right border-btn-bgr-${checkAirportChange(route, 1)}"></div>`
            break;
        case 3:
            //divScaleCircle = `<div class="border-btn-circle border-btn-3-circle-left border-btn-bgr-${checkAirportChange(route, 0)}"></div>
            //<div class="border-btn-circle border-btn-3-circle-center border-btn-bgr-${checkAirportChange(route, 1)}"></div>
            //<div class="border-btn-circle border-btn-3-circle-right border-btn-bgr-${checkAirportChange(route, 2)}"></div>`
            break;
        default:
            break;
    }
    //<div class="stops-circle"></div>
    return `<div class="-fly-way-line ${evaColor}">${scales}</div>
                    
                    <hr class="${lineColor}" onclick="showScaleInfo(event, this, '${encodeURIComponent(JSON.stringify(route))}')">
                    
                `
        //${divScaleCircle}`
}

function showScaleInfo(event, dom, routeString) {
    event.preventDefault();
    const route = JSON.parse(decodeURIComponent(routeString))
    console.log(routeString)

    console.log(route)
    debugger;


}

function handbagAvailable(baggageInfo) {
    const quantity = baggageInfo.quantity;
    const handbagType = baggageInfo.handbagType;
    let html = "";
    if (handbagType == "INCLUDED") {
        html += `<span class="baggage-img">
                    <i class="-eva-3-icon-sm eva-3-icon-handbag"></i>
                </span>`
    } else {
        html += `<span class="baggage-img-grey">
                    <i class="-eva-3-icon-sm eva-3-icon-handbag"></i>
                </span>`
    }
    return html;
}

function singlebagAvailable(baggageInfo) {
    const quantity = baggageInfo.quantity;
    const type = baggageInfo.type;
    let html = "";
    if (quantity > 0 && type == "INCLUDED") {
        html += `<span class="baggage-img">
                    <i class="-eva-3-icon-sm eva-3-icon-single"></i>
                </span>`
    } else {
        html += `<span class="baggage-img-grey">
                    <i class="-eva-3-icon-sm eva-3-icon-single"></i>
                </span>`
    }
    return html;
}

function renderContainerBegin() {
    return `<div class="cluster-container cluster-white-background">`;
}

function renderContainerEnd() {
    disableLoader();
    return `</div>`;
}

function renderClusterBodyBegin() {
    return `<div class="cluster-content">
                <div class="cluster-description fix-eva-heigth">`;
}

function renderClusterBodyEnd() {
    return `</div></div><div class='no-error' id='no-error'></div>`
}

function renderHeaderGo(referenceData) {
    return ` 
<div class="eva-3-row -no-gutter -eva-3-center">
    <div class="col -sm-12 -md-12">
        <div class="eva-3-row -no-gutter">
            <div class="col -sm-12 -md-3 max-width-label">
                <div class="fly-way-header" id="fly-way-go-cluster">
                    <span class="fly-way-wrapper">
                        <span class="fly-way" >
                            <i class="eva-3-icon-airplane-going"></i>
                            IDA
                        </span>

                        <span class="fly-way">${capitalizeFirstLetter(moment(dateGo.value.split("/").reverse().join("-")).locale('es').format("ddd D MMM YY"))}</span>
                    </span>
                </div>
            </div>

            <div class="col -md-3">
                <div class="fly-way-header -padding-air-info">
                        <span class="fly-departure -eva-3-hide-small">
                            <span class="fly-info-airport">${flyFrom.value.toUpperCase()}</span>

                            <span class="fly-info-city">${referenceData.cities[flyFrom.value.toUpperCase()]}</span>
                        </span>
                </div>
            </div>

            <div class="col -md-6">
                <div class="fly-way-header -padding-air-info">
                        <span class="fly-arrival -eva-3-hide-small">
                        <span class="fly-info-airport">${flyTo.value.toUpperCase()}</span>

                        <span class="fly-info-city">${referenceData.cities[flyTo.value.toUpperCase()]}</span>
                        </span>
                </div>
            </div>
        </div> `

}

function renderHeaderReturn(referenceData) {
    return `
            <div class="eva-3-row -no-gutter -eva-3-center">
                <div class="col -sm-12 -md-12">
                    <div class="eva-3-row -no-gutter">
                        <div class="col -sm-12 -md-3 max-width-label">
                            <div class="fly-way-header" id="fly-way-return-cluster">
                                <span class="fly-way-wrapper">
                                    <span class="fly-way" >
                                        <i class="eva-3-icon-airplane-return"></i>
                                        VUELTA
                                    </span>

                                    <span class="fly-way">${capitalizeFirstLetter(moment(dateReturn.value.split("/").reverse().join("-")).locale('es').format("ddd D MMM YY"))}</span>
                                </span>
                            </div>
                        </div>

                        <div class="col -md-3">
                            <div class="fly-way-header -padding-air-info">
                                
                                    <span class="fly-arrival -eva-3-hide-small">
                                        <span class="fly-info-airport">${flyTo.value.toUpperCase()}</span>

                                        <span class="fly-info-city">${referenceData.cities[flyTo.value.toUpperCase()]}</span>
                                    </span>
                            </div>
                        </div>

                        <div class="col -md-6">
                            <div class="fly-way-header -padding-air-info">
                                    <span class="fly-departure -eva-3-hide-small">
                                    <span class="fly-info-airport">${flyFrom.value.toUpperCase()}</span>

                                    <span class="fly-info-city">${referenceData.cities[flyFrom.value.toUpperCase()]}</span>
                                    </span>
                            </div>
                        </div>
                    </div>`;
}

function renderItems(routes, referenceData, id) {
    let listBegin = `<ul>`;
    let checked = ' checked';
    for (let route of routes) {
        listBegin += `<li class="itinerary-container">
        
            <div class="eva-3-row -no-gutter  -eva-3-center">
                <div class="col -sm-1 -md-1 max-circle">
                    <span class="eva-3-radio">
                        <label class="radio-label-container">
                            <input
                                type="radio"
                                name="fly-way-${id}"
                                class="radio-tag"
                                ${checked}
                            ></input>

                            <i class="radio-circle no-margin-right"></i>
                        </label>
                    </span>
                </div>

                <div class="col -sm-9 -md-8">
                    <div class="eva-3-row -no-gutter padding-center">
                        <div class="col -sm-12 -md-2 min-width-label">
                            <div class="airline-spot">
                                <span class="airline-logo">
                                    <img
                                        src="https://ar.staticontent.com/flights-images/latest/common/airlines/25x25/${route.airlines[0]}.png"
                                        alt=""
                                    >
                                </span>

                                <span class="airline-name">${referenceData.airlines[route.airlines[0]]}</span>
                            </div>
                        </div>

                        <div class="col -sm-3 -md-2">
                            <span class="fly-departure -eva-3-hide-medium -eva-3-hide-large">
                                <span class="fly-info-airport">${route.departure.airportCode}</span>
                            </span>

                            <div class="fly-time">
                                <span class="time">${moment(route.departure.date).format('HH:mm')}</span>
                            </div>
                        </div>

                        <div class="col -sm-4 -md-2">
                            <div class="fly-way-connector">

                                ${renderScales(route)}
                                
                            </div>
                        </div>

                        <div class="col -sm-5 -md-3">
                            <span class="fly-departure -eva-3-hide-medium -eva-3-hide-large">
                                <span class="fly-info-airport">${route.arrival.airportCode}</span>
                            </span>

                            <div class="fly-time">
                                <span class="time">
                                ${moment(route.arrival.date).format('HH:mm')}

                                    <span class="plus-time">${flyDaysDifference(route.daysDifference)}</span>
                                </span>
                            </div>
                        </div>

                        <div class="col -sm-12 -md-2">
                            <span class="fly-duration">
                                <span class="time">
                                    <span class="-eva-3-hide-medium -eva-3-hide-large">Duracion:</span>

                                    ${flyTotalDuration(route.totalDuration)}
                                </span>
                            </span>
                        </div>
                    </div>
                </div>

                <div class="col -sm-2 -md-3">
                    <div class="eva-3-row -no-gutter">
                        <div class="col -sm-12">
                            <div class="icon-info-margin -eva-3-hide-medium -eva-3-hide-large">
                                <span class="icon-info-blue">
                                    <a class="info eva-3-icon-info-circle-outline"></a>
                                </span>
                            </div>
                        </div>

                        <div class="col -sm-12 -md-10 column-flex">
                            <span class="baggage">
                                <span class="baggage-item">
                                    ${handbagAvailable(route.baggageInfo)}
                                    ${singlebagAvailable(route.baggageInfo)}
                                </span>
                            </span>
                        </div>

                        <div class="col -md-2">
                            <div class="-eva-3-hide-small">
                                <span class="">
                                    <i class="-eva-3-icon-sm eva-3-icon-arrow-down"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        
    </li>`;
        checked = '';
    }
    listBegin += `</ul></div></div>`;
    return listBegin;
}

function renderPriceBox(cluster) {
    return `<!-- BEGIN PRICE-BOX -->
    <div class="cluster-pricebox-container fare-box">
        <div class="eva-3-row -no-gutter">
            <div class="col -sm-5 -md-12">
                <div class="fare-text-wrapper">
                    <div class="fare-legend">${cluster.priceDetail.mainFare.primaryMessage}</div>
                    ${cluster.priceDetail.mainFare.secondaryMessage != undefined ? 
                        `<div class="fare-legend -all-inclusive">${cluster.priceDetail.mainFare.secondaryMessage}</div>`
                        : ``
                    }
                    <div class="fare-price">
                        <em>
                            <span class="pesos-sign">$</span>
                            <span class="current-price">${priceWithPoint(cluster.priceDetail.mainFare.amount.toString())}</span>
                        </em>
                    </div>
                </div>
            </div>
            <div class="col -sm-1 -md-12">
            <div class="icon-info-price-margin -eva-3-hide-medium -eva-3-hide-large">
            <span class="icon-info-blue">
                <a class="info eva-3-icon-info-circle-outline"></a>
            </span>
        </div>            </div>
            <div class="col -sm-6 -md-12">
                <div class="fare-button-wrapper">
                    <div class="button-container">
                        <a class="eva-3-btn -primary -md">
                            <em class="btn-text">Comprar</em>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="eva-3-row -no-gutter">
            <div class="col -sm-12">
                <div class="payment-container">
                    <em class="payment-text">
                        <span class="payment-block">
                            <i class="-eva-3-icon-xsm eva-3-icon-card"></i>
                            ${cluster.paymentsInfo.paymentsMessage}
                        </span>
                        <span class="payment-show-banks">${cluster.paymentsInfo.morePaymentsMessage}</span>
                    </em>
                </div>
            </div>
        </div>
    </div>

    <!-- END PRICE-BOX -->
        `;
}

function renderLoader(){
    flyButton.disabled = true;
    document.getElementById('loaderSpot').innerHTML = `<div class="eva-3-loader"> 
    <div class="loader-container"> 
      <div class="spinner-dot"></div> 
      <div class="loader-spinner"> 
        <div class="spinner-container spinner-left"> 
          <div class="spinner-circle"></div> 
        </div> 
        <div class="spinner-container spinner-right"> 
          <div class="spinner-circle"></div> 
        </div> 
      </div> 
    </div><span class="loader-label -sm">Buscando...</span> 
  </div> `
}

function disableLoader(){
    flyButton.disabled = false;
    document.getElementById('loaderSpot').innerHTML = ""; 
}
function renderMarkup(json, isReturnNecessary) {
    divClusters.innerHTML = "";
    if(json.status == 400 || (json.status == 500 && json.message.includes("Country for location with code"))){
        disableLoader();
        divClusters.innerHTML = renderMessage400();
    }
    else if (json.status == 430){
        disableLoader();
        divClusters.innerHTML = renderMessage430();
    }
    else if(json.clusters != undefined && Object.keys(json.clusters).length == 0){
        disableLoader();
        divClusters.innerHTML = renderMessageNoFly();
    }
    else if (json.status >= 500){
        disableLoader();
        divClusters.innerHTML = renderSystemError("¡Ups! Ha ocurrido un error en el sistema", "Ha ocurrido un error en el sistema, por favor intente luego." );
    } 
    else
    {
        let idIndex = 0;
        const referenceData = json.referenceData;
        divClusters.classList.add(`total-${Object.keys(json.clusters).length}`)
        for (let cluster of json.clusters ) {
            let htmlForCluster = "";
            let divCluster = document.createElement('div');
            divCluster.id = 'cluster-' + idIndex.toString();
            //comienzo container
            htmlForCluster += renderContainerBegin();
            // comienzo clusters
            htmlForCluster += renderClusterBodyBegin();
            // comienzo cluster ida //
            htmlForCluster += renderHeaderGo(referenceData);
            htmlForCluster += renderItems(cluster.routeChoices[0].routes, referenceData, idIndex.toString());
            // fin cluster ida //
            // comienzo cluster vuelta //
            if(isReturnNecessary){
                htmlForCluster += renderHeaderReturn(referenceData);
                htmlForCluster += renderItems(cluster.routeChoices[1].routes, referenceData, 'r' + idIndex.toString());
            }
            idIndex++;
            // end cluster vuelta //
            // end clusterBody 
            htmlForCluster += renderClusterBodyEnd();                                
            /////
                // comienzo price box //
                htmlForCluster += renderPriceBox(cluster);
                // end price box //
            htmlForCluster += renderContainerEnd();
            divCluster.innerHTML = htmlForCluster;
            divClusters.append(divCluster)
        }
    }
}