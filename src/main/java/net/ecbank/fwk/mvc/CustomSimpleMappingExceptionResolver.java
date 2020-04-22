/**
 * 
 */
package net.ecbank.fwk.mvc;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import net.ecbank.fwk.common.PropertyService;
import net.ecbank.fwk.exception.BizRuntimeException;

/**
 * Spring MVC 예외처리 커스트마이징.
 * 
 * @author LYJ
 * @since 2020. 4. 20.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 * ajax 요청에 대한 에러가 발생할 경우 json 에러응답 처리
 * TODO 에러로깅 처리 (에러로깅 테이블에 write)
 * TODO 서버환경 및 권한에 따라 에러메세지 상세화 처리
 *   
 * << 개정이력(Modification Information) >>
 * 2020. 4. 20. LYJ - 최초 작성.
 * </pre>
 */
public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
	
	private static final Logger log = LoggerFactory.getLogger(CustomSimpleMappingExceptionResolver.class);
	
	@Autowired
	PropertyService propertyService;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.SimpleMappingExceptionResolver#doResolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		String ajaxHeader = request.getHeader("X-Requested-With");
        if(ajaxHeader != null && "XMLHttpRequest".equals(ajaxHeader)){
        	
        	ModelAndView view = new ModelAndView("jsonView");
        	
        	view.addObject("status", JsonData.FAIL);
        	String errorMsg = "";
        	if (ex instanceof EgovBizException || ex instanceof BizRuntimeException) {
        		errorMsg = ex.getMessage();
        	}else {
        		errorMsg = "서버에 오류가 발생하여 요청을 수행할 수 없습니다. 시스템 관리자에게 문의 바랍니다.";
        	}
        	view.addObject("errMsg", errorMsg);
        	view.addObject("activeProfiles", Arrays.toString(propertyService.getActiveProfiles()));
        	view.addObject("roles",          Arrays.toString(EgovUserDetailsHelper.getAuthorities().toArray()));
        	
        	if ( !propertyService.isProdMode() || EgovUserDetailsHelper.hasRole("ROLE_ADMIN")) {
        		view.addObject("stackTrace", ExceptionUtils.getStackTrace(ex));
        	}
        	
        	view.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        	
        	return view;
		}else {
			return getModelAndView(determineViewName(ex, request), ex, request);
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.SimpleMappingExceptionResolver#getModelAndView(java.lang.String, java.lang.Exception, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected ModelAndView getModelAndView(String viewName, Exception ex, HttpServletRequest request) {
		return super.getModelAndView(viewName, ex, request);
	}
	
}
