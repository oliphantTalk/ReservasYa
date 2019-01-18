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
            url : '/panel/admin/user/add',
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

    $('#editUserForm').submit(function(e) {

        //Prevent default submission of form
        e.preventDefault();

        let json = {};
        json.roleId = $('#editRolId').val();
        json.userId = $('#userId').val();
        json.password = $('#password').val();

        $.ajax({
            type: "POST",
            url : '/panel/admin/user/edit',
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

    $('#deleteUserForm').submit(function(e) {
        e.preventDefault();
        let json = {};
        json.deleteUserId = $('#deleteUserId').val();
        $.ajax({
            type: "POST",
            url : '/panel/admin/user/delete',
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