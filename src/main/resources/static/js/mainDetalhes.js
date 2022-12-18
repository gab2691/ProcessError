/*
$( document ).ready(function() {


    $(function () {
        $('.box-date').datepicker({dateFormat: 'dd/mm/yy'});
    })


    $('body').on('click', '.ui-menu-item-wrapper', function () {
        $(this).text();
    })



$('.btn-search').click(function () {
    $('body').loadingView({
        'state': true,
        'image': "/historicoabends/imagens/logoSantander.png",
    });

    var dataEnvio = $('.box-date').val();
    var sistema = $('.select-sistema option:selected').text();
    var processo = $('.title-processo').val();
    var ticket = $('.ipt-ticket').val();
    var equipe = $('.select-equipe option:selected').text();
    var evento = $('.ipt-evento').val();
    var atuacao = $('.ipt-atuacao').val();
    var erro = $('.ipt-erro').val();
    var ocorrencia = $('.select-ocorrencia option:selected').text();
    var selectAtuacao = $('.select-atuacao option:selected').text();
    var selectProcesso = $('.select-processo option:selected').text();
    var selectAcao = $('.select-acao option:selected').text();
    var selectEmail = $('.select-email option:selected').text();

    $.ajax({
        method: "POST",
        url: "/historicoabends/informe/getInformeBySearch",
        contentType: "application/json",
        data: JSON.stringify({
            dataEvento: dataEnvio,
            sistema: sistema,
            processo: processo,
            ticket: ticket,
            equipes: equipe,
            eventoFuncionalidade: evento,
            descAtuacao: atuacao,
            descOcorrencia: erro,
            strTipoOcorrencia: ocorrencia,
            strTipoAtuacao: selectAtuacao,
            coreProcesso: selectProcesso,
            strAcaoInterna: selectAcao,
            seguimento: selectEmail,
            id : null
        }),
        success: function (data, statusText, xhr) {
            setTimeout(function () {
                $('body').loadingView({
                    'state': false,
                });
                $('.box-table').html(data);
            }, 1000);
        },
    })

});

$('.linha-informer-table').hover(function () {
    $(this).toggleClass('change-table-row');
})

$(".title-processo").autocomplete({
    source: function (request, response) {
        var processo = $('.title-processo').val();
        $.ajax({
            method: "POST",
            url: "/historicoabends/informe/getJobsSearche",
            contentType: "application/json",
            data: JSON.stringify({
                processo : processo
            }),
            success: function(data) {

                response($.map(data, function(item) {
                    return {
                        label: item,
                        value: item
                    }
                }));
            }
        });
    }
});

/!*$('.linha-informer-table').click(function () {
    console.log('aqui')
    var id = $(this).find('.idInforme').text();
    $.ajax({
        method: "POST",
        url: "/historicoabends/informe/getInformeById",
        contentType: "application/json",
        data: JSON.stringify({
            id: id
        }),
        success: function (data, xhr, statusText) {

            if (statusText.status == 200) {
                console.log(data)
                /!*$('.modal-search-id').html(data)
                $('.modal-search-id').modal('show');*!/
            }

            if (statusText.status == 500) {
                $('.modal-search-error').modal('show');
            }
        },
    })
})*!/





var atualizarGrid = function(){
    $.ajax({
        method: "POST",
        url: "/historicoabends/informe/getInformeBySearch",
        contentType: "application/json",
        data: JSON.stringify({
            dataEvento : $('.box-date').val()
        }),
        success: function(data,xhr,statusText) {
            if(statusText.status == 200) {
                $('.table-informe').html(data);
            }
        },
    })
}

    $('.custom-file-input').change(function (e) {
        var nome = ''

        for(var i=0; i< $(this)[0].files.length; i++){
            nome += e.target.files[i].name + " / ";
            $('.custom-file-label').text(nome);
        }
    })

});

$(document).ready(function () {
    $("table").colResizable({resizeMode:'overflow'});
})
$(function () {
    $('.box-date').datepicker({dateFormat: 'dd/mm/yy'});
})

$('.addNewElement').click(function () {
    $('.modal-add-element').modal('show');
})

$('.addNewSystem').click(function () {
    $('.modal-add-system').modal('show');
})
$('.btn-new-option').click(function (){
    $('.select-equipe').append($("<option></option>").attr("selected", true).text($('.new-input-option').val()));
    $('.new-input-option').val('');
})

$('.btn-new-option-system').click(function (){
    $('.select-sistema').append($("<option></option>").attr("selected", true).text($('.new-input-system').val()));
    $('.btn-new-option-system').val('');
})









$('.btn-open-ppt').click(function () {
    $.ajax({
        method: "GET",
        url: "/historicoabends/informe/downloadInforme",
        contentType: "application/json"
    })
})

$('.custom-file-input').change(function (e) {
    var nome = ''

    for(var i=0; i< $(this)[0].files.length; i++){
        nome += e.target.files[i].name + " / ";
        $('.custom-file-label').text(nome);
    }
})


*/
