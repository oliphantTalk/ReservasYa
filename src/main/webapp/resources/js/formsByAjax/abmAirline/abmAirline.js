$(function() {

    $('#addAirlineForm').submit(function(e) {

        //Prevent default submission of form
        e.preventDefault();

        let json = {};
        json.roleId = $('#rol').val();
        json.airlineName = $('#airlineName').val();
        json.password = $('#password').val();

        $.ajax({
            type: "POST",
            url : '/panel/admin/airline/add',
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
        json.roleId = $('#editRolId').val();
        json.airlineId = $('#airlineId').val();
        json.password = $('#passwordEdit').val();

        $.ajax({
            type: "POST",
            url : '/panel/admin/airline/edit',
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
            url : '/panel/admin/airline/delete',
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