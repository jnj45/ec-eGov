package net.ecbank.fwk.common;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
	
	@Autowired
	protected PropertyService propertyService;
}