package net.ecbank.fwk.util;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class TemplateUtils {
	
	private static final Logger log = LoggerFactory.getLogger(TemplateUtils.class);
	
	private URL url;
	private String fileName;
	
	public TemplateUtils(String urlPath,String fileName) {
		this.url = getClass().getResource(urlPath);
		this.fileName = fileName;
	}
	
	public String createTemplateHtml(Map<String, Object> paramMap) throws Exception {
		
		if(this.url == null) {
			throw new Exception("Url is empty!!!");
		}
		
		if(this.fileName == null) {
			throw new Exception("FileName is empty!!!");
		}
		
		String resutHtml = "";
		
		log.debug("======================== 양식(Template) 생성(Create) Start ============================");
		
		try {
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
				cfg.setDirectoryForTemplateLoading(new File(url.getPath().substring(1)));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setOutputEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	        cfg.setLogTemplateExceptions(false);
	        cfg.setWrapUncheckedExceptions(true);
	        
			Template doc = cfg.getTemplate(fileName+".ftlh", "UTF-8");
			
			Writer out = new StringWriter();
			
			doc.setOutputEncoding("UTF-8");
			doc.process(paramMap, out);
			
	        resutHtml = out.toString();
	        
	        out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Template 생성에 실패하였습니다.");
		}
        
        log.debug("======================== 양식(Template) 생성(Create) End ============================");
		
		return resutHtml;
	}
	
	public void setUrl(String urlPath) {
		this.url = getClass().getResource(urlPath);
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
