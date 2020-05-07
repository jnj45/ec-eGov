/****************************************************************
 *
 * 파일명 : EgovMultiFiles.js
 * 설  명 : 업로드시 여러 파일에 대한 목록 표시 및 처리
 *
 * 수정일         수정자        수정내용
 * ----------   ---------   ----------------------------
 * 2019.10.15   취두영        다중 파일 업로드 개선 (HTML5)
 * 2019.12.06   신용호        EgovMultiFilesChecker 추가하여 확장자 체크 및 용량 체크 하기
 * 2020.04.                   webapp\js\egovframework\com\cmm\fms\EgovMultiFiles.js 를 copy해서 수정  
 */
/**
 * 생성자
 * @param list_target 파일목록이 표시될 영역 객체
 * @param max 최대파일개수
 * @param file_label
 * @returns
 */
var multiSelectorList = [];

function MultiSelector(list_target_id, max, file_label) {
	
	multiSelectorList.push(this);
	
	// Where to write the list
	this.id = list_target_id;
	this.list_target = document.getElementById(list_target_id);
	
	//this.id = 0;
	// Is there a maximum?
	if (max) {
		this.max = max;
	} else {
		this.max = -1;
	}
	
	this.file_label = file_label;
	
	this.filesTempArr = [];
	
	/**
	 * Add a new file input element
	 */
	_base = this;
	this.addElement = function(elementId) { //element id --> <input type="file"> 객체 id
		// Make sure it's a file input element
		var element =  document.getElementById(elementId);
		
		if (element.tagName == 'INPUT' && element.type == 'file') {

			// Add reference to this object
			element.multi_selector = this;

			// What to do when a file is selected
			element.onchange = function() {

				var i = 0;

				var sErrMsg = "첨부파일 개수는 [" + _base.max + "]까지 첨부할 수 있습니다!";
				var files_length = 1;
				// HTML5 지원여부 체크
				if (typeof element.files != "undefined") {
					files_length = element.files.length;
				}
				
				/*var files = element.files;
			    var filesArr = Array.prototype.slice.call(files);
			    var filesArrLen = filesArr.length;
			    var filesTempArrLen = _base.filesTempArr.length;
			    
			    for( var i=0; i<filesArrLen; i++ ) {
			    	_base.filesTempArr.push(filesArr[i]);
			        //$("#fileList").append("<div>" + filesArr[i].name + "<img src=\"/images/deleteImage.png\" onclick=\"deleteFile(event, " + (filesTempArrLen+i)+ ");\"></div>");
			        var html = '';
					html += "<tr id=newFileTr_'"+_base.file_label+i+"'>";
					html += "	<td>"+element.files[i].name+"</td>";
					html += "	<td></td>";
					html += "	<td>신규</td>";
					html += "	<td><img src='/images/egovframework/com/cmm/btn/btn_del.png'";
					html += "          class='cursor' onClick=\"fn_delete_new_File('newFileTr_"+_base.file_label+i+"');\" alt='파일삭제'></td>";
					html += "</tr>";

					$(_base.list_target).append(html);
			    }*/

				/*
				if (_base.max < files_length) {
					element.value = "";
					alert(sErrMsg);

					while (list_target.firstChild) {
						list_target.removeChild(list_target.firstChild);
					}

					return;
				}

				if (document.getElementById("egov_file_view_table") != null) {
					var sum = files_length
							+ document.getElementById("egov_file_view_table")
									.getElementsByTagName("tr").length;

					if (_base.max < sum) {
						element.value = "";
						alert(sErrMsg);
						return;
					}
				}

				if (files_length > 0) {
					while (list_target.firstChild) {
						list_target.removeChild(list_target.firstChild);
					}
				}*/

				// Update list : 화면에 선택한 파일 정보 추가
				for (i; i < files_length; i++) {
					this.multi_selector.addListRowNew(this, i);
				}

			};
			// Most recent element
			this.current_element = element;
		} else {
			// This can only be applied to file input elements!
			alert('Error: not a file input element');
		}
		;
	};

	/**
	 * Add a new row to the list of files
	 * 화면에 선택한 파일 정보 추가
	 */
	this.addListRowNew = function(element, i) {

		// Row div
		/*var new_row = document.createElement('div');
		new_row.className = "file_add_" + i;
		new_row.innerHTML = "<span>" + element.files[i].name + "</span>&nbsp;&nbsp;";

		// Add it to the list
		this.list_target.appendChild(new_row);*/
		this.filesTempArr.push(element.files[i]);
		
		var html = '';
		var trId = "newFileTr_"+this.file_label+"_"+this.filesTempArr.length;
		html += "<tr id='"+trId+"'>";
		html += "	<td>"+element.files[i].name+"</td>";
		html += "	<td></td>";
		html += "	<td>신규</td>";
		html += "	<td><img src='/images/egovframework/com/cmm/btn/btn_del.png'";
		html += "          class='cursor' onClick=\"fn_delete_new_added_file('"+trId+"','"+this.id+"',"+(this.filesTempArr.length-1)+");\" alt='파일삭제'></td>";
		html += "</tr>";

		$(this.list_target).append(html);
		//$(list_target).find('tr:last').after(html);
	};

};

