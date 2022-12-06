let timelength = $("#timeLength").val(); 
let endDateCheck = new Date( $("#cobi").val());

let endDate = new Date(endDateCheck.getTime() + (timelength*24*3600*1000));    

// [0 ]승인 - 기간만 가져오면됨
// [0] 승인 후 now() + 기간*24*3600*1000 으로 마감시간 만듬
// [ ] 0되면 deadline 1로 변경하도록
let haveDday = () => { 
    let now = new Date();
    let timer = document.querySelector("#timertest")
      // 현재날짜
    let dateGap = endDate.getTime() - now.getTime();
    // D-Day 날짜에서 현재 날짜의 차이를 getTime 메서드를 사용해서 밀리초의 값으로 가져온다.
    // console.log("r"+ timerStart);
    // console.log(now.getTime())
    console.log(now.getTime());
    // Math.floor 함수를 이용해서 근접한 정수값을 가져온다.
    // 밀리초 값이기 때문에 1000을 곱한다. 
    // 1000*60 => 60초(1분)*60 => 60분(1시간)*24 = 24시간(하루)
    // 나머지 연산자(%)를 이용해서 시/분/초를 구한다.
    let day = Math.floor(dateGap / (1000*60*60*24));   
    let hours = Math.floor((dateGap % (1000*60*60*24)) / (1000*60*60));   // 하루단위로 나누고 나온 나머지는 시,분,초가 나온다.
    let minutes = Math.floor((dateGap % (1000*60*60)) / (1000*60));   // 마찬가지로 시간단위로 나누고 나온 나머지는 분,초가 나온다.
    let seconds = Math.floor(dateGap % (1000*60) / 1000);   // 분단위로 나누고 나온 나머지는 초 가 나온다.
    $(".timer").innerHTML = `${day}<span>일</span> ${hours}<span>시간</span> : ${minutes}<span>분</span> : ${seconds}<span>초</span> 남았습니다.`;
    // console.log(timer.val());
    timer.innerHTML = "남은시간"+'<strong><span id="d-day-day0">' +`0${day}</span>일<span id="d-day-hour0"> ${hours}</span> : <span id="d-day-min0"> ${minutes}</span> : <span id="d-day-sec0"> `+`${seconds}</span></strong>`;
};

  let countTime = () => {  // countTime함수 생성해서
      setInterval(haveDday, 1000);    // setInterval 메서드에서 haveDday함수를 1초(1000밀리초)마다 실행(호출)한다.
  };

$(document).ready(function() {
    countTime();
})

  