$(function() {

    $('#addAgencyForm').submit(function(e) {

        //Prevent default submission of form
        e.preventDefault();

        let json = {};
        json.addAgencyName = $('#addAgencyName').val();
        json.addAgencyCity = $('#addAgencyCity').val();

        $.ajax({
            type: "POST",
            url : '/agency/add',
            data : JSON.stringify(json),
            /*data: $('#localParamsForm').serialize(),*/
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
                $('#addAgencyResult pre code').text("");
                $('#addAgencyResult pre code').text(JSON.stringify(res));
                $('#addAgencyResult').show();
            }
        })
    });
});
$(function() {

    $('#editAgencyForm').submit(function(e) {

        //Prevent default submission of form
        e.preventDefault();

        let json = {};

        json.editAgencyId = $('#editAgencyId').val();
        json.editAgencyName = $('#editAgencyName').val();
        json.editAgencyCity = $('#editAgencyCity').val();

        $.ajax({
            type: "POST",
            url : '/agency/edit',
            data : JSON.stringify(json),
            contentType: "application/json; charset=utf-8",
            async: true,
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                //Set response
                $('#editAgencyResult pre code').text("");
                $('#editAgencyResult pre code').text(JSON.stringify(res));
                $('#editAgencyResult').show();
            }
        })
    });
});
$(function() {

    $('#deleteAgencyForm').submit(function(e) {
        e.preventDefault();
        let json = {};
        json.deleteAgencyId = $('#deleteAgencyId').val();
        $.ajax({
            type: "POST",
            url : '/agency/delete',
            data : JSON.stringify(json),
            contentType: "application/json; charset=utf-8",
            async: true,
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                //Set response
                $('#removeAgencyResult pre code').text("");
                $('#removeAgencyResult pre code').text(JSON.stringify(res));
                $('#removeAgencyResult').show();
            }
        })
    });
});


$(function() {

    $('#addCarForm').submit(function(e) {
        e.preventDefault();
        let json = {};
        json.addAgencyId = $('#addAgencyId').val();
        json.addCarDescription = $('#addCarDescription').val();
        json.addCarModel = $('#addCarModel').val();
        json.addCarYear = $('#addCarYear').val();
        json.addCarCapacity = $('#addCarCapacity').val();
        json.addCarPatent = $('#addCarPatent').val();
        json.addCarPrice = $('#addCarPrice').val();
        $.ajax({
            type: "POST",
            url : '/agency/car/add',
            data : JSON.stringify(json),
            contentType: "application/json; charset=utf-8",
            async: true,
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                //Set response
                $('#addCarResult pre code').text("");
                $('#addCarResult pre code').text(JSON.stringify(res));
                $('#addCarResult').show();
            }
        })
    });
});


$(function() {

    $('#getAgencyIdForm').submit(function(e) {
        e.preventDefault();
        let json = {};
        json.getAgencyId = $('#getAgencyId').val();
        let agencyName = $("#getAgencyId option:selected").text();
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url : '/agency/cars',
            data : JSON.stringify(json),
            dataType: 'json',
            async: true,
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                //Set response
                console.log(res);
                document.getElementById('deleteCar').innerHTML = "";
                let displayFlyOptions = function(result){
                    let content = ``;
                    console.log(result);
                    for (r in result){
                        console.log(r)
                        content += `<option value="${result[r].id}">Modelo: ${result[r].model} Año: ${result[r].year} Patente: ${result[r].patent}</option>`
                    }
                    return content;
                };
                document.getElementById('deleteCar').innerHTML += '<form id="deleteCarForm" method="post" action="">\n' +
                    '<div class="form-group">\n' +
                    '<label for="deleteCarId"><strong>Autos para ' +`${agencyName}` + ' </strong></label>\n' +
                    '<select type="text" class="form-control form-control-sm" aria-describedby="agencyIdHelp"  id="deleteCarId">\n' + `${displayFlyOptions(res)}` +
                    '          </select>\n' +
                    '          <small id="agencyIdHelp" class="form-text text-muted">Elija el auto a eliminar.</small>\n' +
                    '        </div>\n' +
                    '\n' +
                    '        <div class="text-center">\n' +
                    '                  <button type="submit" class="btn btn-primary btn-lg" id="btnParams">Borrar auto</button>\n' +
                    '        </div>\n' +
                    '    </form>';

                $('#addCarResult pre code').text("");
                $('#addCarResult').show();
            }
        })
    });
});

$(function() {
    $('body').on("submit", "#deleteCarForm",function(e) {
        e.preventDefault();
        let json = {};
        json.getAgencyId = $('#getAgencyId').val();
        json.deleteCarId = $('#deleteCarId').val();
        $.ajax({
            type: "POST",
            url : '/agency/car/delete',
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(json),
            async: true,
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                //Set response
                $('#removeCarResult pre code').text("");
                $('#removeCarResult pre code').text(JSON.stringify(res));
                $('#removeCarResult').show();
            }
        })
    })
});
