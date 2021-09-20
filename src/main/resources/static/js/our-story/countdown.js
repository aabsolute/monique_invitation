function counter() {
    var dday = new Date("Apr 7,2018,09:00:00").getTime(); //디데이
    setInterval(function() {
        var now = new Date(); //현재 날짜 가져오기
        var distance = dday - now;
        var d = Math.floor(distance / (1000 * 60 * 60 * 24));
        var h = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        var m = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var s = Math.floor((distance % (1000 * 60)) / 1000);
        if (s < 10) {
            s = '0' + s;
        }
        $('#clock').html('<div class="countdown_wrap d-flex"><div  class="single_countdown"><h3>' + d + '</h3><span>Days</span></div><div class="single_countdown"><h3>' + h + '</h3><span>Hours</span></div><div class="single_countdown"><h3>' + m + '</h3><span>Minutes</span></div><div class="single_countdown"><h3>' + s + '</h3><span>Seconds</span></div></div>')


    }, 1000);
}
counter();