var EgovMultiFilesChecker = {
	getFileExtension: function(filename) {

		if (filename == null) return "";
	    var __fileLen = filename.length;
	    var __lastDot = filename.lastIndexOf('.');
	    if (__lastDot < 0 ) return "";
	    var __fileExt = filename.substring(__lastDot, __fileLen).toLowerCase();
	 
	    return __fileExt;
	}
	// 결과가 true 인경우 허용
	// 결과가 false 인경우 불가 
	,checkExtensions: function(fileObjId, allowTypes) {
		if ( document.getElementById( fileObjId ) == null ) return false; // file객체가 없으면 승인하지 않는다.
		if ( typeof document.getElementById( fileObjId ).files == "undefined" )
			return this.checkExtensionsOldIE(fileObjId, allowTypes);
		else
			return this.checkExtensionsHTML5(fileObjId, allowTypes);
			
	}
	,checkExtensionsHTML5: function(fileObjId, allowTypes) {
		var __filelen = document.getElementById( fileObjId ).files.length;
		var __fileObjs = document.getElementById( fileObjId ).files;
	    if ( __filelen == 0 ) return true;
	    for(var i=0; i<__fileObjs.length; i++) {
	    	var __fileObj = __fileObjs[i];
	    	console.log(__fileObj.name);
	    	console.log(this.getFileExtension(__fileObj.name));

	    	var __fileExt = this.getFileExtension(__fileObj.name);
	    	if ( __fileExt == "" || allowTypes.indexOf(__fileExt) < 0 ) {
	    		alert("허용되지 않는 확장자 입니다.["+__fileExt+"]");
	    		return false;
	    	}
	    }
	    
	    return true;
	}
	,checkExtensionsOldIE: function(fileObjId, allowTypes) {
		var __filelPath = document.getElementById( fileObjId ).value;
	    console.log(__filelPath);
	    console.log(this.getFileExtension(__filelPath));

	    var __fileExt = this.getFileExtension(__filelPath);
    	if ( __fileExt == "" || allowTypes.indexOf(__fileExt) < 0 ) {
    		alert("2.허용되지 않는 확장자 입니다.["+__fileExt+"]");
    		return false;
    	}
	    
	    return true;
	}

	// 결과가 true 인경우 허용
	// 결과가 false 인경우 불가
	,checkFileSize: function(fileObjId, allowSize) {
		if ( document.getElementById( fileObjId ) == null ) return false; // file객체가 없으면 승인하지 않는다.
		if ( typeof document.getElementById( fileObjId ).files == "undefined" )
			return this.checkFileSizeOldIE(fileObjId, allowSize);
		else
			return this.checkFileSizeHTML5(fileObjId, allowSize);
	}
	,checkFileSizeHTML5: function(fileObjId, allowSize) {
		var __filelen = document.getElementById( fileObjId ).files.length;
		var __fileObjs = document.getElementById( fileObjId ).files;
	    if ( __filelen == 0 ) return true;
	    for(var i=0; i<__fileObjs.length; i++) {
	    	var __fileObj = __fileObjs[i];
	    	console.log(__fileObj.name);
	    	console.log(this.getFileExtension(__fileObj.name));
	    	console.log(__fileObj.size);
	    	
	    	if ( __fileObj.size > allowSize ) {
	    		alert("허용되지 않는 파일 사이즈 입니다.["+__fileObj.name+" : "+__fileObj.size+" bytes / "+allowSize+" bytes]");
	    		return false;
	    	}
	    }
	    
	    return true;
	}
	// 구형 IE 브라우저의 경우 사이즈 체크의 제한이 있습니다.
	,checkFileSizeOldIE: function(fileObjId, allowSize) {
		
		var __filelPath = document.getElementById( fileObjId ).value;
	    console.log(__filelPath);

    	alert("구형 브라우저에서는 파일 사이즈 체크를 할수 없습니다.");
	    
	    return true;
	}

};

