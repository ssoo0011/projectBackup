$(function () {
    $('.visitSpot').each(function () {
        let imageArray = $(this).find('.imgSrcArray').val().split(",");
        let html = '';

        for (let i = 0; i < imageArray.length; i++) {
            html += '<img class="spotImg" src="' + imageArray[i] + '" alt="사진">';
        }

        $(this).append(html);
    });

    $('.visitSpot').on('click', function () {
        let link = $(this).find('a').attr('href');
        window.location.href = link;
    });
});