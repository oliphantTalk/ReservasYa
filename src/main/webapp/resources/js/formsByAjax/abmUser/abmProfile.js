$(function() {
    $('#editProfileForm').submit(function(e) {
        e.preventDefault();
        let json = {};
        json.pName = $('#pName').val();
        json.pUsername = $('#pUsername').val();
        json.pEmail = $('#pEmail').val();
        json.pPassword = $('#pPassword').val();
        console.log(json);
        $.ajax({
            type: "POST",
            url : '/user/profile/edit',
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(json),
            async: true,
            dataType: 'json',
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                //Set response
                $('#editProfileResult pre code').text("");
                $('#editProfileResult pre code').text(JSON.stringify(res));
                $('#editProfileResult').show();
            }
        })
    });
});

