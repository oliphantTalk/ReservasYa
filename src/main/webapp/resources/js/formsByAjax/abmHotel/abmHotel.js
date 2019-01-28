$(function() {

    $('#addHotelForm').submit(function(e) {

        //Prevent default submission of form
        e.preventDefault();

        let json = {};
        json.addHotelName = $('#addHotelName').val();
        json.addHotelCity = $('#addHotelCity').val();
        json.addHotelStars = $('#addHotelStars').val();
        $.ajax({
            type: "POST",
            url : '/hotel/add',
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

    $('#editHotelForm').submit(function(e) {

        //Prevent default submission of form
        e.preventDefault();

        let json = {};
        json.editHotelId = $('#editHotelId').val();
        json.editHotelName = $('#editHotelName').val();
        json.editHotelCity = $('#editHotelCity').val();
        json.editHotelStars = $('#editHotelStars').val();

        $.ajax({
            type: "POST",
            url : '/hotel/edit',
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

    $('#deleteHotelForm').submit(function(e) {
        e.preventDefault();
        let json = {};
        json.deleteHotelId = $('#deleteHotelId').val();
        $.ajax({
            type: "POST",
            url : '/hotel/delete',
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

    $('#addRoomForm').submit(function(e) {
        e.preventDefault();
        let json = {};
        json.addHotelId = $('#addHotelId').val();
        json.addRoomId = $('#addRoomId').val();
        json.addRoomBeds = $('#addRoomBeds').val();
        json.addRoomPrice = $('#addRoomPrice').val();
        $.ajax({
            type: "POST",
            url : '/hotel/room/add',
            data : JSON.stringify(json),
            contentType: "application/json; charset=utf-8",
            async: true,
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                //Set response
                $('#resultAddRoom pre code').text(JSON.stringify(res));
                $('#resultAddRoom').show();
            }
        })
    });
});


$(function() {

    $('#getHotelIdForm').submit(function(e) {
        e.preventDefault();
        let json = {};
        json.getHotelId = $('#getHotelId').val();
        let hotelName = $("#getHotelId option:selected").text();
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url : '/hotel/rooms',
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
                document.getElementById('deleteRoom').innerHTML = "";
                let displayFlyOptions = function(result){
                    let content = ``;
                    console.log(result);
                    for (r in result){
                        console.log(r)
                        content += `<option value="${result[r].id}">Numero habitacion: ${result[r].roomId} Capacidad: ${result[r].beds} }</option>`
                    }
                    return content;
                };
                document.getElementById('deleteRoom').innerHTML += '<form id="deleteRoomForm" method="post" action="">\n' +
                    '<div class="form-group">\n' +
                    '<label for="deleteRoomId"><strong>Autos para ' +`${hotelName}` + ' </strong></label>\n' +
                    '<select type="text" class="form-control form-control-sm" aria-describedby="hotelIdHelp"  id="deleteRoomId">\n' + `${displayFlyOptions(res)}` +
                    '          </select>\n' +
                    '          <small id="hotelIdHelp" class="form-text text-muted">Elija el auto a eliminar.</small>\n' +
                    '        </div>\n' +
                    '\n' +
                    '        <div class="text-center">\n' +
                    '                  <button type="submit" class="btn btn-primary btn-lg" id="btnParams">Borrar habitacion</button>\n' +
                    '        </div>\n' +
                    '    </form>';

                $('#resultContainer pre code').text("");
                $('#resultContainer').show();
            }
        })
    });
});

$(function() {
    $('body').on("submit", "#deleteRoomForm",function(e) {
        e.preventDefault();
        let json = {};
        json.getHotelId = $('#getHotelId').val();
        json.deleteRoomId = $('#deleteRoomId').val();
        $.ajax({
            type: "POST",
            url : '/hotel/room/delete',
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(json),
            async: true,
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                //Set response
                console.log(res)
                $('#resultContainer pre code').text(JSON.stringify(res));
                $('#resultContainer').show();
            }
        })
    })
});