/**
 * 첨부파일 목록을 조회하여 화면에 표시 
 * @param atchFileId 첨부파일id
 * @param displayTargetId 첨부파일목록 테이블id
 * @param deletable 삭제여부
 * @returns
 */
function displayFileList(atchFileId, displayTargetId, deletable){
	var params = {"param_atchFileId": atchFileId};
	var target = $("#"+displayTargetId);
	
	ajaxJsonCall("/cmm/fms/selectFileList.do", params, 
        	function(jObj){
				console.log(JSON.stringify(jObj));
				var html = '';
				if (!isEmpty(jObj.fields.fileList)){
					
					jObj.fields.fileList.forEach(function(row){
						html += "<tr id='fileTr_"+row.atchFileId+row.fileSn+"'>";
						html += "	<td><a href=\"javascript:fn_egov_downFile('"+row.atchFileId+"','"+row.fileSn+"');\">"+row.orignlFileNm+"</td>";
						html += "	<td>"+row.fileMg+"</td>";
						html += "	<td>"+row.creatDt+"</td>";
						if (deletable){
							html += "	<td><img src='/images/egovframework/com/cmm/btn/btn_del.png'";
							html += "          class='cursor' onClick=\"fn_egov_deleteFile('"+row.atchFileId+"','"+row.fileSn + "', 'fileTr_"+row.atchFileId+row.fileSn+"');\" alt='파일삭제'></td>";
						}
						html += "</tr>";
		            }); 
				}else{
					html = '<tr><td colspan="3">등록된 파일이 없습니다.</td></tr>';
				}
				target.append(html);
            }, 
        	function(jObj){
            	alert('첨부파일 데이타 조회 중 오류가 발생하였습니다.\n'+jObj.errMsg);
        });		
}

//파일다운로드
function fn_egov_downFile(atchFileId, fileSn){
	window.open("/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn);
}
//파일삭제
function fn_egov_deleteFile(atchFileId, fileSn, delTrId) {
	var newForm = document.createElement( 'form' );
	var newfileSn = document.createElement( 'input' );
	var newAtchFileId = document.createElement( 'input' );
	// Chrome 56+ 동적 생성한 form submit 불가 (HTML5)
	// HTML5 표준에선 Browsing contexts(document)에 form 이 연결되어 있지 않으면, form submit을 중단하도록 규정
	// https://www.w3.org/TR/html5/forms.html#constraints 4.10.22.3
	document.body.appendChild(newForm);
	
	newfileSn.setAttribute("name","fileSn");
	newAtchFileId.setAttribute("name","atchFileId");
	
	newfileSn.setAttribute("type","hidden");
	newAtchFileId.setAttribute("type","hidden");

	newfileSn.setAttribute("value",fileSn);
	newAtchFileId.setAttribute("value",atchFileId);
	
	newForm.appendChild( newfileSn );
	newForm.appendChild( newAtchFileId );

	newForm.method = "post";
	newForm.action = "/cmm/fms/deleteFileInfs.do";
	newForm.target = "iframe_egov_file_delete" 
	newForm.submit();
	
	$("#"+delTrId).remove();
}

function fn_delete_new_added_file(trId, multiSelectorId, arrIdx){
	$("#"+trId).remove();
	//$(filesTempArr).pop(arrIdx);
	for(var i=0; i < multiSelectorList.length; i++){
		if (multiSelectorList[i].id == multiSelectorId){
			multiSelectorList[i].filesTempArr[arrIdx] = null;
		}
	}
}

function fn_get_new_added_files(multiSelectorId){
	var files = [];
	for(var i=0; i < multiSelectorList.length; i++){
		if (multiSelectorList[i].id == multiSelectorId){
			for(var idx=0; idx < multiSelectorList[i].filesTempArr.length; idx++){
				if (multiSelectorList[i].filesTempArr[idx] != null){
					files.push(multiSelectorList[i].filesTempArr[idx]);
				}
			}
		}
	}
	return files;
}
