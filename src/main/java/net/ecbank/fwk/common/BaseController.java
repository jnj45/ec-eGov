package net.ecbank.fwk.common;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;

public class BaseController {
	
	@Autowired
	protected CodeService codeService;
	
	@Autowired
	protected PropertyService propertyService;
	
	@Autowired
	protected MessageSource messageSource;
	
	// 첨부파일 관련
	@Resource(name = "EgovFileMngService")
	protected EgovFileMngService fileMngService;
			
	@Resource(name = "EgovFileMngUtil")
	protected EgovFileMngUtil fileUtil;
}
