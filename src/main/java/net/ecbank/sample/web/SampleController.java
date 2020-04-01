package net.ecbank.sample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.ecbank.fwk.manage.service.ServerConfigManageService;

@Controller
public class SampleController {
	
	@Autowired
	ServerConfigManageService securityManageService;
	
	@GetMapping("/sample/sampleList")
	public @ResponseBody String list() {
		return "sample";
	}
		
	@GetMapping("/sample/reloadRolesAndUrlMapping")
	public @ResponseBody String reloadRolesAndUrlMapping() {
		try {
			securityManageService.reloadRolesAndUrlMapping();
		}catch(Exception e) {
			return e.getMessage();
		}
		return "Roles and Url Mapping reloaded!";
	}
	
}
