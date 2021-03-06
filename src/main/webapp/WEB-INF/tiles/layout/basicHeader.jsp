<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<meta charset="UTF-8">
<title>전자정부프레임워크</title>
<link rel="stylesheet" href="/css/jquery-ui.css" type="text/css">
<link rel="stylesheet" href="/css/jquery-ui.theme.css" type="text/css">
<link rel="stylesheet" href="/css/jquery.treeview.css" type="text/css">
<link rel="stylesheet" href="/css/reset.css" type="text/css">
<link rel="stylesheet" href="/css/common.css" type="text/css">
<!-- javascript -->
<script src="/resources/js/jquery-3.4.1.min.js"></script>
<script src="/js/jquery-ui.js"></script>
<script src="/js/jquery.treeview.js"></script>
<script src="/js/common.js"></script>
<script src="/resources/js/common.js"></script>
<script src="/resources/js/jquery.inputmask.js"></script>
<script src="/resources/js/jquery.validate.js"></script>
<script src="/resources/js/messages_ko.js"></script>
<script src="/resources/js/additional-methods.js"></script>
<script src="/resources/js/validate.js"></script>
<script>

	$.validator.setDefaults({
	    onkeyup:false,
	    onclick:false,
	    onfocusout:false,
	    showErrors: function(errorMap,errorList){
	        if(this.numberOfInvalids()){ // 에러가 있으면
	            alert(errorList[0].message); // 경고창으로 띄움
	        }
	    },
	    invalidHandler: function(form, validator) {
	        var errors = validator.numberOfInvalids();
	        if (errors) {                    
	            validator.errorList[0].element.focus();
	        }
	    } 
	});
	
	
	
</script>
</head>

</html>