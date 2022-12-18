$(function () {
    $('.box-date-modalInforme').datepicker({
        dateFormat: 'dd/mm/yy',
        minDate: 0,
        defaultDate: "+1w"
    });
})

$('.box-date-modalInforme').click(function () {
    $('.ui-datepicker').css("z-index", "9999");
})


$('.icon-download3').hover(function () {
    $(this).toggleClass('icon-change-color-click');
})

var atualizarGrid = function () {
    $.ajax({
    method: "POST",
    url: "/historicoabends/modal/updateList",
    contentType: "application/json",
    data: JSON.stringify({
    dataEvento: $('.box-date-modalInforme').val()
    }),
        success: function (data, xhr, statusText) {
        if (statusText.status == 200) {
        for (var x =0; x<= 3; x++) {
            $('.table-informe-details').html(data);
            clearForm();
                }
            }
        },
    })
}

$('.arrow-right-absolute').click(function () {
    var id = parseInt($('.id-hidden').val());
    var process = $('.title-processo-modalInforme').val();
    var direction = "NEXT"

    getNextOrPreviusByClick(id,process,direction);
});

$('.arrow-left-absolute').click(function () {
    var id = parseInt($('.id-hidden').val());
    var process = $('.title-processo-modalInforme').val();
    var direction = "PREV"

    getNextOrPreviusByClick(id,process,direction);

});

function getNextOrPreviusByClick(id, process, direction){
    $.ajax({
        method: "GET",
        url: "/historicoabends/modal/getPreviusOrNext/"+id+"/"+process+"/"+direction,
        contentType: "application/json",
        success: function (data,textStatus,jqXHR) {
           $('.modal-search-id').html(data);
        },
        error: function (data){
            $('.modal-search-id').modal('hide');
            $('.modal-return-message').html(data.responseJSON.message)
            $('.modal-return').modal('show')
        }
    })

}


$('.btn-delete').click(function () {
    var id = $('.id-hidden').val();
    $.ajax({
        type : "DELETE",
        url: "/historicoabends/modal/deleteInforme/"+id,
        contentType: "application/json",
        success: function (data, xhr, statusText) {
            $('.modal-search-id').modal('hide');
            $('.modal-return-message').text(data);
            $('.modal-return').modal('show')
            atualizarGrid();
        },
        error: function (data, xhr, statusText) {
            $('.modal-return-message').text(data.responseJSON.message);
            $('.modal-return').modal('show')
        }

    })
});

$('.btn-update').click(function () {
    if($(".box-date-modalInforme").val() == '' || $('.select-sistema-modalInforme').val() == '' || $('.select-atuacao-modalInforme').val() == '' || $('.select-email-modalInforme').val() == '' || $('.select-ocorrencia-modalInforme').val() == ''
        || $(".modal-text-desc").val() == '' || $(".modal-text-prod").val() == '' || $(".modal-text-branch").val() == ''){
        return;
    }

    var dataEnvio = $('.box-date-modalInforme').val();
    var sistema = $('.select-sistema-modalInforme option:selected').text();
    var processo = $('.title-processo-modalInforme').val();
    var ticket = $('.ipt-ticket-modalInforme').val();
    var equipe = $('.select-equipe-modalInforme option:selected').text();
    var evento = $('.ipt-evento-modalInforme').val();
    var atuacao = $('.ipt-atuacao-modalInforme').val();
    var erro = $('.ipt-erro-modalInforme').val();
    var ocorrencia = $('.select-ocorrencia-modalInforme option:selected').text();
    var selectAtuacao = $('.select-atuacao-modalInforme option:selected').text();
    var selectProcesso = $('.select-processo-modalInforme option:selected').text();
    var selectAcao = $('.select-acao-modalInforme option:selected').text();
    var selectEmail = $('.select-email-modalInforme option:selected').text();
    var blnInforme = $('.check-box-modalInforme').is(":checked");
    var descricao = $('.modal-text-desc').val();
    var produto = $('.modal-text-prod').val();
    var branch = $('.modal-text-branch').val();


    var formData = new FormData();
    var file = new Array();

    for(var i=0; i< $('.custom-file-input-modal')[0].files.length; i++){
        formData.append("file", $('.custom-file-input-modal')[0].files[i]);
    }

    formData.append('historicoInforme', new Blob([JSON.stringify({
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
        destinatario: selectEmail,
        blnInforme : blnInforme,
        id: $('.id-hidden').val()
    })],
{
          type : "application/json"
        }))


    formData.append('infoJobs', new Blob([JSON.stringify({
        descricao: descricao,
        branch: branch,
        produto: produto

    })],
{
          type : "application/json"
        }))


    $.ajax({
    method: "PUT",
    url: "/historicoabends/modal/updateInforme",
    processData: false,
    contentType: false,
    cache: false,
    data: formData,
    success: function (data, xhr, statusText) {
            if (statusText.status == 200) {
                atualizarGrid();
                $('.modal-search-id').modal('hide');
                $('.modal-return-message').html(data)
                $('.modal-return').modal('show')
            }
         },
    })
});

$('.custom-file-input-modal').change(function (e) {
    var nome = ''

    for(var i=0; i< $(this)[0].files.length; i++){
        nome += e.target.files[i].name + " / ";
        $('.custom-file-label').text(nome);
    }
});





$('.icon-download3').hover(function () {
    $(this).toggleClass('icon-change-color-click');
})

$('.btn-copy').click(function () {
    var dataEnvio = $('.box-date-modalInforme').val();
    var sistema = $('.select-sistema-modalInforme option:selected').text();
    var processo = $('.title-processo-modalInforme').val();
    var ticket = $('.ipt-ticket-modalInforme').val();
    var equipe = $('.select-equipe-modalInforme option:selected').text();
    var evento = $('.ipt-evento-modalInforme').val();
    var atuacao = $('.ipt-atuacao-modalInforme').val();
    var erro = $('.ipt-erro-modalInforme').val();
    var ocorrencia = $('.select-ocorrencia-modalInforme option:selected').text();
    var selectAtuacao = $('.select-atuacao-modalInforme option:selected').text();
    var selectProcesso = $('.select-processo-modalInforme option:selected').text();
    var selectAcao = $('.select-acao-modalInforme option:selected').text();
    var selectEmail = $('.select-email-modalInforme option:selected').text();
    var blnInforme = $('.check-box-modalInforme').is(":checked");

    $.ajax({
        method: "POST",
        url: "/historicoabends/modal/copyInforme",
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
            blnInforme : blnInforme,
        }),
        success: function (data, xhr, statusText) {
            if(statusText.status == 200) {
                atualizarGrid();
                $('.modal-search-id').modal('hide');
            }
        },
    })
});

