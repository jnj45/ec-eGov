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
<%@ include file="/include/css.jsp" %>
<%@ include file="/include/script.jsp" %>
<script type="text/javascript">

var viewType = '${VIEW_TYPE}'; //페이지조회구분 C:등록, U:수정, R:조회
var isRegist = viewType=='C'?true:false;
var isEdit = viewType=='U'?true:false;
var isView = isEmpty(viewType)||viewType=='R'?true:false;

var gridView;

var author; //조회한 작가정보 json data.

$(function(){
	//alert('viewType:'+viewType);
	init();
	setEvent();
	setInitGrid();
	setViewData();
});
//페이지 초기화 작업
function init(){
	fnDx5View();
	initUploadFile();
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
	        	author = jObj.fields.author;
	            fnSetElementVal(author);
	            //에디터 내용
	        	DEXT5.setBodyValue(author.HISTORY, 'dx5');
	        	$("#HISTORY").html(author.HISTORY);
	        	$("#div_HISTORY").html(author.HISTORY);
	        	
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
	if (!confirm('<spring:message code="confirm.common.save"/>')){
		return;
	}
	var url = '';
	if (isRegist){
		url = "<c:url value='/sample/registAuthor.do'/>";
	}else{
		url = "<c:url value='/sample/updateAuthor.do'/>";
	}
	commitGrids();
	
	if (!checkValidation()){
		return false;
	}
	
	// 저장 파라미터 셋팅
	$("#HISTORY").val(DEXT5.getBodyValue("dx5")); //에디터 내용.
    var params = fnGetParams();
    
    // 책 목록
    $.extend(params, {"BOOK_LIST":gridView.getStateRows()});
    
    ajaxJsonCall(url, params, 
    	function(jObj){
            if (jObj.status == "SUCC") {
                alert('<spring:message code="success.common.save"/>');
                if (isRegist){
	                $("#AUTHOR_ID").val(jObj.fields.result.AUTHOR_ID); //등록 시 신규 키값 셋팅
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
var fnDx5View = function(){
    // 에디터에 example.xml 으로 로드합니다.
    DEXT5.config.EditorHolder = "editor_area"; //에디터가 위치할 div 태그 id
    DEXT5.config.SkinName = "gray";
    DEXT5.config.InitXml = "dext_work_editor2.xml";
    DEXT5.config.FileFieldID = "dx5";
    DEXT5.config.Width = "100%";
    DEXT5.config.Height = "320px";
    
    //DEXT5.config.FocusInitObjId = "section1"; //페이지로드 후 기본 포커스 이동이 필요한 경우 해당 html elementid
    
    new Dext5editor("dx5");
}
function initUploadFile(){
	var maxFileNum = 3;
	var multi_selector = new MultiSelector( document.getElementById('uploadingFileList'), maxFileNum ); //파일목록이 표시될 영역(div), 최대파일개수, 
	multi_selector.addElement( document.getElementById('fileUpload') ); //<input type="file">
}
<%-- function dext_editor_loaded_event(){
    var url1 = "<%=URL_IN%>/resources/css/wf_common.css";
    var url2 = "<%=URL_IN%>/resources/css/wf_reset.css";
    DEXT5.addUserCssUrl(url1, 'dx5');
    DEXT5.addUserCssUrl(url2, 'dx5');
} --%>
</script>
</head>
<body>
<h1>작가상세조회</h1>
<input type="button" id="btnRegist" value="등록"/>
<input type="button" id="btnSave"   value="저장"/>
<input type="button" id="btnClose"  value="닫기"/>
<h2>작가기본정보</h2>
<table width="100%">
	<tr>
		<td>작가ID</td><td><input type="text" id="AUTHOR_ID"/></td>
		<td>작가명</td><td><input type="text" id="AUTHOR_NM"/></td>
	</tr>
	<tr>		
		<td>생년월일</td><td><input type="text" id="BIRTH_DAY" class="datepicker"/></td>
		<td>데뷔년도</td><td><input type="text" id="DEBUT_YEAR"/></td>
	</tr>
	<tr>
		<td>작가이력</td>
	    <td colspan="3">
	    	<div id="editor_area"></div>
	        <div id="div_HISTORY" name="div_HISTORY" style="display:none;"></div>
	        <textarea id="HISTORY" name="HISTORY" style="display:none;"></textarea><!-- 서버로 전송할 에디터내용 -->
	    </td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td colspan="3">
			<div>
				<input name="fileUpload" id="fileUpload" type="file" multiple/><!-- 첨부파일명 입력 -->
				<div id="uploadingFileList"></div>
			</div>
		</td>
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