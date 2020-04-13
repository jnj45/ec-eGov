package net.ecbank.sample.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.ecbank.fwk.manage.service.ServerConfigManageService;
import net.ecbank.fwk.model.JsonData;

@Controller
@RequestMapping("/sample")
public class SampleController {
	
	@Autowired
	ServerConfigManageService securityManageService;
	
	@GetMapping("/reloadRolesAndUrlMapping")
	public @ResponseBody String reloadRolesAndUrlMapping() {
		try {
			securityManageService.reloadRolesAndUrlMapping();
		}catch(Exception e) {
			return e.getMessage();
		}
		return "Roles and Url Mapping reloaded!";
	}
	
	@GetMapping("/authorList.do")
	public String authorList() {
		return "/sample/authorList";
	}
	
	/*@RequestMapping("/getAuthorList.do")
	@ResponseBody
	public Map<String, Object> getAuthorList() {
		List<Map> list = new ArrayList<Map>();
		Map<String, Object> map = new HashMap<String, Object>();
		list.add(map);
		
		//return new ResponseEntity<>(list, HttpStatus.OK);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list);
		return resultMap;
		//에러 : java.lang.IllegalArgumentException: No converter found for return value of type: class java.util.HashMap
		//https://www.egovframe.go.kr/uss/olh/qna/QnaInqireCoUpdt.do?qaId=QA_00000000000017650&menu=5&submenu=3
	}*/
	
	@RequestMapping("/selectAuthorList.do")
	@ResponseBody
	public JsonData selectAuthorList(@RequestBody Map<String,Object> paramMap, HttpServletRequest request, ModelMap model) {
		JsonData jsonData = new JsonData();
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("AUTHOR_ID", "123");
		map.put("NAME",      "홍길동");
		dataList.add(map);
		
		jsonData.setPageRows(paramMap, dataList, dataList!=null?dataList.size():0);
		
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap.put("list", list);
		
//		ModelAndView jsonView = new ModelAndView("jsonView");
//		jsonView.addObject("result", list);
		
		return jsonData;
	}
	
	
}
