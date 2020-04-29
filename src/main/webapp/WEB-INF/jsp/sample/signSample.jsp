<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="/resources/plugin/ktnet/js/bs-3.3.5/js/bootstrap.js"></script>
<script type="text/javascript" src="/resources/plugin/ktnet/js/nxts/nxts.min.js"></script>
<script type="text/javascript" src="/resources/plugin/ktnet/js/nxts/nxtspki_config.js"></script>
<script type="text/javascript" src="/resources/plugin/ktnet/js/nxts/nxtspki.js"></script>

<script type="text/javascript" src="/resources/plugin/ktnet/js/openWebSignatureForPkcs7.js"></script>
<script type="text/javascript" src="/resources/plugin/ktnet/js/openWebSignatureForPki.js"></script>

<script type="text/javascript">
	
	var basicValidateOptions = {
		rules :{
		},	
	    messages: {
	    }
	};
	
	$(document).ready(function() {
		
	    nxTSPKI.init(true);
		
		$("#btnCSign").click(function(e) {
			execCSign();
	    });
		
		$("#btnAddCSign").click(function(e) {
			execAddCSign();
	    });
		
		
		$("#btnSSign").click(function(e) {
			execSSign();
	    });
		
		$("#btnAddSSign").click(function(e) {
			execAddSSign();
	    });
		
	});
	
	function execSSign(){
		
	}
	
	function execAddSSign(){
		
	}
	
	
	function execCSign(){
		var params = fnGetParams();
		
		var currDate = new Date().toISOString().slice(-24).replace(/\D/g,'').slice(0, 14);
        var isAllowCertShow = "Y"; // 지정된 OID를 가진 인증서만 팝업에 노출할지 여부('N'이면 모두 노출)
        
        var bizRegNo = $("#bizNo").val();
        
        signPkcs7Message($("#sourceValue").val(), bizRegNo, currDate, isAllowCertShow, function (result){
    		if(result[0]){ // 성공
    	        $("#signValue").val(result[1]); // 서명된 전자문서 데이터
    	        
    	        //$("#CERT_SERIAL").val(result[2].serialNumberDec);
    	        //$("#CERT_DN").val(result[2].subjectDN);
    	        
    		}else{ // 에러
    	        var msg = result[1];
    	        alert(msg);
    	    }
    		
    	}); 
			
	}
	
	function execAddCSign(){
		//var params = fnGetParams();
		
		var currDate = new Date().toISOString().slice(-24).replace(/\D/g,'').slice(0, 14);
        var isAllowCertShow = "Y"; // 지정된 OID를 가진 인증서만 팝업에 노출할지 여부('N'이면 모두 노출)
        
        var bizRegNo = $("#bizNo").val();
        
        addSignPkcs7Message($("#signValue").val(), bizRegNo, currDate, isAllowCertShow, function (result){
    		if(result[0]){ // 성공
    	        $("#addSignValue").val(result[1]); // 서명된 전자문서 데이터
    	        
    		}else{ // 에러
    	        var msg = result[1];
    	        alert(msg);
    	    }
    		
    	}); 
			
	}
	
	
	
</script>
</head>
<body style="background: white;">

<div class="tit-area">
	<h3>전자서명 샘플</h3>
	<ul class="location-area">
		<li><a href="#none" class="home">Home</a></li>
		<li><a href="#none">샘플메뉴</a></li>
		<li><a href="#none">전자서명 샘플</a></li>
		<li><strong>전자서명 샘플</strong></li>
	</ul>
</div>

<form id="frm">
<div class="layout-box">
	
	<div class="layout">
		
		<div class="sub-tit">
			<h4>전자서명</h4>
			<div class="icon-area">
				<button type="button" id="btnCSign" class="i-btn">clientSign</button>
				<button type="button" id="btnSSign" class="i-btn">serverSign</button>
				<button type="button" id="btnAddCSign" class="i-btn">addClientSign</button>
				<button type="button" id="btnAddSSign" class="i-btn">addServerSign</button>
				<button type="button" id="btnVerifySign" class="i-btn">verifySign</button>
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
					<th>식별값</th>
					<td>
						<input type="text" id="bizNo" value="1234567890" style="width: 100%" /> 
					</td>
				</tr>
				<tr style="height: 200px">
					<th>원본</th>
					<td>
						<textarea rows="8" id="sourceValue"></textarea>
					</td>
				</tr>
				<tr style="height: 200px">
					<th>서명값</th>
					<td>
						<textarea rows="8" id="signValue"></textarea>
					</td>
				</tr>
				<tr style="height: 200px">
					<th>Add 서명값</th>
					<td>
						<textarea rows="8" id="addSignValue"></textarea>
					</td>
				</tr>
				<tr style="height: 200px">
					<th>검증 서명값</th>
					<td>
						<textarea rows="8" id="verifySignValue"></textarea>
					</td>
				</tr>
				<tr style="height: 200px">
					<th>서명 검증</th>
					<td id="verifySignInfo">
						
					</td>
				</tr>
			</tbody>
			</table>
		</div>
	</div>
	
</div>
</form>

</body>
</html>