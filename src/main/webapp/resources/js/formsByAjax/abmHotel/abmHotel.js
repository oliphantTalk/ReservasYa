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