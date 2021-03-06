<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>
<%@ page import="egovframework.com.cmm.util.EgovUserDetailsHelper" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/jquery-ui.css' />" /> 
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/jquery-ui.theme.css'/>"/>
<script src="<c:url value='/resources/js/jquery-3.4.1.min.js'/>"></script>
<!-- ===== realGrid  start ===== -->
<script type="text/javascript" src="<c:url value='/resources/plugin/realgrid/realgridjs-lic.js'/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/resources/plugin/realgrid/realgridjs.1.1.33.min.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='/resources/plugin/realgrid/realgridjs_eval.1.1.33.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/plugin/realgrid/realgridjs-api.1.1.33.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/plugin/realgrid/jszip.min.js'/>"></script>
<!-- ===== realGrid  end ===== -->
<script src="<c:url value='/resources/js/site_define.js'/>"></script>
<script src="<c:url value='/resources/js/common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/rgrid.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/rtgrid.js'/>"></script>
<script type="text/javascript">
$(function(){
});
</script>
</head>
<body>
<h3>세션정보</h3>
<!-- 
//로그인 시 세션 attribute set됨
session.setAttribute("loginVO", loginVO);
 -->
<ul>
	<li>사용자id : <%=EgovUserDetailsHelper.getUserId()%>, ${loginVO.id}</li>
	<li>사용자명 : <%=EgovUserDetailsHelper.getUserName()%>, ${loginVO.name}</li>
	<li>권한목록 : <%=java.util.Arrays.toString(EgovUserDetailsHelper.getAuthorities().toArray())%></li>
</ul>
<h3>spring security tag 사용</h3>
<sec:authentication var="principal" property="principal" />
<ul>
	<li>principal : <sec:authentication property="principal"/></li>
	<li>principal.username : <sec:authentication property="principal.username"/>, ${principal.username}</li>
	<li>principal.password : <sec:authentication property="principal.password"/></li>
	<li>principal.enabled : <sec:authentication property="principal.enabled"/></li>
	<li>principal.accountNonExpired : <sec:authentication property="principal.accountNonExpired"/></li>
</ul>
<h3>권한소유여부 및 url액세스가능여부 </h3>
<ul>
	<li>로그인여부:<sec:authorize access="isAuthenticated()">로그인됨.</sec:authorize><sec:authorize access="!isAuthenticated()">로그인안됨.</sec:authorize></li>
	<li>ROLE_ADMIN 권한 있는지 여부: <sec:authorize access="hasRole('ROLE_ADMIN')" var="u">${u}</sec:authorize></li>
	<li>ROLE_ADMIN, ROLE_TEST 하나라도 있는지 여부: <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_TEST')" var="u">${u}</sec:authorize></li>
	<li>ROLE_XXX 없는 경우: <sec:authorize access="!hasRole('ROLE_XXX')" var="u">${u}</sec:authorize></li>
	<%-- <li>/sample/authorList.do 액세스권한여부: <sec:authorize url="/sample/authorList.do" var="t">${t}</sec:authorize></li> 적용안됨
	<li>/sample/authorRegistPop.do 액세스권한여부: <sec:authorize url="/sample/authorRegistPop.do" var="t">${t}</sec:authorize></li> --%>
</ul>
<h3>환경설정, 코드 정보</h3>
<ul>
	<li>프로퍼티조회 bean : <c:out value='${propertyService}'/></li>
	<li>프로파일 조회 : <c:out value='${fn:join(propertyService.getActiveProfiles(),",")}'/></li>
	<li>파일 프로퍼티값 조회 : <c:out value='${propertyService.getString("test.prop")}'/></li>
	<li>DB 프로퍼티값 조회 : <c:out value='${propertyService.getString("test.db.prop")}'/></li>
	<li>DB 프로퍼티값 조회(동일키 여러개) : <c:out value='${fn:join(propertyService.getStringArray("test.db.prop"),",")}'/></li>
	<br>
	<li>코드조회 bean : <c:out value='${codeService}'/></li>
	<li>코드정보 :  <c:out value='${codeService.getCode("COM001", "REGC02")}'/></li>
	<li>코드값   : <c:out value='${codeService.getCode("COM001", "REGC02").CODE_NM}'/></li>
	<li>
		코드selectbox :
		<select name="codeBox">
			<option value="">선택</option>
			<c:forEach var="code" items='${codeService.getCodeList("COM001")}'>
				<option value="${code.CODE}">${code.CODE_NM}</option>	
			</c:forEach>
		</select>
	</li>
</ul>
<h3>메세지 조회</h3>
<ul>
	<li>메세지 : <spring:message code="fail.common.msg"/></li>
	<li>메세지(인자) : <spring:message code="fail.common.sql" arguments="첫번째, 두번째"/></li>
</ul>
</body>
</html>