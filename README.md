# ec-eGov
전자정부 프레임워크 커스트마이징.
egovframework 3.9 버전 전체 공통 콤포넌트에서 사용하지 않는 불필요한 콤포넌트 제거 및 의존성 제거.
개선 및 기능추가 커스트마이징.

# 환경설정
  * 로컬DB는 script 디렉토리에 있는 sql 사용.
  * 로컬DB 패스워드는 EgovEnvCryptoUserTest.java 로 암호화된 db패스워드값을 globals.properties에 사용.
# 프레임워크 설정 관련
  * 권한 계층형 구조는 사용하지 않고, 복수권한 방식으로 사용하기 위한 작업
    1. context-security.xml에 sqlHierarchicalRoles 부분을 아래와 같이 수정
    
       ```xml
       sqlHierarchicalRoles ="SELECT 'ROLE_ANONYMOUS' as parent
			     , AUTHOR_CODE AS child
			  FROM COMTNAUTHORINFO
			 WHERE AUTHOR_CODE LIKE 'ROLE_%'
			   AND AUTHOR_CODE != 'ROLE_ANONYMOUS' "
       ```
    2. 사용자별 권한 테이블(COMTNEMPLYRSCRTYESTBS)에 사용자별로 복수권한을 등록,수정할 수 있도록 관리자 화면 수정필요.
    
# TODO-LIST
  * spring security 계층형 권한적용에서 복수권한 방식으로 변경. 부서별 권한과 동시 적용 고려
  * spring jpa 구성 및 샘플 프로그램.
    - egov의 spring 4.3버전과 spring data jpa 2.x대 버전과 호환성 문제
    - 실무 프로젝트 시 jpa 방식의 개발로 인한 리스크 (외주 개발자의 미익숙, jpa 숙련자 부재)
    - 따라서, 실무 프로젝트 시 jpa 방식 적용은 보류
    - MyBatis와 계층간 Map Data기반 개발이 효율적일 수 있음.
    
  * 업무별 로깅처리 및 로그레벨 관리콘솔로 실시간 변경기능.
    - log구현체 logback으로 변경적용
  * 디자인 적용
    - 샘플 디자인 
    - 템플릿 프레임워크 적용(tiles3)
  * grid 적용.
  * 불필요한 공통 컴포넌트 소스 제거.
  * 배치 프로그램
    - 예제(easy_batch) 프로그램
    - 전자정부 배치관리 및 운영방법 확인
  * 샘플 프로그램 예제(spring MVC, MyBatis dao, 계층간 Map 데이타 기반)
  * 스프링 시큐리티 권한관리 cache방식. admin콘솔에서 JMX로 실시간 적용방식.
  
