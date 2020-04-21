package net.ecbank.fwk.common;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
	
	@Autowired
	protected CodeService codeService;
	
	@Autowired
	protected PropertyService propertyService;
}
