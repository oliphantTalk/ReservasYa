$(function() {

    $('#addUserForm').submit(function(e) {

        //Prevent default submission of form
        e.preventDefault();

        let json = {};
        json.roleId = $('#rol').val();
        json.userName = $('#userName').val();
        json.password = $('#password').val();

        $.ajax({
            type: "POST",
            url : '/user/add',
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
                $('#addUserResult pre code').text("");
                $('#addUserResult pre code').text(JSON.stringify(res));
                $('#addUserResult').show();
            }
        })
    });
});
$(function() {

    $('#editUserForm').submit(function(e) {

        //Prevent default submission of form
        e.preventDefault();

        let json = {};
        json.roleId = $('#editRolId').val();
        json.userId = $('#userId').val();
        json.password = $('#passwordEdit').val();

        $.ajax({
            type: "POST",
            url : '/user/edit',
            data : JSON.stringify(json),
            contentType: "application/json; charset=utf-8",
            async: true,
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                //Set response
                $('#editUserResult pre code').text("");
                $('#editUserResult pre code').text(JSON.stringify(res));
                $('#editUserResult').show();
            }
        })
    });
});
$(function() {

    $('#deleteUserForm').submit(function(e) {
        e.preventDefault();
        let json = {};
        json.deleteUserId = $('#deleteUserId').val();
        $.ajax({
            type: "POST",
            url : '/user/delete',
            data : JSON.stringify(json),
            contentType: "application/json; charset=utf-8",
            async: true,
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                //Set response
                $('#removeUserResult pre code').text("");
                $('#removeUserResult pre code').text(JSON.stringify(res));
                $('#removeUserResult').show();
            }
        })
    });
});