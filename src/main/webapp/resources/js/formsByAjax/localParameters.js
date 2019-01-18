$(function() {
    $('#localParamsForm').submit(function(e) {
        e.preventDefault();
        let json = {};
        json.gapMax = $('#gapMax').val();
        json.factorDevolucion = $('#factorDevolucion').val();
        json.factorServicioVuelo = $('#factorServicioVuelo').val();
        json.factorVueloEscala = $('#factorVueloEscala').val();
        json.pesosPorPunto = $('#pesosPorPunto').val();
        json.puntosPorPeso = $('#puntosPorPeso').val();
        json.businessClassRate = $('#businessClassRate').val();
        json.firstClassRate = $('#firstClassRate').val();
        $.ajax({
            type: "POST",
            url : '/panel/admin/localParams',
            data : JSON.stringify(json),
            /*data: $('#localParamsForm').serialize(),*/
            contentType: "application/json; charset=utf-8",
            async: true,
            beforeSend: function(xhr){
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success : function(res) {
                $('#resultContainer pre code').text(JSON.stringify(res));
                $('#resultContainer').show();
            }
        })
    });
});