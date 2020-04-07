<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/jquery-ui.css' />" /> 
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/jquery-ui.theme.css'/>"/>
<script src="<c:url value='/resources/js/jquery-3.4.1.min.js'/>"></script>
<script src="<c:url value='/resources/js/common.js'/>"></script>
<!-- realGrid -->
<script type="text/javascript" src="<c:url value='/resources/plugin/realgrid/realgridjs-lic.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/plugin/realgrid/realgridjs.1.1.33.min.js'/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/resources/plugin/realgrid/realgridjs_eval.1.1.33.min.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='/resources/plugin/realgrid/realgridjs-api.1.1.33.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/plugin/realgrid/jszip.min.js'/>"></script>
<script type="text/javascript">
$(function(){
	alert('document ready!');
	init();
	setEvent();
	setInitGrid();
});
//페이지내용 및 데이타 초기화 작업
function init(){
	
}
//이벤트 맵핑
function setEvent(){
	$("#btnSearch").click(function(e){
		searchList();
	});	
}
//그리드 초기화
function setInitGrid(){
	
}
//목록조회
function searchList(){
	
}
</script>
</head>
<body>
<h1>책 목록2</h1>
<input type="button" id="btnSearch" value="조회"/>

</body>
</html>