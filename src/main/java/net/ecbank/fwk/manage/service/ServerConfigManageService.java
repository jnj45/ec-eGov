package net.ecbank.fwk.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.security.intercept.EgovReloadableFilterInvocationSecurityMetadataSource;

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
	
	@Autowired
	private EgovReloadableFilterInvocationSecurityMetadataSource securityMetadataSource;

	/**
	 * spring security의 role과 url 리소스 권한 맵핑 정보를 reload한다.
	 * @throws Exception 
	 */
	@ManagedOperation
	public void reloadRolesAndUrlMapping() throws Exception {
//		throw new RuntimeException("jmx 에러 테스트");
		securityMetadataSource.reload();
	}
}
