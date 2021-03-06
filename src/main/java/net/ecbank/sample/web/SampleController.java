package net.ecbank.sample.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import net.ecbank.fwk.common.BaseController;
import net.ecbank.fwk.manage.service.ServerConfigManageService;
import net.ecbank.fwk.mvc.JsonData;
import net.ecbank.fwk.util.MultipartFileUtils;
import net.ecbank.fwk.util.TemplateUtils;
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
	@RequestMapping("/sample/reloadRolesAndUrlMapping")
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
	@RequestMapping("/sample/infoView.do")
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
	@RequestMapping("/sample/authorList.do")
	public String authorList(ModelMap model){
		return "/sample/authorList"; //jsp파일경로
	}
	
	/**
	 * 작가 상세 조회 페이지
	 * @return
	 */
	@RequestMapping("/sample/authorViewPop.do")
	public String authorViewPop(ModelMap model){
		model.addAttribute("VIEW_TYPE", "R"); //페이지조회구분 C:작성, U:수정, R:조회
		return "/sample/authorViewPop"; //jsp파일경로
	}
	
	/**
	 * 작가 등록 페이지
	 * @return
	 */
	@RequestMapping("/sample/authorRegistPop.do")
	public String authorRegistPop(ModelMap model){
		model.addAttribute("VIEW_TYPE", "C"); //페이지조회구분 C:등록, U:수정, R:조회
		return "/sample/authorViewPop"; //jsp파일경로
	}
	
	/**
	 * 작가 수정 페이지
	 * @return
	 */
	@RequestMapping("/sample/authorEditPop.do")
	public String authorEditPop(ModelMap model){
		model.addAttribute("VIEW_TYPE", "U"); //페이지조회구분 C:등록, U:수정, R:조회
		return "/sample/authorViewPop"; //jsp파일경로
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
		
		log.debug("test: {}", "2");
		
		List<Map<String,Object>> dataList = sampleService.selectAuthorList(paramMap);
		jsonData.setPageRows(paramMap, dataList, dataList!=null ? dataList.size() : 0);
		
		return jsonData;
	}
	
	/**
	 * 작가 상세 데이타 조회
	 * @param paramMap
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/sample/selectAuthor.do")
	@ResponseBody
	public JsonData selectAuthor(@RequestBody Map<String,Object> paramMap, HttpServletRequest request, ModelMap model) {
		JsonData jsonData = new JsonData();
		
		Map<String,Object> author = sampleService.selectAuthor(paramMap);
		jsonData.addFields("author", author);
		
		List<Map<String, Object>> authorBookList = sampleService.selectAuthorBookList(paramMap);
		jsonData.addFields("authorBookList", authorBookList);
		
		return jsonData;
	}
	
	/**
	 * 작가 등록
	 * @param paramMap
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/sample/registAuthor.do")
	@ResponseBody
	public JsonData registAuthor(@RequestBody Map<String,Object> paramMap, HttpServletRequest request, ModelMap model) throws Exception {
		JsonData jsonData = new JsonData();
		
		Map<String,Object> resultMap = sampleService.registAuthor(paramMap);
		jsonData.addFields("result", resultMap);
		
		return jsonData;
	}
	/**
	 * 작가 수정
	 * @param paramMap
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/sample/updateAuthor.do")
	@ResponseBody
	public JsonData updateAuthor(@RequestBody Map<String,Object> paramMap, HttpServletRequest request, ModelMap model) throws Exception {
		JsonData jsonData = new JsonData();
		
		Map<String,Object> resultMap = sampleService.updateAuthor(paramMap);
		jsonData.addFields("result", resultMap);
			
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
	
	@RequestMapping("/sample/insertSample.do")
	public String insertSample(ModelMap model) {
		//로그인 사용자 정보 조회
		
		return "sample/insertSample";
	}
	
	@RequestMapping("/sample/templateSample.do")
	public String templateSample(ModelMap model) {
		
		return "sample/templateSample";
	}
	
	@RequestMapping("/sample/getTemplate.do")
	@ResponseBody
	public JsonData getTemplate(@RequestBody Map<String, Object> paramMap, ModelMap model) {
		JsonData jsonData = new JsonData();
		
		TemplateUtils tu = new TemplateUtils("/freemarker/", "sampleFreemarker");
		
		try {
			String html = tu.createTemplateHtml(paramMap);
			
			jsonData.addFields("contents", html);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonData;
	}
	
	@RequestMapping("/sample/signSample.do")
	public String signSample(ModelMap model) {
		
		return "sample/signSample";
	}
	
	@RequestMapping("/sample/serverSign.do")
	@ResponseBody
	public JsonData serverSign(@RequestBody Map<String, Object> paramMap) {
		JsonData jsonData = new JsonData();
		
		String signData = "";
		
		try {
			
			
			
			jsonData.addFields("signData", signData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonData;
	}
	
	@RequestMapping("/sample/uploadFile.do")
	@ResponseBody
	public JsonData uploadFile(final MultipartHttpServletRequest multiRequest, HttpServletRequest request, ModelMap model) throws Exception {
		JsonData jsonData = new JsonData();
		
		String authorId = ServletRequestUtils.getStringParameter(request, "AUTHOR_ID");
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		
		final Map<String, MultipartFile> files_A = MultipartFileUtils.getMulipartFileByFieldName(files, "files_A");
		final Map<String, MultipartFile> files_B = MultipartFileUtils.getMulipartFileByFieldName(files, "files_B");
		
		log.debug("uploaded Files : {}", files);
		log.debug("ATCH_FILE_ID_A --: {}", ServletRequestUtils.getStringParameter(multiRequest, "ATCH_FILE_ID_A"));
		log.debug("ATCH_FILE_ID_A --: {}", ServletRequestUtils.getStringParameter(request, "ATCH_FILE_ID_A"));
		log.debug("ATCH_FILE_ID_B : {}", ServletRequestUtils.getStringParameter(request, "ATCH_FILE_ID_B"));
		
		//첨부파일A 처리
		String atchFileId_A = ServletRequestUtils.getStringParameter(request, "ATCH_FILE_ID_A", "");
		
		if (!files_A.isEmpty()) {
			if ("".equals(atchFileId_A)) { //처음 등록
			    List<FileVO> result = fileUtil.parseFileInf(files_A, "FAQ_", 0, atchFileId_A, "");
			    atchFileId_A = fileMngService.insertFileInfs(result);
			    
			    //첨부파일id 정보 update
				Map<String, Object> fileParamMap = new HashMap<String, Object>();
				fileParamMap.put("AUTHOR_ID",      authorId);
				fileParamMap.put("ATCH_FILE_ID_A", atchFileId_A);
				sampleService.updateAuthorAttachFileId(fileParamMap);
				
			} else { //기존 업로드에 추가
			    FileVO fvo = new FileVO();
			    fvo.setAtchFileId(atchFileId_A);
			    int cnt = fileMngService.getMaxFileSN(fvo);
			    List<FileVO> _result = fileUtil.parseFileInf(files_A, "FAQ_", cnt, atchFileId_A, "");
			    fileMngService.updateFileInfs(_result);
			}
	    }
		
		//첨부파일B 처리
		String atchFileId_B = ServletRequestUtils.getStringParameter(request, "ATCH_FILE_ID_B", "");
		
		if (!files_B.isEmpty()) {
			if ("".equals(atchFileId_B)) { //처음 등록
			    List<FileVO> result = fileUtil.parseFileInf(files_B, "FAQ_", 0, atchFileId_B, "");
			    atchFileId_B = fileMngService.insertFileInfs(result);
			    
			    //첨부파일id 정보 update
				Map<String, Object> fileParamMap = new HashMap<String, Object>();
				fileParamMap.put("AUTHOR_ID",      authorId);
				fileParamMap.put("ATCH_FILE_ID_B", atchFileId_B);
				sampleService.updateAuthorAttachFileId(fileParamMap);
				
			} else { //기존 업로드에 추가
			    FileVO fvo = new FileVO();
			    fvo.setAtchFileId(atchFileId_B);
			    int cnt = fileMngService.getMaxFileSN(fvo);
			    List<FileVO> _result = fileUtil.parseFileInf(files_B, "FAQ_", cnt, atchFileId_B, "");
			    fileMngService.updateFileInfs(_result);
			}
	    }
		
		jsonData.addFields("ATCH_FILE_ID_A", atchFileId_A);
		jsonData.addFields("ATCH_FILE_ID_B", atchFileId_B);
		
		return jsonData;
	}
}
