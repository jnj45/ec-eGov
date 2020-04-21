ALTER TABLE COMTNAUTHORROLERELATE 
DROP CONSTRAINT COMTNAUTHORROLERELATE_FK1;

ALTER TABLE COMTNAUTHORROLERELATE 
DROP CONSTRAINT COMTNAUTHORROLERELATE_FK2;

/*직원 테이블 생성 스크립트*/
CREATE TABLE EF_EMP 
(
  EMP_NO VARCHAR2(20) NOT NULL 
, EMP_NM VARCHAR2(50) NOT NULL 
, DEPT_CD VARCHAR2(20) 
, DEPT_NM VARCHAR2(100) 
, EMAIL VARCHAR2(50) 
, TEL VARCHAR2(20) 
, FAX VARCHAR2(20) 
, JOB VARCHAR2(40) 
, DUTY VARCHAR2(40) 
, BIZ_CD VARCHAR2(20) 
, EMP_TYPE VARCHAR2(5) 
, PASSWORD VARCHAR2(200) NOT NULL 
, PWD_FAIL_CNT NUMBER 
, LAST_PWD_CHG_DT DATE 
, LAST_LOGIN_DT DATE 
, USE_YN VARCHAR2(1) 
, RETIRE_YN VARCHAR2(1) 
, STATUS VARCHAR2(3) 
, FRST_REGISTER_ID VARCHAR2(20) 
, LAST_UPDUSR_ID VARCHAR2(20) 
, FRST_REGIST_PNTTM DATE 
, LAST_UPDT_PNTTM DATE 
, CONSTRAINT EF_EMP_PK PRIMARY KEY 
  (
    EMP_NO 
  )
  ENABLE 
);

COMMENT ON COLUMN EF_EMP.EMP_NM IS '이름';
COMMENT ON COLUMN EF_EMP.DEPT_CD IS '부서코드';
COMMENT ON COLUMN EF_EMP.DEPT_NM IS '부서명';
COMMENT ON COLUMN EF_EMP.EMAIL IS '이메일';
COMMENT ON COLUMN EF_EMP.TEL IS '전화번호';
COMMENT ON COLUMN EF_EMP.FAX IS '팩스';
COMMENT ON COLUMN EF_EMP.JOB IS '직위';
COMMENT ON COLUMN EF_EMP.DUTY IS '직책';
COMMENT ON COLUMN EF_EMP.BIZ_CD IS '회사코드';
COMMENT ON COLUMN EF_EMP.EMP_TYPE IS '사용자 타입';
COMMENT ON COLUMN EF_EMP.PASSWORD IS '비밀번호';
COMMENT ON COLUMN EF_EMP.PWD_FAIL_CNT IS '비밀번호 실패 횟수';
COMMENT ON COLUMN EF_EMP.LAST_PWD_CHG_DT IS '최종 패스워드 변경 일시';
COMMENT ON COLUMN EF_EMP.LAST_LOGIN_DT IS '최종 로그인 일시';
COMMENT ON COLUMN EF_EMP.USE_YN IS '사용 유무';
COMMENT ON COLUMN EF_EMP.RETIRE_YN IS '퇴사 유무';
COMMENT ON COLUMN EF_EMP.STATUS IS '상태';
COMMENT ON COLUMN EF_EMP.FRST_REGISTER_ID IS '최초 등록자';
COMMENT ON COLUMN EF_EMP.LAST_UPDUSR_ID IS '최종 수정자';
COMMENT ON COLUMN EF_EMP.FRST_REGIST_PNTTM IS '최초 등록일';
COMMENT ON COLUMN EF_EMP.LAST_UPDT_PNTTM IS '최종 수정일시';


/*협력사 테이블 생성 스크립트*/
CREATE TABLE EF_VENDOR 
(
  VENDOR_CD VARCHAR2(20) NOT NULL 
, VENDOR_NM VARCHAR2(100) NOT NULL 
, VENDOR_BIZ_NO VARCHAR2(10) 
, VENDOR_REPRE_NM VARCHAR2(100) 
, VENDOR_TYPE VARCHAR2(5) NOT NULL 
, BIZ_CLASS VARCHAR2(200) 
, BIZ_TYPE VARCHAR2(200) 
, ADDR VARCHAR2(400) 
, FAX VARCHAR2(20) 
, TEL VARCHAR2(20) 
, EMAIL VARCHAR2(50) 
, USE_YN VARCHAR2(1) 
, STATUS VARCHAR2(3) 
, FRST_REGISTER_ID VARCHAR2(20) 
, LAST_UPDUSR_ID VARCHAR2(20) 
, FRST_REGIST_PNTTM DATE 
, LAST_UPDT_PNTTM DATE 
, CONSTRAINT EF_VENDOR_PK PRIMARY KEY 
  (
    VENDOR_CD 
  )
  ENABLE 
);

