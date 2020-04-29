<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title>SCORE PKI for OpenWeb 설치</title>
<link rel="stylesheet" href="/resources/plugin/ktnet/js/bs-3.3.5/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="/resources/plugin/ktnet/js/bs-3.3.5/css/bootstrap-theme.css" type="text/css">
<link rel="stylesheet" href="/resources/plugin/ktnet/css/openWebCommon.css" type="text/css">
<link rel="stylesheet" href="/resources/plugin/ktnet/css/openWebInstall.css" type="text/css">
<script type="text/javascript" src="/resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/resources/plugin/ktnet/js/bs-3.3.5/js/bootstrap.js"></script>
<script type="text/javascript" src="/resources/plugin/ktnet/js/nxts/nxts.min.js"></script>
<script type="text/javascript" src="/resources/plugin/ktnet/js/nxts/nxtspki_config.js"></script>
<script type="text/javascript">nxTSConfig.disableInstallConfirmMessage = true;</script>
<script type="text/javascript" src="/resources/plugin/ktnet/js/nxts/nxtspki.js"></script>
</head>
<body>
<ul id="nxtsdemoinstall">
	<li> SCORE PKI for OpenWeb 프로그램 설치 </li>
	<li><img src="/resources/plugin/ktnet/images/openWeb/install.png"></li>
	<li>
		<dl>
			<dt>설정정보 </dt>
			<dd>
				<div class="panel-body">
					<div id="configInfo"> </div>
				</div>
			</dd>
		</dl>
	</li>
	<li>
		<dl>
			<dt>브라우저 정보 </dt>
			<dd>
				<div class="panel-body">
					<div id="browserInfo"> </div>
				</div>
			</dd>
		</dl>
	<li> <a class="btn01" onClick="download_setup();" >설치파일 다운로드</a> </li>
	<li> <a class="btn02" href="/index.jsp">처음으로</a> <a class="btn02" href="javascript:window.history.back();" >뒤로</a> </li>
</ul>


<script>
	var checkTimer;
	$(document).ready(function(){
		function installComplete(res,data) {
			$("#nxVersion").html(res.data.nx);
			$("#pkiVersion").html(res.data.tstoolkit);

			if(res.code == nxTSError.res_success) {
				checkTimer.stop();
				alert("SCORE PKI for OpenWeb 프로그램이 설치 되었습니다.")
// 				window.location.href = "/index.jsp";
				window.history.back();
			}
		}
		checkTimer = new nxTSUtil.timer(3000,function(){
			nxTSCommon.installCheck(false,{ajaxto:2500,success:installComplete,versionCheck:[nxTSConfig.TSXMLTOOLKIT]});
		});
		checkTimer.start();

		updateConfigInfo("configInfo");
		updateBrowserInfo("browserInfo");
	});
	
	function download_setup() {
		window.location.href = "/toolkit/nxtspkisetup.exe";
	}

	function updateConfigInfo(target) {
		var ul = $("<ul></ul>");
		ul.append($('<li><span class="key">NX 권장 버전 </span><span class="val uninstall">'+nxTSPKIConfig.version.nx+' ( 현재 버전 : <span id="nxVersion" class=""></span> )</span></li>'));
		ul.append($('<li><span class="key">TSPKI 권장 버전</span><span class="val uninstall">'+nxTSPKIConfig.version.tstoolkit+' ( 현재 버전 : <span id="pkiVersion" class=""></span> )</span></li>'));
		ul.append($('<li><span class="key">Service URL</span><span class="val">'+nxTSConfig.getServiceUrl()+'</span></li>'));
		$("#"+target).append(ul);
	}

	function updateBrowserInfo(target) {
		var ul = $("<ul></ul>");
		ul.append($('<li><span class="key">Platform</span><span class="val">'+navigator.platform+'</span></li>'));
		ul.append($('<li><span class="key">AppVersion</span><span class="val">'+navigator.appVersion+'</span></li>'));
		ul.append($('<li><span class="key">UserAgent</span><span class="val">'+navigator.userAgent+'</span></li>'));
		$("#"+target).append(ul);
	}

</script>
</body>
</html>
