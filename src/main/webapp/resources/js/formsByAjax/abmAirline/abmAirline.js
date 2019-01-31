$(function() {

    $('#addAirlineForm').submit(function(e) {

        //Prevent default submission of form
        e.preventDefault();

        let json = {};
        json.addAirlineName = $('#addAirlineName').val();
        //json.addAirlineShortName = $('#addAirlineShortName').val();
        $.ajax({
            type: "POST",
            url : '/airline/add',
            data : JSON.stringify(json),
            contentType: "application/json; charset=utf-8",
            async: true,
            /*
                     async: true,
            */
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                //Set response
                $('#addAirlineResult pre code').text("");
                $('#addAirlineResult pre code').text(JSON.stringify(res));
                $('#addAirlineResult').show();
            }
        })
    });
});
$(function() {

    $('#editAirlineForm').submit(function(e) {

        //Prevent default submission of form
        e.preventDefault();

        let json = {};
        json.editAirlineId = $('#editAirlineId').val();
        json.editAirlineName = $('#editAirlineName').val();
        json.editAirlineShortName = $('#editAirlineShortName').val();

        $.ajax({
            type: "POST",
            url : '/airline/edit',
            data : JSON.stringify(json),
            contentType: "application/json; charset=utf-8",
            async: true,
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                //Set response
                $('#editAirlineResult pre code').text("");
                $('#editAirlineResult pre code').text(JSON.stringify(res));
                $('#editAirlineResult').show();
            }
        })
    });
});
$(function() {

    $('#deleteAirlineForm').submit(function(e) {
        e.preventDefault();
        let json = {};
        json.deleteAirlineId = $('#deleteAirlineId').val();
        $.ajax({
            type: "POST",
            url : '/airline/delete',
            data : JSON.stringify(json),
            contentType: "application/json; charset=utf-8",
            async: true,
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                //Set response
                $('#removeAirlineResult pre code').text("");
                $('#removeAirlineResult pre code').text(JSON.stringify(res));
                $('#removeAirlineResult').show();
            }
        })
    });
});

$(function() {

    $('#addFlightForm').submit(function(e) {
        e.preventDefault();
        let json = {};
        json.addAirlineId = $('#addAirlineId').val();
        json.addGapMax = $('#addGaṕMax').val();
        json.addScales = $('#addScales').val();
        json.addFlightFrom = $('#addFlightFrom').val();
        json.addFlightTo = $('#addFlightTo').val();
        let depDate = $('#addFlightDepDate').val();
        let depTime = $('#addFlightDepTime').val();
        let fixDepGMT = depTime.split(":");
        let arrDate = $('#addFlightArrDate').val();
        let arrTime = $('#addFlightArrTime').val();
        let fixArrGMT = arrTime.split(":");
        depTime = (parseInt(fixDepGMT[0]) - 3);
        if(depTime < 0){
            depTime += 24;}
        depTime = depTime.toString() + ':' + fixDepGMT[1];
        json.addFlightDepartureDate = (new Date(depDate + " " + depTime)).toISOString();
        arrTime = (parseInt(fixArrGMT[0]) - 3);
        if(arrTime < 0) { arrTime += 24 ;}
        arrTime = arrTime.toString() + ':' + fixArrGMT[1];
        json.addFlightArrivalDate = (new Date(arrDate + " " + arrTime)).toISOString();
        json.addEconomic = $('#addEconomic').val();
        json.addFirst = $('#addFirst').val();
        json.addBusiness = $('#addBusiness').val();
        json.addFlightPrice = $('#addFlightPrice').val();
        $.ajax({
            type: "POST",
            url : '/airline/flight/add',
            data : JSON.stringify(json),
            contentType: "application/json; charset=utf-8",
            async: true,
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                //Set response
                $('#addFlightResult pre code').text("");
                $('#addFlightResult pre code').text(JSON.stringify(res));
                $('#addFlightResult').show();
            }
        })
    });
});


$(function() {

    $('#getAirlineIdForm').submit(function(e) {
        e.preventDefault();
        let json = {};
        json.getAirlineId = $('#getAirlineId').val();
        let airlineName = $("#getAirlineId option:selected").text();
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url : '/airline/flights',
            data : JSON.stringify(json),
            dataType: 'json',
            async: true,
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                //Set response
                document.getElementById('deleteFlight').innerHTML = "";
                let displayFlyOptions = function(result){
                    let content = ``;
                    for (r in result){
                        content += `<option value="${result[r].id}">Codigo: ${result[r].flyCode} Origen: ${result[r].from} Destino: ${result[r].to} Salida: ${result[r].departureTime} LLegada: ${result[r].arrivalTime}</option>`
                    }
                    return content;
                };
                document.getElementById('deleteFlight').innerHTML += '<form id="deleteFlightForm" method="post" action="">\n' +
                    '        <div class="form-group">\n' +
                    '          <label for="deleteFlightId"><strong>Vuelos para ' +`${airlineName}` + ' </strong></label>\n' +
                    '          <select type="text" class="form-control form-control-sm" aria-describedby="airlineIdHelp"  id="deleteFlightId">\n' + `${displayFlyOptions(res)}` +
                    '          </select>\n' +
                    '          <small id="airlineIdHelp" class="form-text text-muted">Elija el vuelo a eliminar.</small>\n' +
                    '        </div>\n' +
                    '\n' +
                    '        <div class="text-center">\n' +
                    '                  <button type="submit" class="btn btn-primary btn-lg" id="btnParams">Borrar Vuelo</button>\n' +
                    '        </div>\n' +
                    '    </form>';

                $('#addFlightResult pre code').text("");
                $('#addFlightResult').show();
            }
        })
    });
});

$(function() {
    $('body').on("submit", "#deleteFlightForm",function(e) {
        e.preventDefault();
        let json = {};
        json.getAirlineId = $('#getAirlineId').val();
        json.deleteFlightId = $('#deleteFlightId').val();
        $.ajax({
            type: "POST",
            url : '/airline/flight/delete',
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(json),
            async: true,
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                //Set response
                $('#removeFlightResult pre code').text("");
                $('#removeFlightResult pre code').text(JSON.stringify(res));
                $('#removeFlightResult').show();
            }
        })
    })
});