COMMENT ON COLUMN EF_VENDOR.VENDOR_CD IS '협력사 코드';
COMMENT ON COLUMN EF_VENDOR.VENDOR_NM IS '협력사명';
COMMENT ON COLUMN EF_VENDOR.VENDOR_BIZ_NO IS '협력사 사업자번호';
COMMENT ON COLUMN EF_VENDOR.VENDOR_REPRE_NM IS '대표자명';
COMMENT ON COLUMN EF_VENDOR.VENDOR_TYPE IS '협력사 타입';
COMMENT ON COLUMN EF_VENDOR.BIZ_CLASS IS '업종';
COMMENT ON COLUMN EF_VENDOR.BIZ_TYPE IS '업태';
COMMENT ON COLUMN EF_VENDOR.ADDR IS '주소';
COMMENT ON COLUMN EF_VENDOR.FAX IS '대표 팩스';
COMMENT ON COLUMN EF_VENDOR.TEL IS '대표 전화번호';
COMMENT ON COLUMN EF_VENDOR.EMAIL IS '대표 이메일';
COMMENT ON COLUMN EF_VENDOR.USE_YN IS '사용유무';
COMMENT ON COLUMN EF_VENDOR.STATUS IS '상태';
COMMENT ON COLUMN EF_VENDOR.FRST_REGISTER_ID IS '최초 등록자';
COMMENT ON COLUMN EF_VENDOR.LAST_UPDUSR_ID IS '최종 수정자';
COMMENT ON COLUMN EF_VENDOR.FRST_REGIST_PNTTM IS '최초 등록일';
COMMENT ON COLUMN EF_VENDOR.LAST_UPDT_PNTTM IS '최종 수정일';

/*협력사 사용자 테이블 생성 스크립트*/
CREATE TABLE EF_VENDOR_USER 
(
  USER_ID VARCHAR2(20) NOT NULL
, USER_NM VARCHAR2(50) NOT NULL 
, VENDOR_CD VARCHAR2(20)
, DEPT_NM VARCHAR2(100) 
, MOBILE VARCHAR2(20) 
, EMAIL VARCHAR2(50) 
, TEL VARCHAR2(20) 
, FAX VARCHAR2(20) 
, JOB VARCHAR2(40) 
, DUTY VARCHAR2(40) 
, ADM_USER_YN VARCHAR2(1) 
, PASSWORD VARCHAR2(200) NOT NULL 
, PWD_FAIL_CNT NUMBER 
, LAST_PWD_CHG_DT DATE 
, LAST_LOGIN_DT DATE 
, USE_YN VARCHAR2(1) 
, STATUS VARCHAR2(3) 
, FRST_REGISTER_ID VARCHAR2(20) 
, LAST_UPDUSR_ID VARCHAR2(20) 
, FRST_REGIST_PNTTM DATE 
, LAST_UPDT_PNTTM DATE 
, CONSTRAINT EF_VENDOR_USER_PK PRIMARY KEY 
  (
    USER_ID 
  )
  ENABLE 
);

