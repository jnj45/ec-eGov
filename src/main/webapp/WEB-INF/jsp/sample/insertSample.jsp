<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	
	var validateOptons = {
	    messages: {
	    	  samInput1: { required: "토큰이 누락되었습니다." }
	        , saamInput2: { required: "행사명을 입력하세요." }
	        , samInput3: { required: "행사지역을 선택하세요." }
	        , samInput4: { required: "행사유형을 선택하세요." }
	    }
	};
	
	$(document).ready(function() {
		
		$("#btnSave").click(function(e) {
			sssave();
	    });		
	});
	
	function sssave(){
		var param = new Object();
		
		 ajaxJsonCallAddValidate("/", param, function(){}, function(){}, "frm",validateOptons);
	}
	
</script>
</head>
<body style="background: white;">

<div class="tit-area">
	<h3>샘플페이지</h3>
	<ul class="location-area">
		<li><a href="#none" class="home">Home</a></li>
		<li><a href="#none">샘플메뉴</a></li>
		<li><a href="#none">샘플 페이지</a></li>
		<li><strong>샘플 페이지</strong></li>
	</ul>
</div>

<form id="frm">
<div class="layout-box">
	
	<div class="layout">
		
		<div class="sub-tit">
			<h4>샘플</h4>
			<div class="icon-area">
				<button type="button" id="btnSave" class="i-btn"><em class="icon03"></em>저장</button> 
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
						<input type="text" id="samInput1" name="samInput1" style="width: 30%" required="required" />
					</td>
				</tr>
				<tr>
					<th><label for="saamInput2">항목2</label></th>
					<td>
						<input type="text" id="saamInput2" name="saamInput2" style="width: 30%" required="required" />
					</td>
				</tr>
				<tr>
					<th>항목3</th>
					<td><input type="text" id="samInput3" style="width: 30%" ></td>
				</tr>
				<tr>
					<th>항목4</th>
					<td><input type="text" id="samInput4" style="width: 30%" ></td>
				</tr>
			</tbody>
			</table>
			
		</div>
	</div>
	
</div>
</form>

</body>
</html>