(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner(0);


    // Fixed Navbar
    $(window).scroll(function () {
        if ($(window).width() < 992) {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow');
            } else {
                $('.fixed-top').removeClass('shadow');
            }
        } else {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow').css('top', -55);
            } else {
                $('.fixed-top').removeClass('shadow').css('top', 0);
            }
        } 
    });
    
    
   // Back to top button
   $(window).scroll(function () {
    if ($(this).scrollTop() > 300) {
        $('.back-to-top').fadeIn('slow');
    } else {
        $('.back-to-top').fadeOut('slow');
    }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Testimonial carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 2000,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav : true,
        navText : [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0:{
                items:1
            },
            576:{
                items:1
            },
            768:{
                items:1
            },
            992:{
                items:2
            },
            1200:{
                items:2
            }
        }
    });


    // vegetable carousel
    $(".vegetable-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1500,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav : true,
        navText : [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0:{
                items:1
            },
            576:{
                items:1
            },
            768:{
                items:2
            },
            992:{
                items:3
            },
            1200:{
                items:4
            }
        }
    });


    // Modal Video
//    $(document).ready(function () {
//        var $videoSrc;
//        $('.btn-play').click(function () {
//            $videoSrc = $(this).data("src");
//        });
//        console.log($videoSrc);
//
//        $('#videoModal').on('shown.bs.modal', function (e) {
//            $("#video").attr('src', $videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0");
//        })
//
//        $('#videoModal').on('hide.bs.modal', function (e) {
//            $("#video").attr('src', $videoSrc);
//        })
//    });



    // Product Quantity
    $('.quantity button').on('click', function () {
        var button = $(this);
        var oldValue = button.parent().parent().find('input').val();
        if (button.hasClass('btn-plus')) {
            var newVal = parseFloat(oldValue) + 1;
        } else {
            if (oldValue > 0) {
                var newVal = parseFloat(oldValue) - 1;
            } else {
                newVal = 0;
            }
        }
        button.parent().parent().find('input').val(newVal);
    });

    // 비밀번호 일치 여부를 확인하는 함수
    function checkPasswordMatch() {
        var password = $("#password").val();
        var confirmPassword = $("#password2").val();

        // 비밀번호가 일치하는지 확인
        if (password != confirmPassword) {
            // 비밀번호가 일치하지 않는 경우, 사용자에게 메시지 표시
            $("#password2")[0].setCustomValidity("비밀번호가 일치하지 않습니다.");
        } else {
            // 비밀번호가 일치하는 경우, 사용자에게 메시지 제거
            $("#password2")[0].setCustomValidity("");
        }
    }

    // 페이지가 로드될 때 비밀번호 일치 여부 확인
    $(document).ready(function() {
        // 두 번째 비밀번호 필드에 대한 입력 이벤트 리스너 추가
        $("#password2").on("input", checkPasswordMatch);
    });

// 별점 눌렀을 때 노란색으로 바뀜
 $(document).ready(function() {
     const ratingStars = $(".rating__star");
     const ratingResult = $(".rating__result");
     const reviewScoreInput = $("#reviewScore");

     printRatingResult(ratingResult);

     function executeRating(stars, result) {
         const starClassActive = "fas fa-star";
         const starClassUnactive = "far fa-star";
         const starsLength = stars.length;

         stars.each(function(index, star) {
             $(star).on("click", function() {
                 let i = stars.index($(star));

                 if ($(star).hasClass(starClassUnactive)) {
                     printRatingResult(result, i + 1);
                     reviewScoreInput.val(i + 1); // 숨겨진 입력 필드에 값을 설정
                     for (i; i >= 0; --i) $(stars[i]).removeClass(starClassUnactive).addClass(starClassActive);
                 } else {
                     printRatingResult(result, i);
                     reviewScoreInput.val(0); // 클릭이 취소된 경우 값 제거
                     for (i; i < starsLength; ++i) $(stars[i]).removeClass(starClassActive).addClass(starClassUnactive);
                 }
             });
         });
     }

     function printRatingResult(result, num = 0) {
         result.text(`${num}/5`);
     }

     executeRating(ratingStars, ratingResult);
 });

})(jQuery);


