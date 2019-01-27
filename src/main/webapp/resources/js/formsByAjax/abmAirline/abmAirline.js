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
                $('#resultContainer pre code').text(JSON.stringify(res));
                $('#resultContainer').show();
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
                $('#resultContainer pre code').text(JSON.stringify(res));
                $('#resultContainer').show();
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
                $('#resultContainer pre code').text(JSON.stringify(res));
                $('#resultContainer').show();
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
                $('#resultContainer pre code').text(JSON.stringify(res));
                $('#resultContainer').show();
            }
        })
    });
});


$(function() {

    $('#getAirlineForm').submit(function(e) {

        //Prevent default submission of form
        e.preventDefault();

        let json = {};
        json.getAirlineId = $('#getAirlineId').val();
        //json.addAirlineShortName = $('#addAirlineShortName').val();
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url : '/airline/addF',
            data : JSON.stringify(json),
            dataType: 'json',
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
                console.log(res);
                document.getElementById('pipi').innerHTML += '  <form id="addFlightForm" method="post"  th:object="${abmFlightForm}" th:action="@{/airline/flight/add}">\n' +
                    '\n' +
                    '        <div class="form-group" >\n' +
                    '            <label for="airlineName"><strong>Nombre de la aerolinea</strong></label>\n' +
                    '            <input type="text" class="form-control form-control-sm"  aria-describedby="airlineNameHelp" th:field="*{addAirlineName}">\n' +
                    '            <small id="airlineNameHelp" class="form-text text-muted">Nombre de la aerolinea a crear.</small>\n' +
                    '        </div>\n' +
                    '\n' +
                    '        <div class="form-group">\n' +
                    '            <label for="shortName"><strong>Nombre corto de la aerolinea</strong></label>\n' +
                    '            <input type="text" class="form-control form-control-sm"  aria-describedby="shortNameHelp" th:field="*{addAirlineShortName}">\n' +
                    '            <small id="shortNameHelp" class="form-text text-muted">Nombre corto de la aerolinea.</small>\n' +
                    '        </div>\n' +
                    '\n' +
                    '        <div class="text-center">\n' +
                    '          <button type="submit" class="btn btn-primary btn-lg" id="btnParams">Guardar</button>\n' +
                    '        </div>\n' +
                    '    </form>';
                document.getElementById('pipi').innerHTML += `${JSON.stringify(res[0])}`;

                $('#resultContainer pre code').text("");
                $('#resultContainer').show();
            }
        })
    });
});

/*$('#location').change(
        function() {
            $.getJSON("http://localhost:8181/appointment/agencies", {
                cityId : $(this).val(),
                ajax : 'true'
            }, function(data) {
                var html = '<option value="">--alege agentia--</option>';
                var len = data.length;
                for ( var i = 0; i < len; i++) {
                    html += '<option value="' + data[i].nume + '">'
                            + data[i].nume + '</option>';
                }
                html += '</option>';
                $('#agency').html(html);
            });
        });
*/