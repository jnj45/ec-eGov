<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					<li>
						<a href="#none">구매요청</a>
						<ul class="gnb-sub">
							<li><a href="#none">자재</a></li>
							<li><a href="#none">외주</a></li>
						</ul>
					</li>
					<li>
						<a href="#none">구매진행</a>
						<ul class="gnb-sub">
							<li><a href="#none">요약정보</a></li>
							<li><a href="#none">OPS 전송</a></li>
							<li><a href="#none">자재</a></li>
							<li><a href="#none">외주</a></li>
							<li><a href="#none">견적의뢰</a></li>
						</ul>
					</li>
					<li>
						<a href="#none">업체선정</a>
						<ul class="gnb-sub">
							<li><a href="#none">업체 선정</a></li>
							<li><a href="#none">업체선정 결과</a></li>
							<li><a href="#none">오프라인 견적</a></li>
						</ul>
					</li>
					<li>
						<a href="#none">구매오더</a>
						<ul class="gnb-sub">
							<li><a href="#none">구매오더 대기</a></li>
							<li><a href="#none">구매오더 결과</a></li>
						</ul>
					</li>
					<li>
						<a href="#none">전자계약</a>
						<ul class="gnb-sub">
							<li><a href="#none">전자계약생성</a></li>
							<li><a href="#none">전자계약진행</a></li>
							<li><a href="#none">전자계약완료</a></li>
							<li><a href="#none">서면계약관리</a></li>
						</ul>
					</li>
					<li>
						<a href="#none">협력업체관리</a>
						<ul class="gnb-sub">
							<li><a href="#none">신규등록업체 관리</a></li>
							<li><a href="#none">업체정보 관리</a></li>
						</ul>
					</li>
					<li>
						<a href="#none">레포트</a>
						<ul class="gnb-sub">
							<li><a href="#none">레포트</a></li>
						</ul>
					</li>
					<li>
						<a href="#none">전자결재</a>
						<ul class="gnb-sub">
							<li><a href="#none">결재 진행</a></li>
							<li><a href="#none">결재 완료</a></li>
							<li><a href="#none">결재선 지정</a></li>
						</ul>
					</li>
					<li>
						<a href="#none">시스템관리</a>
						<ul class="gnb-sub">
							<li><a href="#none">코드관리</a></li>
							<li><a href="#none">팝업관리</a></li>
							<li><a href="#none">게시판 관리</a></li>
							<li><a href="#none">권한관리</a></li>
							<li><a href="#none">정보관리</a></li>
							<li><a href="#none">Sample</a></li>
						</ul>
					</li>
					<li>
						<a href="#none">커뮤니티</a>
						<ul class="gnb-sub">
							<li><a href="#none">게시판</a></li>
						</ul>
					</li>
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