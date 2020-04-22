/**
 * 
 */
package net.ecbank.fwk.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.ecbank.fwk.common.CodeService;
import net.ecbank.fwk.common.PropertyService;

/**
 * MVC 인터셉터.
 * 
 * @author LYJ
 * @since 2020. 4. 22.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 상세설명 >>
 * 모든 controller 요청이 끝날 때 공통 유틸성 빈을 request attribute에 담아서 jsp단에서 바로 사용할 수 있도록 함.
 *   
 * << 개정이력(Modification Information) >>
 * 2020. 4. 22. LYJ - 최초 작성.
 * </pre>
 */
public class SetBeansInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger log = LoggerFactory.getLogger(SetBeansInterceptor.class);
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	PropertyService propertyService;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//Object bean = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()).getBean("codeService");
		//log.debug("SetBeansInterceptor bean : ==================================================================== " + bean);
		//modelAndView.addObject("codeService", bean != null ? (CodeService)bean : null);
		modelAndView.addObject("codeService",     codeService);
		modelAndView.addObject("propertyService", propertyService);
	}

	
}
