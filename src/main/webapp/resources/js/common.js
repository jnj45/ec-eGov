/**
 * 
 */

var ajaxJsonCall = function(url, param, successCallback, errorCallback) {
	var contentType;
    var data;
    var dataType;

    if (typeof param == "string") {
        contentType = "application/json;charset=UTF-8";
        data = param;
        dataType = "json"
    } else {
        //contentType = "application/x-www-form-urlencoded;charset=UTF-8";
    	contentType = "application/json;charset=UTF-8";
    	data = JSON.stringify(param);;
        dataType = "json";
    }

    $.ajax({
    type : 'POST',
    url : url,
    contentType : contentType,
    data : data,
    dataType : dataType,
    cache: false,
    beforeSend:function(){
    },
    complete:function(){
    },
    success : function(data) {
        try {
            if (data) {
                if (data["status"] == "SUCC") {
                    if (typeof successCallback !== 'undefined') {
                        successCallback(data);
                    }
                } else if (data["status"] == "FAIL"){
                    
                    if (typeof errorCallback !== 'undefined') {
                        errorCallback(data);
                    }else{
                    	alert(data["errMsg"]);
                    }
                } else {
                    if (typeof successCallback !== 'undefined') {
                        successCallback(data);
                    }
                }
            }
        } catch (e) {
            alert(e.message);
        }
    },
    error : function(xhr, status, error) {
        if (401 === xhr.status) {
            alert('오류가 발생하였습니다.');
            location.href = "/";
        } else if (500 === xhr.status) {
        	alert('서버에 오류가 발생하여 요청을 수행할 수 없습니다. 시스템 관리자에게 문의 바랍니다.');
        } else {
            alert(error);
        }
        }
    });
};

var fnGetParams = function(isIncludeDisabled) {
    isIncludeDisabled = isIncludeDisabled || true;
    var params = {};
    $(':input').each(function() {
        if (this.id.indexOf('jqg')>-1) {
            //나중에 그리드 INPUT 받아서 처리시에 추가
        } else {
        	if (this.id != '') {
	            if (!isIncludeDisabled) {
	                if ('disabled' != $(this).attr('disabled')) {
	                    params[this.id] = $.trim($(this).val());
		            	if ( $(this).is('.decimal, .decimal1, .decimal2, .decimal3, .integer') ){
		            		params[this.id] = params[this.id].replace(/,/gi,"");
		            	}
	                }
	            } else {
	            	if (this.type=="checkbox") {
	            		if (this.checked) params[this.id] = $(this).val();
	            		else params[this.id] = "";
	            	} else if (this.type=="radio") {
	            		if( this.checked) params[this.id] = $(this).val();
	            	} else {
            			params[this.id] = $.trim($(this).val());		
	            	}
	            	if ( $(this).is('.decimal, .decimal1, .decimal2, .decimal3, .integer') ){
	            		params[this.id] = params[this.id].replace(/,/gi,"");
	            	}
	            }
        	}
        }
    });
    
    return params;
};


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
