
    $(function () {
        $('.box-date-rele').datepicker({dateFormat: 'dd/mm/yy'});
    })

    var atualizarGrid = function () {
        var dataEvento = '';
        if ($('.box-date-modal').length == 0) {
            dataEvento = $('.box-date').val();
        } else {
            dataEvento = $('.box-date-modal').val();
        }

        var descOcorrencia = $('.box-ocorrencia').val()

        $.ajax({
            method: "POST",
            url: "/historicoabends/relevantes/getOcorrenciaSearch",
            contentType: "application/json",
            data: JSON.stringify({
                dataEvento: dataEvento,
                descOcorrencia: descOcorrencia
            }),
            success: function (data, xhr, statusText) {
                $('.box-table').html(data);
            },
        })
    }

    $('.linha-informer-table').hover(function () {
        $(this).toggleClass('change-table-row');
    })

    $('.linha-informer-table').click(function () {
        var id = $(this).find('.idRele').val()
        $.ajax({
            method: "GET",
            url: "/historicoabends/relevantes/getOcorrenciaById/"+id,
            contentType: "application/json",
            success: function (data, xhr, statusText) {
                $('.modal-search-id-rele').html(data)
                $('.modal-search-id-rele').modal('show');
            },
        })
    })

    $('.btn-search').click(function () {
        var dataEvento = $('.box-date').val();
        var descOcorrencia = $('.box-ocorrencia').val()

        $.ajax({
            method: "POST",
            url: "/historicoabends/relevantes/getOcorrenciaSearch",
            contentType: "application/json",
            data: JSON.stringify({
                dataEvento: dataEvento,
                descOcorrencia: descOcorrencia
            }),
            success: function (data, xhr, statusText) {
                if (statusText.status == 200) {
                    $('.table-ocorrencia').html(data)

                }
                if (statusText.status == 500) {
                    $('.modal-search-error').modal('show');
                }
            },
        })

    })

    $('.btn-save').click(function () {
        var dataEvento = $('.box-date').val();
        var descOcorrencia = $('.box-ocorrencia').val()

        $.ajax({
            method: "POST",
            url: "/historicoabends/relevantes/saveRelevantes",
            contentType: "application/json",
            data: JSON.stringify({
                dataEvento: dataEvento,
                descOcorrencia: descOcorrencia
            }),
            success: function (data, xhr, statusText) {
                $('.modal-return-message').html(data);
                $('.modal-return').modal('show')
                atualizarGrid()
            },
        })
    })

    $('.btn-clear').click(function () {
        $('.box-date').val('');
        $('.box-ocorrencia').val('');
    });