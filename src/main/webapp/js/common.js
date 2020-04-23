
$(function(){		

	//datepicker 달력
	$.datepicker.regional['ko'] = {
		prevText : '이전달',
		nextText : '다음달',
		monthNames : [  '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
		monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
		dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
		dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
		dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
		firstDay : 0,
		yearSuffix : '',
		showAnim : "slideDown"  
 	};
	$.datepicker.setDefaults( $.datepicker.regional[ "ko" ] );
    $('.date-inpt').datepicker( {
    	showOn: "both",
		buttonImage: "../../resource/images/common/icon_calendar.png",
		buttonImageOnly: true,
        changeMonth: true,
        changeYear: true,
        showButtonPanel: false,
        dateFormat: 'yy-mm-dd',
		beforeShow: function(input, inst) {
			$('#ui-datepicker-div').removeClass('hide-calendar');
		},
    });	

	//LNB menu 아코디언
	$('.lnb-menu li.active').addClass('open').children('ul').show();
	$('.lnb-menu li.has-sub>a').on('click', function(){
		//$(this).removeAttr('href');
		var element = $(this).parent('li');
		if (element.hasClass('open')) {
			element.removeClass('open');
			element.find('li').removeClass('open');
			element.find('ul').slideUp(200);
		}
		else {
			element.addClass('open');
			element.children('ul').slideDown(200);
			element.siblings('li').children('ul').slideUp(200);
			element.siblings('li').removeClass('open');
			element.siblings('li').find('li').removeClass('open');
			element.siblings('li').find('ul').slideUp(200);
		}
	});
	
	//lnb 열고/닫기
	$(".lnb-btn").click(function(){
		if(!$(this).hasClass("on")){
			$(".lnb-area").css("margin-left","-210px");
			$(".content").css("margin-left","20px");
			$(this).addClass("on");
		}else{
			$(".lnb-area").css("margin-left","0");
			$(".content").css("margin-left","240px");
			$(this).removeClass("on");
		}
	});
	/*
	//gnb toggle
	$(".gnb-btn").click(function(){
		if(!$(this).hasClass("on")){
			$(".header-wrap").css("transform","translatey(-100px)");
			$(".gnb-btn").css("transform","translatey(-100px)");
			$(".content").css("margin-top","-100px");
			$(".main-cont").css("margin-top","-100px");
			$(".lnb-area").css("top","0");
			$(".lnb-btn").css("top","0");
			$(this).addClass("on");
		}else{
			$(".header-wrap").css("transform","translatey(0)");
			$(".gnb-btn").css("transform","translatey(0)");
			$(".content").css("margin-top","0");
			$(".main-cont").css("margin-top","0");
			$(".lnb-area").css("top","100px");
			$(".lnb-btn").css("top","100px");
			$(this).removeClass("on");
		}
	});*/

	/*$(".gnb-btn").click(function(){
		if(!$(this).hasClass("on")){
			$(".header-wrap").css("margin-top","-100px");
			$(this).addClass("on");
		}else{
			$(".header-wrap").css("margin-top","0");
			$(this).removeClass("on");
		}
	});*/

	//gnb 열고/닫기
	$(".gnb-btn").click(function(){
		if(!$(this).hasClass("on")){
			$(".header-wrap").hide();
			$(".lnb-area").css("top","0");
			$(".lnb-btn").css("top","0");
			$(this).addClass("on");
		}else{
			$(".header-wrap").show();
			$(".lnb-area").css("top","100px");
			$(".lnb-btn").css("top","100px");
			$(this).removeClass("on");
		}
	});
	

	// tab 탭
	$(".tab-cont").hide();
    $(".tab-cont:first").show();

    $(".tab-area li").click(function () {
        $(".tab-area li").removeClass("tab-on");
        $(this).addClass("tab-on");
        $(".tab-cont").hide();
        var activeTab = $(this).attr("rel");
        $("#" + activeTab).fadeIn();
    });
	
	
	/*var now = 0;

	$(".user-selec button").click(function(){
		
		if(now == 0){		
			$(this).addClass("selec-on");
			$(".user-selec ul").slideDown(200);
			
			now=1;
			return false;
		}
		else{
			$(this).removeClass("selec-on");
			$(".user-selec ul").slideUp(200);

			now=0;
			return false;
		}

	});*/

	//메인 select 박스 열고/닫기
	$(".selec-box button").click(function(){

		var submenu = $(this).next("ul");

		if( submenu.is(":visible") ){
			submenu.slideUp(200);
			$(this).removeClass("selec-on");
		}else{
			submenu.slideDown(200);
			$(this).addClass("selec-on");
		}
	});


});


