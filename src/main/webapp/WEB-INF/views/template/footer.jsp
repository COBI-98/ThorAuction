<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
  <footer class="row row-cols-1 row-cols-sm-2 row-cols-md-5 py-5 my-5 border-top">
    <div class="col mb-3">

    </div>

    <div class="col mb-3">
      <h5>Section</h5>
      <ul class="nav flex-column">
        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Home</a></li>
        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Features</a></li>
        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Pricing</a></li>
        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">FAQs</a></li>
        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">About</a></li>
      </ul>
    </div>

    <div class="col mb-3">
      <h5>Section</h5>
      <ul class="nav flex-column">
        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Home</a></li>
        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Features</a></li>
        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Pricing</a></li>
        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">FAQs</a></li>
        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">About</a></li>
      </ul>
    </div>

    <div class="col mb-3">
      <h5>Section</h5>
      <ul class="nav flex-column">
        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Home</a></li>
        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Features</a></li>
        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Pricing</a></li>
        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">FAQs</a></li>
        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">About</a></li>
      </ul>
    </div>
    <div class="second-menu">
                <div id="map" style="width:400px;height:200px;"></div>
                <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=67a15d0ede53def65514d5bb44685f5a"></script>
                <script>
                  var container = document.getElementById('map');
                  var options = {
                    center: new kakao.maps.LatLng(37.4787946468336, 126.88108315176632),
                    level: 3
                  };

                  var map = new kakao.maps.Map(container, options);

                  var markerPosition  = new kakao.maps.LatLng(37.4787946468336, 126.88108315176632); 

                  // 마커를 생성합니다
                  var marker = new kakao.maps.Marker({
                      position: markerPosition
                  });

                  // 마커가 지도 위에 표시되도록 설정합니다
                  marker.setMap(map);

                 // 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
                var iwContent = '<div style="padding:3px; font-size : 12px">구디 아카데미 - 토.실.대</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

                // 인포윈도우를 생성합니다
                var infowindow = new kakao.maps.InfoWindow({
                    content : iwContent
                });

                // 마커에 마우스오버 이벤트를 등록합니다
                kakao.maps.event.addListener(marker, 'mouseover', function() {
                  // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
                    infowindow.open(map, marker);
                });

                // 마커에 마우스아웃 이벤트를 등록합니다
                kakao.maps.event.addListener(marker, 'mouseout', function() {
                    // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
                    infowindow.close();
                });
                </script>
                <div></div>
                <!-- <div class="icon" style="color: rgb(0, 140, 255)">
                  <a style="color: rgb(0, 140, 255);" href="https://twitter.com/?lang=ko"><i class="fa-brands fa-twitter"></i></a>
                </div>
                <div class="icon" style="color: rgb(208, 34, 98)">
                  <a style="color: rgb(208, 34, 98);" href="https://www.instagram.com/"><i class="fa-brands fa-instagram-square"></i></a>
                </div>
                <div class="icon" style="color: rgb(0, 85, 255)">
                  <a style="color: rgb(0, 85, 255)" href="https://www.facebook.com/"><i class="fa-brands fa-facebook"></i></a>
                </div>
                <div></div> -->
            </div>
  </footer>
</div>
</div>