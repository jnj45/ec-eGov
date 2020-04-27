
function validateForm(formId, options){
	
	if(options == undefined){
		$("#"+formId).validate({});
	}else{
		$("#"+formId).validate(options);
	}
	
	return $("#"+formId).valid();
}