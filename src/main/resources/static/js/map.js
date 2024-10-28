
$(document).ready(function () {
            var mapContainer = document.getElementById('map'),
                mapOption = {
                    center: new kakao.maps.LatLng(37.478568, 126.864732), // 기본 지도 중심 좌표 (광명시청)
                    level: 6 // 지도의 확대 레벨
                };

            var map = new kakao.maps.Map(mapContainer, mapOption);
            var clusterer = new kakao.maps.MarkerClusterer({
                map: map,
                averageCenter: true,
                minLevel: 5
            });

            // 아파트 데이터를 가져와서 지도에 표시
            $.ajax({
                url: '/apartments/json', // JSON 형식으로 데이터를 제공하는 엔드포인트
                method: 'GET',
                success: function (data) {
                    var markers = [];
                    data.forEach(function (apartment) {
                        if (apartment.ycoordinate && apartment.xcoordinate) {
                            var marker = new kakao.maps.Marker({
                                position: new kakao.maps.LatLng(apartment.ycoordinate, apartment.xcoordinate),
                                title: apartment.apartmentName
                            });
                            markers.push(marker);
                        }
                    });
                    clusterer.addMarkers(markers);
                },
                error: function () {
                    console.error('아파트 데이터를 불러오는 데 실패했습니다.');
                }
            });
        });