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
  ## 
