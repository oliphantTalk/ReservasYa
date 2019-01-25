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

    $('#addFlight').submit(function(e) {

        //Prevent default submission of form
        e.preventDefault();

        let json = {};
        json.idfly = $('#idfly').val();
        //json.addAirlineShortName = $('#addAirlineShortName').val();
        $.ajax({
            type: "GET",
            url : '/airline/add',
            //data : JSON.stringify(json),
            data: {
                idfly: json.idfly
            },
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