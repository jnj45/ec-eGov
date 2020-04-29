var signedXmlData = "";
var rValue = "";
var orgTextData = "";
var systemDate = "";
var signedData = "";
var isAllowCertShowFlag = "N";
var certInfo = null;
var funcSignCallback;
var oid = new Array();

// 일반 전자서명
var signMessage = function(orgData, ssn, currDate, isAllowCertShow, funcCallback){
	
	var now = new Date();
	var today = now.getFullYear() + "" + fncLPAD(now.getMonth()+1) + "" + fncLPAD(now.getDate());
	
	// 본인확인용 사업자등록번호
	var options = {};
	options.ssn = ssn;
	orgTextData = orgData;	
	funcSignCallback = funcCallback;
	isAllowCertShowFlag = isAllowCertShow;
	systemDate = currDate;
	
	if(Number(today) != Number(systemDate.substring(0, 8))){
    	result[0] = false;
		result[1] = "사용자 컴퓨터의 날짜가 현재일자가 아닙니다. 변경 후 다시 진행하세요.";
		funcSignCallback(result);
		return;
    }
	
	// 인증서 팝업 시 허용되는 인증서만 띄울지 전체를 띄울지 구분
	if(isAllowCertShowFlag == "Y"){
		nxTSPKIConfig.options.initPolicies = "1 2 410 200012 1 1 3:범용기업|1 2 410 200004 5 1 1 7:범용기업|1 2 410 200005 1 1 5:범용기업|1 2 410 200004 5 2 1 1:범용기업|1 2 410 200004 5 4 1 2:범용기업|1 2 410 200004 5 3 1 1:범용기관|1 2 410 200004 5 3 1 2:범용기업|1 2 410 200012 5 22 1 31:SK실트론(특수목적용)|1 2 410 200012 5 22 1 32:SK실트론(특수목적용)";
	}else{
		nxTSPKIConfig.options.initPolicies = "";
		oid = new Array();
		oid.push("1.2.410.200004.5.2.1.1");		 // 한국정보인증(법인)
		oid.push("1.2.410.200004.5.1.1.7");		 // 증권전산(법인)
		oid.push("1.2.410.200005.1.1.5");		 // 금융결제원(법인)
		oid.push("1.2.410.200004.5.3.1.2");		 // 한국전산원(법인)
		oid.push("1.2.410.200004.5.4.1.2");		 // 전자인증(법인)
		oid.push("1.2.410.200012.1.1.3");		 // 한국무역정보통신(법인)    
		oid.push("1.2.410.200012.5.22.1.31");    // SK실트론(특수목적용)
		oid.push("1.2.410.200012.5.22.1.32");    // SK실트론(특수목적용)
	}
	
	nxTSPKI.generateSignature("101", orgTextData, options, callbackSignMessage);
	
};

// 일반 전자서명 검증
var verifySigData = function(){
	
	nxTSPKI.verifySignature("101", orgTextData, signedData, certInfo.cert, callbackVerifySigData);
	
};

// 일반 전자서명 Callback
var callbackSignMessage = function(res){
	
	var result = new Array(3);
	var validFrom = "";
	var validTo = "";
	
	if(res.code == 0){

		signedData = res.data.signature;
		certInfo = res.data.certInfo;
		validFrom = certInfo.validFrom.replace(/:/g, "").replace(/-/g, "").replace(/ /g, "");
		validTo = certInfo.validTo.replace(/:/g, "").replace(/-/g, "").replace(/ /g, "");
		
		// 인증서 OID 및 만료일 체크
		if(systemDate < validFrom){
			result[0] = false;
			result[1] = "아직 사용을 할 수 있는 인증서가 아닙니다.\n사용가능일 : " + certInfo.valid;
			funcSignCallback(result);
		}else if(systemDate > validTo){
			result[0] = false;
			result[1] = "사용이 만료된 인증서입니다.\n사용가능일 : " + certInfo.valid;
			funcSignCallback(result);
		}else if(isAllowCertShowFlag == "N" && jQuery.inArray(certInfo.certPolicy.replace(/ /g, "."), oid) == -1){
			result[0] = false;
			result[1] = "허용되지 않는 정책의 인증서입니다.\n선택된 인증서의 정책(ODI) : " + certInfo.certPolicy.replace(/ /g, ".");
			funcSignCallback(result);
		}else{		
			verifySigData();
		}
	}else{
		result[0] = false;
		result[1] = res.errorMessage;
		funcSignCallback(result);
	}
	
};

// 전자서명 검증
var callbackVerifySigData = function(res){
	
	var result = new Array(4);
	
	if (res.code == 0) {
		result[0] = true;
		result[1] = signedData;
		result[2] = certInfo.cert;
		result[3] = certInfo;
		funcSignCallback(result);
	}else{
		result[0] = false;
		result[1] = res.errorMessage;
		funcSignCallback(result);
	}
	
};

var objectToTable = function(caption, data) {
    caption = caption || "";
    var table = '<table class="custom table table-striped" style="width:100%;table-layout:fixed"><captoin>'+caption+'</captoin><col width=30% ></col><col width=70%></col>';
    for(var prop in data) {
        table += '<tr><td>';
        table += prop;
        table += '</td><td style="text-overflow: ellipsis;overflow: hidden;white-space: nowrap;">';
        if(typeof data[prop] == "object") {
            table += objectToTable("",data[prop]);
        }
        else {
            table += data[prop];
        }
        table += '</td></tr>';

    }
    table += '</table>';
    return table;
};

var fncLPAD = function(num){
	if(num < 10){
		return '0' + num;
    }else{
		return '' + num;
    }
};