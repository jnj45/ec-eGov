package net.ecbank.fwk.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class BaseController {
	
	@Autowired
	protected CodeService codeService;
	
	@Autowired
	protected PropertyService propertyService;
	
	@Autowired
	protected MessageSource messageSource;
}