COMMENT ON COLUMN EF_VENDOR_USER.USER_ID IS '사용자 아이디';
COMMENT ON COLUMN EF_VENDOR_USER.USER_NM IS '사명자명';
COMMENT ON COLUMN EF_VENDOR_USER.VENDOR_CD IS '협력사코드';
COMMENT ON COLUMN EF_VENDOR_USER.DEPT_NM IS '부서명';
COMMENT ON COLUMN EF_VENDOR_USER.MOBILE IS '핸드폰번호';
COMMENT ON COLUMN EF_VENDOR_USER.EMAIL IS '이메일';
COMMENT ON COLUMN EF_VENDOR_USER.TEL IS '전화번호';
COMMENT ON COLUMN EF_VENDOR_USER.FAX IS '팩스';
COMMENT ON COLUMN EF_VENDOR_USER.JOB IS '직위';
COMMENT ON COLUMN EF_VENDOR_USER.DUTY IS '직책';
COMMENT ON COLUMN EF_VENDOR_USER.ADM_USER_YN IS '대표관리자 여부';
COMMENT ON COLUMN EF_VENDOR_USER.PASSWORD IS '패스워드';
COMMENT ON COLUMN EF_VENDOR_USER.PWD_FAIL_CNT IS '패스워드 실패 횟수';
COMMENT ON COLUMN EF_VENDOR_USER.LAST_PWD_CHG_DT IS '패스워드 최종 변경 일시';
COMMENT ON COLUMN EF_VENDOR_USER.LAST_LOGIN_DT IS '최종 로그인 일시';
COMMENT ON COLUMN EF_VENDOR_USER.USE_YN IS '사용유무';
COMMENT ON COLUMN EF_VENDOR_USER.STATUS IS '상태';
COMMENT ON COLUMN EF_VENDOR_USER.FRST_REGISTER_ID IS '최초 등록자';
COMMENT ON COLUMN EF_VENDOR_USER.LAST_UPDUSR_ID IS '최종 수정자';
COMMENT ON COLUMN EF_VENDOR_USER.FRST_REGIST_PNTTM IS '최초 등록일';
COMMENT ON COLUMN EF_VENDOR_USER.LAST_UPDT_PNTTM IS '최종 수정일';

/*메뉴관리 테이블 URL 필드 추가*/
ALTER TABLE COMTNMENUINFO 
ADD (URL VARCHAR2(250) );

/*추가된 URL 필드에 기존 프로그램 관리 테이블 URL 마이그레이션 처리*/
UPDATE COMTNMENUINFO A 
SET URL = (SELECT URL FROM COMTNPROGRMLIST WHERE PROGRM_FILE_NM = A.PROGRM_FILE_NM)
WHERE URL IS NULL;

ALTER TABLE COMTNMENUINFO 
MODIFY CONSTRAINT COMTNMENUINFO_FK2 DISABLE;

/*Console 사용자 테이블 생성 스크립트*/
CREATE TABLE EA_USER 
(
  USER_ID VARCHAR2(20) NOT NULL
, USER_NM VARCHAR2(50) NOT NULL 
, DEPT_NM VARCHAR2(100) 
, MOBILE VARCHAR2(20) 
, EMAIL VARCHAR2(50) 
, TEL VARCHAR2(20) 
, PASSWORD VARCHAR2(200) NOT NULL 
, PWD_FAIL_CNT NUMBER 
, LAST_PWD_CHG_DT DATE 
, LAST_LOGIN_DT DATE 
, USE_YN VARCHAR2(1) 
, FRST_REGISTER_ID VARCHAR2(20) 
, LAST_UPDUSR_ID VARCHAR2(20) 
, FRST_REGIST_PNTTM DATE 
, LAST_UPDT_PNTTM DATE 
, CONSTRAINT EA_USER_PK PRIMARY KEY 
  (
    USER_ID 
  )
  ENABLE 
);

COMMENT ON COLUMN EA_USER.USER_ID IS '사용자 아이디';
COMMENT ON COLUMN EA_USER.USER_NM IS '사명자명';
COMMENT ON COLUMN EA_USER.DEPT_NM IS '부서명';
COMMENT ON COLUMN EA_USER.MOBILE IS '핸드폰번호';
COMMENT ON COLUMN EA_USER.EMAIL IS '이메일';
COMMENT ON COLUMN EA_USER.TEL IS '전화번호';
COMMENT ON COLUMN EA_USER.PASSWORD IS '패스워드';
COMMENT ON COLUMN EA_USER.PWD_FAIL_CNT IS '패스워드 실패 횟수';
COMMENT ON COLUMN EA_USER.LAST_PWD_CHG_DT IS '패스워드 최종 변경 일시';
COMMENT ON COLUMN EA_USER.LAST_LOGIN_DT IS '최종 로그인 일시';
COMMENT ON COLUMN EA_USER.USE_YN IS '사용유무';
COMMENT ON COLUMN EA_USER.FRST_REGISTER_ID IS '최초 등록자';
COMMENT ON COLUMN EA_USER.LAST_UPDUSR_ID IS '최종 수정자';
COMMENT ON COLUMN EA_USER.FRST_REGIST_PNTTM IS '최초 등록일';
COMMENT ON COLUMN EA_USER.LAST_UPDT_PNTTM IS '최종 수정일';

