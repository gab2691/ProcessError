$( document ).ready(function() {
    $('.options-sidebar').hover(function () {
        $(this).find('.icon-clipboard-edit').toggleClass('icon-change-color');
        $(this).find('.title-options').toggleClass('text-change-color');
    })

    $('.options-sidebar').click(function () {
        $(this).parent().find('.sub-options-sidebar').toggleClass('box-fixed-show');
        $(this).find('.icon-keyboard_arrow_right').toggleClass('icon-keyboard_arrow_right_change');

        $(this).find('.icon-clipboard-edit').toggleClass('icon-change-color-click');
        $(this).find('.title-options').toggleClass('text-change-color-click');
    })

    $('.sub-options-sidebar').hover(function () {
        $(this).find('.icon-circle').toggleClass('icon-change-color');
        $(this).find('.title-sub-options').toggleClass('text-change-color');
    })

    $('.sub-options-sidebar').click(function () {
        $('.icon-circle').removeClass('icon-change-color-click');
        $('.title-sub-options').removeClass('text-change-color-click');

        $(this).find('.icon-circle').toggleClass('icon-change-color-click');
        $(this).find('.title-sub-options').toggleClass('text-change-color-click');
    });

    $('.sidebar-left').mouseenter(function () {
        $(this).toggleClass('full-heigth-left-menu');
    })

    $('.sidebar-left').mouseleave(function () {
        $(this).toggleClass('full-heigth-left-menu');
    })

    $('.menu-relevantes').click(function () {
        $.ajax({
            method: "GET",
            url: "/historicoabends/relevantes/getPageRelevantes",
            contentType: "application/json",
            success: function (data, xhr, statusText) {
                $('.main-container').html(data);
            },
        })
    })

    $('.link-menu-detalhes').click(function () {
        window.location.href = '/historicoabends/informe/pageInforme';
    })
});