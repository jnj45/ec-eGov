<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<tiles:insertAttribute name="head"/>
</head>
<body class="main-body">

	<div class="wrap">
		<header class="header-wrap">

			<div class="top-header-area">
				<div class="top-header">
					<h1 class="logo">
						<a href="#none">e-Pro 전자구매시스템</a>
					</h1>
					<ul class="top-menu">
						<li><a href="#none" class="user"><strong>${loginVO.name }</strong>님 환영합니다.</a></li>
						<li><a href="${pageContext.request.contextPath }/uat/uia/actionLogout.do" class="loginout">로그아웃</a></li>
					</ul>
				</div>
			</div>

			<nav class="gnb-area">
				<ul class="gnb">
					
					<c:if test="${fn:length(menuService.selectUserMenuList()) > 0}">
						<c:set var="initYn" value="Y" />
						<c:forEach var="list" items="${menuService.selectUserMenuList()}" varStatus="status">
						
							<c:if test="${list.LEVEL eq '2'}">
								<c:if test="${initYn ne 'Y' }">
									</ul>
								</li>
								</c:if>
							<li>
								<a href="#none">${list.MENU_NM}</a>
								<ul class="gnb-sub">
								<c:set var="initYn" value="N" />
							</c:if>
							
							<c:if test="${list.LEVEL eq '3'}">
								<li><a href="#none" onclick="goMenuTab('${list.URL}','${list.MENU_NM}','${list.PROGRM_FILE_NM}')">${list.MENU_NM}</a></li>
							</c:if>
							
						</c:forEach>
							</ul>
						</li>
					</c:if>
				</ul>
			</nav>

		</header>
		
		<button class="gnb-btn">gnb 열고/닫기</button>
		
		<section class="main-cont">
			<div id="content">
				<tiles:insertAttribute name="content"/>
			</div>
		</section>
		
		<footer id="foot" class="footer-wrap">
			<p>
				<img src="/images/common/img_copy.png" alt="e-pro 전자구매시스템">
				<span>COPYRIGHT ⓒ EC-BANK.CO., LTD. ALL RIGHTS RESERVED</span>
			</p>
		</footer>
	</div>  
	
</body>
</html>