/*콘솔 관리자 기본값 - 비밀번호 : admin*/
Insert into ECEGOV.EA_USER (USER_ID,USER_NM,DEPT_NM,MOBILE,EMAIL,TEL,PASSWORD,PWD_FAIL_CNT,LAST_PWD_CHG_DT,LAST_LOGIN_DT,USE_YN,FRST_REGISTER_ID,LAST_UPDUSR_ID,FRST_REGIST_PNTTM,LAST_UPDT_PNTTM) values ('admin','admin','-',null,'-',null,'$2a$10$zn83C1E.escmRTCPES8PBu5SG5sl3K6.NLVie9cQVS9B4kq5cBbQ2',0,null,null,'Y','AdminConsole','AdminConsole',to_date('20/04/13','RR/MM/DD'),to_date('20/04/13','RR/MM/DD'));

/* 코드 테이블 컬럼 추가*/
ALTER TABLE COMTCCMMNDETAILCODE 
ADD (SORT NUMBER );

ALTER TABLE COMTCCMMNDETAILCODE 
ADD (CODE_ENG_NM VARCHAR2(120) );

ALTER TABLE COMTCCMMNDETAILCODE 
ADD (ATTR1 VARCHAR2(100) );

ALTER TABLE COMTCCMMNDETAILCODE 
ADD (ATTR2 VARCHAR2(100) );

ALTER TABLE COMTCCMMNDETAILCODE 
ADD (ATTR3 VARCHAR2(100) );

COMMENT ON COLUMN COMTCCMMNDETAILCODE.SORT IS '정렬순서';
COMMENT ON COLUMN COMTCCMMNDETAILCODE.CODE_ENG_NM IS '코드 영문명';
COMMENT ON COLUMN COMTCCMMNDETAILCODE.ATTR1 IS '추가 정보1';
COMMENT ON COLUMN COMTCCMMNDETAILCODE.ATTR2 IS '추가 정보2';
COMMENT ON COLUMN COMTCCMMNDETAILCODE.ATTR3 IS '추가 정보3';

/*SOR 컬럼 값 갱신*/
UPDATE COMTCCMMNDETAILCODE A
SET SORT = (
        SELECT AA.NUM 
        FROM (SELECT CODE_ID,CODE,ROW_NUMBER() OVER (PARTITION BY CODE_ID ORDER BY CODE) AS NUM FROM COMTCCMMNDETAILCODE) AA 
        WHERE AA.CODE = A.CODE AND AA.CODE_ID = A.CODE_ID
        )

--------------------------------------------------------
--  DDL for Table EF_PROPERTY (프로퍼티 테이블)
--------------------------------------------------------

  CREATE TABLE "EF_PROPERTY" 
   (	"PROP_ID" NUMBER, 
	"PROFILE" VARCHAR2(10 BYTE) DEFAULT '*', 
	"KEY" VARCHAR2(100 BYTE), 
	"VALUE" VARCHAR2(500 BYTE), 
	"USE_YN" VARCHAR2(1 BYTE) DEFAULT 'N', 
	"FRST_REGIST_PNTTM" DATE, 
	"FRST_REGISTER_ID" VARCHAR2(20 BYTE), 
	"LAST_UPDT_PNTTM" DATE, 
	"LAST_UPDUSR_ID" VARCHAR2(20 BYTE)
   ) ;

   COMMENT ON COLUMN "EF_PROPERTY"."PROP_ID" IS '프로퍼티ID';
   COMMENT ON COLUMN "EF_PROPERTY"."PROFILE" IS '적용할spring profile: ''*'':전체, '',''로 구분';
   COMMENT ON COLUMN "EF_PROPERTY"."KEY" IS 'key';
   COMMENT ON COLUMN "EF_PROPERTY"."VALUE" IS 'value';
   COMMENT ON COLUMN "EF_PROPERTY"."USE_YN" IS '사용여부';
   COMMENT ON COLUMN "EF_PROPERTY"."FRST_REGIST_PNTTM" IS '생성일시';
   COMMENT ON COLUMN "EF_PROPERTY"."FRST_REGISTER_ID" IS '생성자id';
   COMMENT ON COLUMN "EF_PROPERTY"."LAST_UPDT_PNTTM" IS '수정일시';
   COMMENT ON COLUMN "EF_PROPERTY"."LAST_UPDUSR_ID" IS '수정자id';
