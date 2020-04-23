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

var viewType = '${VIEW_TYPE}'; //페이지조회구분 C:등록, U:수정, R:조회
var isRegist = viewType=='C'?true:false;
var isEdit = viewType=='U'?true:false;
var isView = isEmpty(viewType)||viewType=='R'?true:false;

var gridView;

$(function(){
	//alert('viewType:'+viewType);
	init();
	setEvent();
	setInitGrid();
	setViewData();
});
//페이지 초기화 작업
function init(){
	
}
//이벤트 맵핑
function setEvent(){
	$("#btnRegist").click(function(e){
		save();
	});
	$("#btnSave").click(function(e){
		save();
	});
	$("#btnAddBook").click(function(e){
		addBook();
	});
	$("#btnDelBook").click(function(e){
		deleteBook();
	});
	$("#btnClose").click(function(e){
		self.close();
	});
}
//그리드 초기화
function setInitGrid(){
	var gridId = "gridList"; //div id
    gridView = new RealGridJS.GridView(gridId);
    
    var colModel = [];
    // obj, fieldName, headerStyle, width, dataType, colStyles, extraOption(visible, sortable 등), editType, editOption
    addField(colModel, 'BOOK_ID',         {text: '책ID'},        100, 'text',  {textAlignment: 'center'});
    addField(colModel, 'AUTHOR_ID',       {text: '작가ID'},      100, 'text',  {textAlignment: 'center'});
    addField(colModel, 'TITLE',           {text: '책제목'},      100, 'text',  {textAlignment: 'center'});
    
    gridView.rgrid({
         gridId         : gridId    //required 그리드 ID
        ,height         : 300       //required 그리드 높이
        ,columns        : colModel  // required
        ,checkBar       : true     //default true   앞쪽 체크박스 표시 여부
        ,editable       : true     //default false 그리드 전체 에디트 여부
        ,selectStyle    : "block"   //default singleRow 그리드 선택기능 block:BLOCK ,rows:ROWS , columns:COLUMNS , singleRow:SINGLE_ROW ,singleColumn:SINGLE_COLUMN,  none: NONE
        ,indicatorDp    : "index"   //default "index" 컬럼번호 표시 방식 : none, row, index(정렬무시)
        ,copySingle     : false 	// default ture : 복사하지 않음
        ,autoHResize    : true     //윈도우 높이가 변경시 그리드 전체 높이 자동 조절
        ,viewCount      : true      //그리드 건수 표현
    });
    
    gridView.onDataCellClicked =  function (grid, index) {
    };
}
//데이타 조회
function setViewData(){
	if (isRegist){
		
	} else {
		var params = {"AUTHOR_ID":"${param.AUTHOR_ID}"};
        ajaxJsonCall("<c:url value='/sample/selectAuthor.do'/>", params, 
        	function(jObj){
	        	//작가정보 셋팅
	            fnSetElementVal(jObj.fields.author);
	            
	            //책 목록
	            jObj.fields.authorBookList.forEach(function(row){
	            	var rowId = gridView.addRow(row);
	            	gridView.setRowState(rowId, 'none'); //각 행의 데이타 상태 초기화.
	            });
            }, 
        	function(jObj){
            	alert('데이타 조회 중 오류가 발생하였습니다.\n'+jObj.errMsg);
        });
	 }
}
//책 추가
function addBook(){
	var rowId = gridView.addRow();
	var authorId = $("#AUTHOR_ID").val();
	gridView.setRowValue(rowId, 'AUTHOR_ID', authorId);
}
//책 삭제
function deleteBook(){
	//선택여부 체크
	var items = gridView.getSelectedItems();
	if (items.length == 0){
        alert('삭제할 책을 선택하세요.');
        return;
    }
	//행별 조건 체크
	var item;
	for(var i=0; i < items.length; i++){
		item = items[i];
		if (item.TITLE == 'book0'){
			alert('삭제불가능한 책입니다.');
			return;
		}
	}
	//선택한 행들 삭제.
	gridView.deleteSelectedRows();
}
//저장
function save(){
	commitGrids();
	
	if (!checkValidation()){
		return false;
	}
	var url = '';
	if (isRegist){
		url = "<c:url value='/sample/registAuthor.do'/>";
	}else{
		url = "<c:url value='/sample/updateAuthor.do'/>";
	}
	
	// 저장 파라미터 셋팅
    var params = fnGetParams();
    
    // 책 목록
    $.extend(params, {"BOOK_LIST":gridView.getStateRows()});
    
    ajaxJsonCall(url, params, 
    	function(jObj){
            if (jObj.status == "SUCC") {
                alert('<spring:message code="success.common.save"/>');
                if (isRegist){
	                $("#AUTHOR_ID").val(jObj.fields.AUTHOR_ID); //등록 시 신규 키값 셋팅
                }
                refreshPage(); //페이지 재조회
            } else {
                alert('<spring:message code="fail.common.save"/>\n' + jObj.errMsg);
            }
        }, 
        function(jObj){
        	//추가 에러처리
        }
    );
}
//유효성 체크
function checkValidation(){
	if (isEmpty($("#AUTHOR_NM").val())){
		alert('작가명을 입력하세요.');
		return false;
	}
	return true;
}
//그리드 변경데이타 commit
function commitGrids(){
	gridView.commit();
}
//페이지 리로드
function refreshPage() {
	if (typeof opener.searchList === "function"){
    	opener.searchList();
    }
    var url = '<c:url value="/sample/authorViewPop.do"/>';
    fnPostGoto(url, fnGetParams(), "_self");
}
</script>
</head>
<body>
<h1>작가상세조회</h1>
<input type="button" id="btnRegist" value="등록"/>
<input type="button" id="btnSave"   value="저장"/>
<input type="button" id="btnClose"  value="닫기"/>
<h2>작가기본정보</h2>
<table>
	<tr>
		<td>작가ID: <span id="s_AUTHOR_ID"></span><input type="text" id="AUTHOR_ID"/></td>
		<td>작가명: <span id="s_AUTHOR_NM"></span><input type="text" id="AUTHOR_NM"/></td>
	</tr>
	<tr>		
		<td>생년월일: <span id="s_BIRTH_DAY"></span><input type="text" id="BIRTH_DAY"/></td>
		<td>데뷔년도: <span id="s_DEBUT_YEAR"></span><input type="text" id="DEBUT_YEAR"/></td>
	</tr>
</table>
<h2>작가 책목록</h2>
<button type="button" id="btnAddBook" class="btn">책 추가</button>
<button type="button" id="btnDelBook" class="btn">책 삭제</button>
<!-- realgrid 들어가는 영역 : S -->
<div class="realgrid-area">
    <div id="gridList"></div> 
</div>
</body>
</html>