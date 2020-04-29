<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	
	var basicValidateOptions = {
		rules :{
		},	
	    messages: {
	    	  item1: { required: "항목1이 누락되었습니다." }
	        , item2:{ required: "항목2이 누락되었습니다." }
	        , item3: { required: "항목3이 누락되었습니다." }
	        , item4: { required: "항목4이 누락되었습니다." }
	    }
	};
	
	$(document).ready(function() {
		
		$("#btnView").click(function(e) {
			viewTemplate();
	    });		
	});
	
	function viewTemplate(){
		var params = fnGetParams();
		
		var url = "/sample/getTemplate.do";
		
		if(validateForm("frm",basicValidateOptions)){
			
			 ajaxJsonCall(url, params, 
		    	function(jObj){
				 	$("#htmlView").html(jObj.fields.contents);
		        }, 
		        function(jObj){
		        	//추가 에러처리
		        }
		    );
			
		}
		
		 //ajaxJsonCallAddValidate("/", param, function(){}, function(){}, "frm",validateOptons);
	}
	
</script>
</head>
<body style="background: white;">

<div class="tit-area">
	<h3>탬플릿 샘플</h3>
	<ul class="location-area">
		<li><a href="#none" class="home">Home</a></li>
		<li><a href="#none">샘플메뉴</a></li>
		<li><a href="#none">탬플릿 샘플</a></li>
		<li><strong>탬플릿 샘플</strong></li>
	</ul>
</div>

<form id="frm">
<div class="layout-box">
	
	<div class="layout">
		
		<div class="sub-tit">
			<h4>탬플릿 샘플</h4>
			<div class="icon-area">
				<button type="button" id="btnView" class="i-btn"><em class="icon03"></em>가져오기</button> 
			</div>
			
		</div>
		<div>
			<table class="tbl-style02">
			
			<colgroup>
				<col width="20%">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th><label for="samInput1">항목1</label></th>
					<td>
						<input type="text" id="item1" name="item1" style="width: 30%" required="required" />
					</td>
				</tr>
				<tr>
					<th><label for="saamInput2">항목2</label></th>
					<td>
						<input type="text" id="item2" name="item2" style="width: 30%" required="required" />
					</td>
				</tr>
				<tr>
					<th>항목3</th>
					<td><input type="text" id="item3" name="item3" style="width: 30%" required="required" /></td>
				</tr>
				<tr>
					<th>항목4</th>
					<td><input type="text" id="item4" name="item4" style="width: 30%" required="required" ></td>
				</tr>
			</tbody>
			</table>
			
		</div>
		<div id="htmlView" style="overflow: auto;height:300px"></div>
	</div>
	
</div>
</form>

</body>
</html>