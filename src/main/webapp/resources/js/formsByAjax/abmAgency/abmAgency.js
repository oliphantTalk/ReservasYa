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
                $('#resultContainer pre code').text(JSON.stringify(res));
                $('#resultContainer').show();
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
                $('#resultContainer pre code').text(JSON.stringify(res));
                $('#resultContainer').show();
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
                $('#resultContainer pre code').text(JSON.stringify(res));
                $('#resultContainer').show();
            }
        })
    });
});