<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/plugin/ktnet/js/bs-3.3.5/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="/resources/plugin/ktnet/js/bs-3.3.5/css/bootstrap-theme.css" type="text/css">
<link rel="stylesheet" href="/resources/plugin/ktnet/css/openWebCommon.css" type="text/css">
<script type="text/javascript" src="/resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/resources/plugin/ktnet/js/bs-3.3.5/js/bootstrap.js"></script>
<script type="text/javascript" src="/resources/plugin/ktnet/js/nxts/nxts.min.js"></script>
<script type="text/javascript" src="/resources/plugin/ktnet/js/nxts/nxtspki_config.js"></script>
<script type="text/javascript" src="/resources/plugin/ktnet/js/nxts/nxtspki.js"></script>
</head>
<body>
<script type="text/javascript">
	//초기화 함수 필수
    $(document).ready(function(){
    	nxTSPKI.onInit(function(){ 
			top.location = "/index.jsp";
		});

    	nxTSPKI.init(true);
    });	
</script>
</body>
</html>
