$(function () {

    let city = $('#city').val()
    let state = $('#state').val()

    $('.contentTypeId').on('click', function (){

        let contentTypeId = $(this).val()

        $.ajax({
            url: '/scheduleType',
            type:'post',
            data:{
                contentTypeId : contentTypeId,
                city : city,
                state : state
            },
            success:function (itemMap){
                $('.placeDetail').remove()
                // JSON 데이터를 기반으로 템플릿 생성
                let html = '';

                itemMap.forEach(function(item) {

                    html += '<div class="placeDetail">' +
                        '<div class="title">' + item.title + '</div><br>' +
                        '<div class="addr">' + item.addr1 + '</div><br>' +
                        '<input type="hidden" class="x" value="' + item.mapx + '">' +
                        '<input type="hidden" class="y" value="' + item.mapy + '">' +
                        '<img class="spotImg" src="' + item.firstimage + '" alt="사진">' +
                        "<button class='addPlace' type='button'>+</button>" +
                        '</div>';
                });

                // 동적으로 생성된 템플릿을 DOM에 추가
                $('#place').append(html);
            }
        })
    })


    let firstX = $('.x').first().val();
    let firstY = $('.y').first().val();

    let container = document.getElementById('map');
    let options = {
        center: new kakao.maps.LatLng(firstY, firstX),
        level: 7
    };

    let map = new kakao.maps.Map(container, options);
    let mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(firstY, firstX), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다


    // 아래 코드는 지도 위의 마커를 제거하는 코드입니다
    // marker.setMap(null);

    $(document).on('click', '.addPlace', function () {

        let index = $('.addPlace').index(this);
        let spot = $(".title").eq(index).text();
        let img = $(".spotImg").eq(index).attr('src');

        let x = $(".x").eq(index).val();
        let y = $(".y").eq(index).val();

        // 해당 장소 있는지 확인
        let exists = $('.visitSpot:contains(' + spot + ')').length > 0;

        if (!exists) {
            // 추가하지 않은 장소만 추가
            $("#addSchedule").append("<div class='visitSpot' name='visitSpot'>" + spot +
                "<br><img src =  '" + img + "'>"+
                "<br><button class = 'delete' type='button'>X</button>" +
                "<button class = 'up' type='button'>▲</button>" +
                "<button class = 'down' type='button'>▼</button>" +
                "<input type='hidden' name='visitSpot' value='" + spot + "'>"+
                "<input type='hidden' name='imgSrc' value='"+img+"'>"+
                "</div>");


            // 마커가 표시될 위치입니다
            let markerPosition  = new kakao.maps.LatLng(y, x);
            // 마커가 지도 위에 표시되도록 설정합니다
            let marker1 = new kakao.maps.Marker({
                position: markerPosition
            });
            // 마커를 생성합니다
            marker1.setMap(map);
            makers.push(marker1);


        } else alert('이미 추가한 장소입니다.')
    });

    $(document).on('click', '.up', function () {
        let currentElement = $(this).parent(); // 현재 요소
        let prevElement = currentElement.prev(); // 바로 위 요소

        if (prevElement.length > 0) {
            prevElement.before(currentElement); // 위치 변경
        }
    });

    $(document).on('click', '.down', function () {
        let currentElement = $(this).parent(); // 현재 요소
        let prevElement = currentElement.next(); // 바로 위 요소

        if (prevElement.length > 0) {
            prevElement.after(currentElement); // 위치 변경
        }
    });

    $(document).on('click', '.delete', function () {
        $(this).parent().remove(); //현재요소
    });

});