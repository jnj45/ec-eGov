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
	$("#btnTest").click(function(e){
		//uploadFile();
		fn_check_uploadFiles();
	});
/* 	$('#btn-upload').click(function (e) {
		e.preventDefault();
		$('#file').click();
	}); */
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
	            
	            //첨부파일
	            displayFileList(author.ATCH_FILE_ID_A, 'fileList_A', true);
	            displayFileList(author.ATCH_FILE_ID_B, 'fileList_B', true);
	            
	            //조회 후 상태 등에 따른 화면표시제어
	            setDisplayElement();
	            
            }, 
        	function(jObj){
            	alert('데이타 조회 중 오류가 발생하였습니다.\n'+jObj.errMsg);
        });
	 }
}
//데이타 조회 후 화면제어
function setDisplayElement() {
	if (isRegist || author.REGIST_STTUS == '10'){ //신규등록화면이거나 임시등록 상태
		if (isRegist){
			$("#AUTHOR_ID").hide(); //신규 등록일때는 작가id 없음.
			$("#btnRegist").show(); //등록버튼
		}else{
			$("#btnSave").show(); //저장버튼
		}
	}else if(author.REGIST_STTUS == '20'){ //결재요청 상태
		
	}else if(author.REGIST_STTUS == '30'){ //결재완료(등록완료) 상태
		//에디터
		$("#editor_area").hide();
		$("#div_HISTORY").show();
		
		//입력필드 제한
		closeInput();
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
                if (isRegist){
	                $("#AUTHOR_ID").val(jObj.fields.result.AUTHOR_ID); //등록 시 신규 키값 셋팅
                }
                if (uploadFile()){ //첨부파일 업로드
	                alert('<spring:message code="success.common.save"/>');
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
	if (!fn_check_uploadFiles()){
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
    DEXT5.config.InitXml = "dext_work_editor.xml"; //에디터설정파일.
    DEXT5.config.FileFieldID = "dx5";
    DEXT5.config.Width = "100%";
    DEXT5.config.Height = "320px";
    
    //DEXT5.config.FocusInitObjId = "section1"; //페이지로드 후 기본 포커스 이동이 필요한 경우 해당 html elementid
    
    new Dext5editor("dx5");
}
<%-- function dext_editor_loaded_event(){
    var url1 = "<%=URL_IN%>/resources/css/wf_common.css";
    var url2 = "<%=URL_IN%>/resources/css/wf_reset.css";
    DEXT5.addUserCssUrl(url1, 'dx5');
    DEXT5.addUserCssUrl(url2, 'dx5');
} --%>
//파일업로드 부분 초기화
function initUploadFile(){
	var multi_selector_A = new MultiSelector( 'fileList_A', '.xlsx,.xls', 1024*10, 3 ); //파일목록이 표시될 영역id, 허용확장자, 파일당 최대크기, 최대파일개수 
	multi_selector_A.addElement( 'files_A' ); //<input type="file"> 파일태그 맵핑
	
	var multi_selector_B = new MultiSelector( 'fileList_B', '.ppt,.pptx', 1024*1, 1 ); //파일목록이 표시될 영역id, 허용확장자, 파일당 최대크기, 최대파일개수
	multi_selector_B.addElement( 'files_B' ); //<input type="file"> 파일태그 맵핑
}
//파일업로드 처리
function uploadFile(){
	if (!checkValidation()){
		return false;
	}
	var isSuccess = false;
	
	var formData = new FormData();
	formData.append("AUTHOR_ID", $("#AUTHOR_ID").val()); //작가id
	
	//첨부파일A ====================================================
	var files_A = fn_get_new_added_files('fileList_A');
	for(var i=0; i < files_A.length; i++){
		formData.append("files_A", files_A[i]); //서버단에서 받을 파일필드명
	}
	formData.append("ATCH_FILE_ID_A", $("#ATCH_FILE_ID_A").val()); //첨부파일ID
	
	//첨부파일B =====================================================
	var files_B = fn_get_new_added_files('fileList_B');
	for(var i=0; i < files_B.length; i++){
		formData.append("files_B", files_B[i]); ////서버단에서 받을 파일필드명
	}
	formData.append("ATCH_FILE_ID_B", $("#ATCH_FILE_ID_B").val()); //첨부파일ID
	
	//첨부파일 업로드 ===============================================
	$.ajax({
	    type : "POST",
	    url : '<c:url value="/sample/uploadFile.do"/>',
	    data : formData,
	    processData: false,
	    contentType: false,
	    async: false,
	    success : function(jObj) {
	    	if (jObj.status == "SUCC") {
	    		isSuccess = true;
                $("#ATCH_FILE_ID_A").val(jObj.fields.result.ATCH_FILE_ID_A);
                $("#ATCH_FILE_ID_B").val(jObj.fields.result.ATCH_FILE_ID_B);
            } else {
                alert('<spring:message code="fail.common.save"/>\n' + jObj.errMsg);
            }
	    },
	    error : function(xhr, status, error) {
	    }
	});
	
	return isSuccess;
}
</script>
</head>
<body>
<h1>작가상세조회</h1>
<input type="button" id="btnRegist" value="등록" style="display:none;"/>
<input type="button" id="btnSave"   value="저장" style="display:none;"/>
<input type="button" id="btnTest"   value="테스트"/>
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
		<td>등록상태</td>
		<td colspan="3">
			<select id="REGIST_STTUS">
				<option value="">선택</option>
				<c:forEach var="code" items='${codeService.getCodeList("TEST01")}'>
					<option value="${code.CODE}">${code.CODE_NM}</option>	
				</c:forEach>
			</select>
		</td>
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
		<td>첨부파일A<input type="hidden" id="ATCH_FILE_ID_A"/><!-- 첨부파일A 일련번호 --></td>
		<td colspan="3">
			<div>
				<table id="fileList_A" style="border:1px solid #666;">
					<tr>
						<th>파일명</th>
						<th>파일크기</th>
						<th>등록일</th>
						<th>삭제</th>
					</tr>
				</table>
			</div>
			<div>
				<input id="files_A" type="file" multiple />
			</div>
		</td>
	</tr>
	<tr>
		<td>첨부파일B<input type="hidden" id="ATCH_FILE_ID_B"/><!-- 첨부파일B 일련번호 --></td>
		<td colspan="3">
			<div>
				<table id="fileList_B" style="border:1px solid #666;">
					<tr>
						<th>파일명</th>
						<th>파일크기</th>
						<th>등록일</th>
						<th>삭제</th>
					</tr>
				</table>
			</div>
			<div>
				<input id="files_B" type="file" multiple/>
			</div>
		</td>
	</tr>
</table>
<h2>작가의 책 목록</h2>
<button type="button" id="btnAddBook" class="btn">책 추가</button>
<button type="button" id="btnDelBook" class="btn">책 삭제</button>
<!-- realgrid 들어가는 영역 : S -->
<div class="realgrid-area">
    <div id="gridList"></div> 
</div>
</body>
</html>