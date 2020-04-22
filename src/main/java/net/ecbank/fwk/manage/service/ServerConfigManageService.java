package net.ecbank.fwk.manage.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.security.intercept.EgovReloadableFilterInvocationSecurityMetadataSource;
import net.ecbank.fwk.common.CodeService;
import net.ecbank.fwk.common.PropertyService;

/**
 * Web Application 환경설정 처리 서비스
 * JMX MBean으로 노출되어 관리콘솔에서 원격으로 호출될 수 있도록 한다.
 * 
 * @author LYJ
 *
 */
@Service
@ManagedResource(
		objectName="ec-eGov:name=ServerConfigManageService", 
		description="서버환경설정 관리 서비스"//,
//		log=true,
//        logFile="ec-eGov-jmx.log",
        //currencyTimeLimit=15
//        persistPolicy="OnUpdate",
//        persistPeriod=200,
//        persistLocation="foo",
//        persistName="bar"
		)
public class ServerConfigManageService{
	
	private static final Logger log = LoggerFactory.getLogger(ServerConfigManageService.class);
	
	@Autowired
	private EgovReloadableFilterInvocationSecurityMetadataSource securityMetadataSource;
	
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private CodeService codeService;
	
	/**
	 * spring security의 role과 url 리소스 권한 맵핑 정보를 reload한다.
	 * @throws Exception 
	 */
	@ManagedOperation
	public void reloadRolesAndUrlMapping() throws Exception {
//		throw new RuntimeException("jmx 에러 테스트");
		try {
			securityMetadataSource.reload();
			log.info("role과 url 리소스 권한 맵핑 리로딩 완료");
		}catch(Exception e) {
			log.error("role과 url 리소스 권한 맵핑 리로딩 오류", e);
			throw e;
		}
	}
	
	/**
	 * 프로퍼티 리로딩 처리.
	 * @throws Exception
	 */
	@ManagedOperation
	public void realodProperties() throws Exception{
		try {
			propertyService.afterPropertiesSet();
			log.info("프로퍼티 리로딩 완료");
		}catch(Exception e) {
			log.error("프로퍼티 리로딩 오류", e);
			throw e;
		}
	}
	
	/**
	 * 코드 캐쉬 초기화 처리
	 */
	@ManagedOperation
	public void clearCodeCache() throws Exception{
		try {
			codeService.clearCodeCache();
			log.info("코드 캐쉬 초기화 완료");
		}catch(Exception e) {
			log.error("코드 캐쉬 초기화 오류", e);
			throw e;
		}
	}
	
}
