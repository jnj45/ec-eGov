# 스프링 시큐리티 관련 설정
  
  ## context-security.xml 수정
  
    * 권한 계층형 구조는 사용하지 않고, 복수권한 방식으로 사용하기 위한 작업
      context-security.xml에 sqlHierarchicalRoles 부분을 아래와 같이 수정
      
       ```xml
       sqlHierarchicalRoles ="SELECT 'ROLE_ANONYMOUS' as parent
			     , AUTHOR_CODE AS child
			  FROM COMTNAUTHORINFO
			 WHERE AUTHOR_CODE LIKE 'ROLE_%'
			   AND AUTHOR_CODE != 'ROLE_ANONYMOUS' "
       ```
       
    * 사용자별 권한 테이블(COMTNEMPLYRSCRTYESTBS)에 사용자별로 복수권한을 등록,수정할 수 있도록 관리자 화면 수정필요.
  
  ## eGov spring security 관련 주요 클래스
  	* EgovSecuritySecuredObjectConfigBeanDefinitionParser
  	  : egov-security schema namespace 'secured-object-config' element 처리를 담당하는 bean definition parser 클래스
  	* EgovReloadableFilterInvocationSecurityMetadataSource
  	  : 보호자원 접근관리 DB Metasource 처리
  	* EgovSecuredObjectServiceImpl
  	  : Spring Security의 초기 데이터를 DB로 부터 조회하여 보호된 자원 접근 권한을 지원, 제어 할 수 있도록 구현한 클래스
  	* SecuredObjectDAO
  	  : DB 기반의 Secured Object 정보를 제공하기 위한 DAO
  	  
  	  
