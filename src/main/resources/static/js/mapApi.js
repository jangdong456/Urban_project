


function mapMark() {

    var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(37.52869739824655, 126.66788179281549), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다    


    var positions = [
        {
            title: '루원호반베르디움', 
            "lat": 37.52869739824655,
            "lng": 126.66788179281549
        },
        {
            title: '루원시티프라움', 
            "lat": 37.526692295827814,
            "lng": 126.6661484895955
        },
        {
            title: '로지의원', 
            "lat": 37.52717999673433,
            "lng": 126.6697664807601
        },
        {
            title: 'sh수협은행',
            "lat": 37.52679244578908,
            "lng": 126.6697229373671
        },
    ];

    for (var i = 0; i < positions.length; i ++) {
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position : new kakao.maps.LatLng(positions[i].lat, positions[i].lng), // 마커의 위치
        });
    
        // 마커에 표시할 인포윈도우를 생성합니다 
        var infowindow = new kakao.maps.InfoWindow({
            content: positions[i].title // 인포윈도우에 표시할 내용
        });
    
        // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
        // 이벤트 리스너로는 클로저를 만들어 등록합니다 
        // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
        kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
        kakao.maps.event.addListener(marker, 'click', () => {
            alert("안녕하세요");
            create
        })
    }
    
    // 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
    function makeOverListener(map, marker, infowindow) {
        return function() {
            infowindow.open(map, marker);
        };
    }
    
    // 인포윈도우를 닫는 클로저를 만드는 함수입니다 
    function makeOutListener(infowindow) {
        return function() {
            infowindow.close();
        };
    }



    // for(i=0; i< positions.length; i++) {
        
    // }


    // var markers = positions.map(function(position) {  // 마커를 배열 단위로 묶음
    //     return new kakao.maps.Marker({
    //         position : new kakao.maps.LatLng(position.lat, position.lng),
    //         clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
    //     });
    // });

    // // 마커 클러스터러를 생성합니다 
    // var clusterer = new kakao.maps.MarkerClusterer({
    //     map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
    //     averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
    //     minLevel: 5, // 클러스터 할 최소 지도 레벨
    //     markers : markers,
    // });

    // clusterer.addMarkers(markers);


}