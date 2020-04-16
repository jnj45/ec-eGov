package net.ecbank.fwk.mvc;

import org.springframework.beans.factory.annotation.Autowired;

import net.ecbank.fwk.common.PropertyService;

public class BaseController {
	
	@Autowired
	protected PropertyService propertyService;
}
