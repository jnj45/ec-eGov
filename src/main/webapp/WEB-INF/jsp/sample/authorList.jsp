<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/jquery-ui.css' />" /> 
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/jquery-ui.theme.css'/>"/>
<script src="<c:url value='/resources/js/jquery-3.4.1.min.js'/>"></script>
<!-- ===== realGrid  start ===== -->
<script type="text/javascript" src="<c:url value='/resources/plugin/realgrid/realgridjs-lic.js'/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/resources/plugin/realgrid/realgridjs.1.1.33.min.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='/resources/plugin/realgrid/realgridjs_eval.1.1.33.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/plugin/realgrid/realgridjs-api.1.1.33.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/plugin/realgrid/jszip.min.js'/>"></script>
<!-- ===== realGrid  end ===== -->
<script src="<c:url value='/resources/js/site_define.js'/>"></script>
<script src="<c:url value='/resources/js/common.js'/>"></script>
<script src="<c:url value='/resources/js/popup.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/rgrid.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/rtgrid.js'/>"></script>
<script type="text/javascript">

var gridView;

$(function(){
	//alert('document ready!');
	init();
	setEvent();
	setInitGrid();
});
//페이지 초기화 작업
function init(){
	
}
//이벤트 맵핑
function setEvent(){
	$("#btnTest").click(function(e){
		test();
	});	
	$("#btnSearch").click(function(e){
		searchList();
	});	
}
//그리드 초기화
function setInitGrid(){
	var gridId = "gridList"; //div id
    gridView = new RealGridJS.GridView(gridId);
    
    var colModel = [];
    // obj, fieldName, headerStyle, width, dataType, colStyles, extraOption(visible, sortable 등), editType, editOption
    addField(colModel, 'AUTHOR_VIEW',      {text: '상세보기'},       60, 'popupLink');
    addField(colModel, 'AUTHOR_ID',        {text: '작가ID'},        100, 'text',  {textAlignment: 'center'});
    addField(colModel, 'AUTHOR_NM',        {text: '작가명'},        100, 'text',  {textAlignment: 'center'});
    addField(colModel, 'BIRTH_DAY',        {text: '생년월일'},      100, 'text',  {textAlignment: 'center'});
    addField(colModel, 'DEBUT_YEAR',       {text: '데뷔년도'},      100, 'text',  {textAlignment: 'center'});
    addField(colModel, 'TOT_ACCMLT_INCME', {text: '총누적수입'},    100, 'text',  {textAlignment: 'center'});
    addField(colModel, 'HISTORY',          {text: '작가이력'},      100, 'text',  {textAlignment: 'center'});
    addField(colModel, 'LAST_UPDT_PNTTM',  {text: '최종수정일시'},  100, 'text',  {textAlignment: 'center'});
    addField(colModel, 'LAST_UPDUSR_ID',   {text: '최종수정자ID'},  100, 'text',  {textAlignment: 'center'});
    
    gridView.rgrid({
         gridId         : gridId    //required 그리드 ID
        ,height         : 300       //required 그리드 높이
        ,columns        : colModel  // required
        ,checkBar       : false     //default true   앞쪽 체크박스 표시 여부
        ,editable       : false     //default false 그리드 전체 에디트 여부
        ,selectStyle    : "block"   //default singleRow 그리드 선택기능 block:BLOCK ,rows:ROWS , columns:COLUMNS , singleRow:SINGLE_ROW ,singleColumn:SINGLE_COLUMN,  none: NONE
        ,indicatorDp    : "index"   //default "index" 컬럼번호 표시 방식 : none, row, index(정렬무시)
        ,copySingle     : false // default ture : 복사하지 않음
        ,autoHResize    : true     //윈도우 높이가 변경시 그리드 전체 높이 자동 조절
        ,viewCount      : true      //그리드 건수 표현
    });
    
    gridView.onDataCellClicked =  function (grid, index) {
        if (index.column == "AUTHOR_VIEW") {
        	authorView();
        }
    };
}
//목록조회
function searchList(){
	gridView.showProgress();
    var params = fnGetParams();
    ajaxJsonCall('<c:url value="/sample/selectAuthorList.do"/>', params, 
    	function(data){
	    	if (typeof data.status != 'undefined' && 'SUCC' !== data.status) {
	            alert(data.errMsg);
	            return;
	        }
	        gridView.setPageRows(data);
	        gridView.closeProgress();
    });
}
//상세조회(팝업)
function authorView(row) {
	var rowData = gridView.getCurrentRow();
    var params = {
    		AUTHOR_ID : rowData.AUTHOR_ID
    };
    $.extend(params, fnGetParams());
    fnPostPopup('<c:url value="/sample/authorViewPop.do"/>', params, 'authorViewPop', 800, 0); 
};

function test(){
	var params = fnGetParams();
	ajaxJsonCall('<c:url value="/sample/testTransaction.do"/>', params, 
    	function(data){
	    	if (typeof data.status != 'undefined' && 'SUCC' !== data.status) {
	            alert(data.errMsg);
	            return;
	        }
    });
}
</script>
</head>
<body>
<h1>작가목록1</h1>
<table>
	<tr>
		<td>작가명: <input type="text" id="AUTHOR_NM"/></td>
		<td>데뷔년도: <input type="text" id="DEBUT_YEAR_FROM"/> ~ <input type="text" id="DEBUT_YEAR_TO"/></td>
		<td><input type="button" id="btnSearch" value="조회"/><input type="button" id="btnTest" value="테스트"/></td>
	</tr>
</table>
<!-- realgrid 들어가는 영역 : S -->
<div class="realgrid-area">
    <div id="gridList"></div> 
</div>
<br>
pageUnit : ${pageUnit} / testProp : ${testProp} / testDbProp : ${testDbProp}<br>
</body>
</html>