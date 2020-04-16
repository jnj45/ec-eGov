package net.ecbank.sample.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.annotation.IncludedInfo;
import net.ecbank.fwk.manage.service.ServerConfigManageService;
import net.ecbank.fwk.mvc.BaseController;
import net.ecbank.fwk.mvc.JsonData;
import net.ecbank.sample.service.SampleService;

/**
 * 샘플 컨트롤러
 * 
 * @author LYJ
 * @since 2020. 4. 14.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 * 추가설명이 필요하면 기술.
 *  
 * << 개정이력(Modification Information) >>
 *   2020. 4. 14. 홍길동 - 최초 생성
 *   2020. 4. 14. 홍길동 - 수정내용 기술.
 * </pre>
 */
@Controller
public class SampleController extends BaseController {
	
	private final Logger log = LoggerFactory.getLogger(SampleController.class);
	
	@Autowired
	ServerConfigManageService securityManageService;
	
	@Autowired
	SampleService sampleService;
	
	/**
	 * 롤,권한 맵핑정보 reloading test
	 * @return
	 */
	@GetMapping("/sample/reloadRolesAndUrlMapping")
	public @ResponseBody String reloadRolesAndUrlMapping() {
		try {
			securityManageService.reloadRolesAndUrlMapping();
		}catch(Exception e) {
			return e.getMessage();
		}
		return "Roles and Url Mapping reloaded!";
	}
	
	/**
	 * 작가 목록 조회
	 * @return
	 */
	@GetMapping("/sample/authorList.do")
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
	
	/**
	 * 작가 목록 데이타 조회
	 * @param paramMap
	 * @param request
	 * @param model
	 * @return
	 */
	@IncludedInfo(name="샘플-작가목록",order = 900 ,gid = 900)
	@RequestMapping("/sample/selectAuthorList.do")
	@ResponseBody
	public JsonData selectAuthorList(@RequestBody Map<String,Object> paramMap, HttpServletRequest request, ModelMap model) {
		JsonData jsonData = new JsonData();
		
//		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("AUTHOR_ID", "123");
//		map.put("NAME",      "홍길동");
//		dataList.add(map);
		paramMap.put("NAME", "길동");
		
		List<Map<String,Object>> dataList = sampleService.selectAuthorList(paramMap);
		
		jsonData.setPageRows(paramMap, dataList, dataList!=null?dataList.size():0);
		
//		ModelAndView jsonView = new ModelAndView("jsonView");
//		jsonView.addObject("result", list);
		
		return jsonData;
	}
	
	@RequestMapping("/sample/testTransaction.do")
	@ResponseBody
	public JsonData testTransaction(HttpServletRequest request, ModelMap model) {
		JsonData jsonData = new JsonData();
		
		log.debug("111==========================================================================================");
		sampleService.testTransaction();
		log.debug("111==========================================================================================");
		/*
		Map<String, Object> testMap = new HashMap<String, Object>();
		testMap.put("AUTHOR_ID", 2);
		testMap.put("NAME",      "222NAME");
		
		sampleService.testUpdateAuthorName(testMap);
		
		testMap.put("AUTHOR_ID", 3);
		testMap.put("NAME",      "333NAME");
		sampleService.testUpdateAuthorName(testMap);
		*/
		return jsonData;
	}
	
}
