$( document ).ready(function() {

    $(function () {
        $('.box-date').datepicker(
            {
                dateFormat: 'dd/mm/yy'
            });
    })

    $(function getDailyProcess(){
        $.ajax({
            method: 'GET',
            url: "/historicoabends/informe/dailyProcess",
            contentType: "application/json",
            success: function (data) {
                $('.table-informe-details').html(data)
                $('.modal-loading').modal('hide')
            }
        });
    })
});


$(".title-processo").autocomplete({
    source: function (request, response) {
        var job = $('.title-processo').val();
        $.ajax({
            method: "GET",
            url: "/historicoabends/informe/jobsAutoComplete/"+job,
            contentType: "application/json",
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

$( ".title-processo" ).focusout(function() {
    job = $(".title-processo").val();
    if(job.length > 3){

        $.ajax({
            method: "GET",
            url: "/historicoabends/informe/findInfoJobs/"+job,
            contentType: "application/json",
            success: function (data,textStatus,jqXHR) {
                $('.text-desc').val(data.descricao);
                $('.text-prod').val(data.produto);
                $('.text-branch').val(data.branch);
            },
        })
    }

})


$('.btn-save').click( function() {
    if($(".title-processo").val() == '' || $(".box-date").val() == '' || $('.select-sistema').val() == '' || $('.select-atuacao').val() == '' || $('.select-email').val() == '' || $('.select-ocorrencia').val() == ''
        || $(".modal-text-desc").val() == '' || $(".modal-text-prod").val() == '' || $(".modal-text-branch").val() == ''){
        return;
    }
    $('.modal-loading').modal('show');

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
    var blnInforme = $('.check-box').is(":checked");
    var descricao = $('.text-desc').val();
    var produto = $('.text-prod').val();
    var branch = $('.text-branch').val();


    var formData = new FormData();
    var file = new Array();

    for(var i=0; i< $('.custom-file-input')[0].files.length; i++){
        formData.append("file", $('.custom-file-input')[0].files[i]);
    }

    formData.append('historicoInforme', new Blob([JSON.stringify({
            id : null,
            dataEvento : dataEnvio,
            sistema : sistema,
            processo : processo,
            ticket : ticket,
            equipes : equipe,
            eventoFuncionalidade : evento,
            descAtuacao : atuacao,
            descOcorrencia : erro,
            strTipoOcorrencia : ocorrencia,
            strTipoAtuacao : selectAtuacao,
            coreProcesso : selectProcesso,
            strAcaoInterna : selectAcao,
            destinatario: selectEmail,
            blnInforme : blnInforme,
            formData : formData,
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
        method: "POST",
        url: "/historicoabends/informe/insertInforme",
        processData: false,
        contentType: false,
        cache: false,
        data: formData,
        success: function(data,xhr,statusText) {
            setTimeout(function () {
                $('.modal-loading').modal('hide')
            }, 1000);
            atualizarGridInforme();
        },
        error: function (data){
            $('.modal-loading').modal('hide')
            $('.modal-return-message').html(data.responseText)
            $('.modal-return').modal('show');
        }
    })

});

var atualizarGridInforme = function(){
    $.ajax({
        method: "POST",
        url: "/historicoabends/informe/getInformeBySearch",
        contentType: "application/json",
        data: JSON.stringify({
            dataEvento : $('.box-date').val()
        }),
        success: function(data,xhr,statusText) {
            if(statusText.status == 200) {
                $('.table-informe-details').html(data);
                clearForm()
            }
        },
    })
}

$('.btn-clear').click(function () {
    clearForm();
})

var clearForm = function (){
    $('input').val('');
    $('select').val('').change()
    $('textarea').val('');
}

$('.btn-search').click(function () {
    $('.modal-loading').modal('show')

    var dataEnvio;
    if ($('.box-date').val() == null){
        dataEnvio = '';
    } else dataEnvio = $('.box-date').val();
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
            destinatario: selectEmail,
            id : null
        }),
        success: function (data) {
            $('.table-informe-details').html(data)
            $('.modal-loading').modal('hide')
        },
        error: function (data){
            setTimeout(function () {
                $('.modal-loading').modal('hide')
            }, 1000);
            setTimeout(function () {
                $('.modal-return-message').text(data.responseJSON.message);
                $('.modal-return').modal('show');
            }, 1500);
        }
    })

});


