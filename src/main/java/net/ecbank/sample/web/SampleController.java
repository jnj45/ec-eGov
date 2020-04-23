package net.ecbank.sample.web;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import net.ecbank.fwk.common.BaseController;
import net.ecbank.fwk.manage.service.ServerConfigManageService;
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
	
	private static final Logger log = LoggerFactory.getLogger(SampleController.class);
	
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
	 * 세션, 권한, 코드 등 정보 조회
	 * @return
	 */
	@GetMapping("/sample/infoView.do")
	public String infoView(ModelMap model) {
		//로그인 사용자 정보 조회
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		log.debug("로그인 사용자 정보 객체:{}", loginVO);
				
		//로그인 사용자의 권한조회
		List<String> authorities = EgovUserDetailsHelper.getAuthorities();
		log.debug("로그인 사용자 권한:{}",  Arrays.toString(authorities.toArray()));
		log.debug("ROLE_ADMIN 권한여부:{}", EgovUserDetailsHelper.hasRole("ROLE_ADMIN"));
		log.debug("ROLE_TEST 권한여부:{}",  EgovUserDetailsHelper.hasRole("ROLE_TEST"));
		
		//프로퍼티 정보 조회
		log.debug("profiles: {}", Arrays.toString(propertyService.getActiveProfiles()));
		log.debug("로컬환경 여부:{}, 개발환경 여부:{}, 운영환경 여부:{}", propertyService.isLocalMode(), propertyService.isDevMode(), propertyService.isProdMode());
		log.debug("pageUnit: {}",   propertyService.getString("pageUnit", "defalut val"));  //context-properties.xml 에 있는 값
		log.debug("test.prop: {}",  propertyService.getString("test.prop", "defalut val")); //별도의 properties 파일에 있는 값
		log.debug("testDbProp: {}", Arrays.toString(propertyService.getStringArray("test.db.prop"))); //EF_PROPERTY 테이블에 있는 값.(현대 spring profile에 해당하는 값)
		
		//코드정보 조회
		log.debug("codeService bean:" + codeService);
		log.debug("COM001 코드그룹 코드:");
		for(Map<String,Object> map : codeService.selectCodeList("COM001")) {
			log.debug(map.toString());
		}
		log.debug("COM001-REGC02 코드:" + codeService.selectCode("COM001", "REGC02"));
		
		return "/sample/infoView";
	}
	
	/**
	 * 작가 목록 조회 페이지
	 * @return
	 */
	@GetMapping("/sample/authorList.do")
	public String authorList(ModelMap model){
		return "/sample/authorList"; //jsp파일경로
	}
	
	/**
	 * 작가 목록 데이타 조회
	 * @param paramMap
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/sample/selectAuthorList.do")
	@ResponseBody
	public JsonData selectAuthorList(@RequestBody Map<String,Object> paramMap, HttpServletRequest request, ModelMap model) {
		JsonData jsonData = new JsonData();
		
		List<Map<String,Object>> dataList = sampleService.selectAuthorList(paramMap);
		jsonData.setPageRows(paramMap, dataList, dataList!=null ? dataList.size() : 0);
		
		return jsonData;
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
	 * 트랜잭션 테스트.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/sample/testTransaction.do")
	@ResponseBody
	public JsonData testTransaction(HttpServletRequest request, ModelMap model) {
		JsonData jsonData = new JsonData();
		
		log.debug("333==========================================================================================");
		sampleService.testTransaction();
		log.debug("333==========================================================================================");
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
