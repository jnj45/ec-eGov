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
		<section class="main-cont">
			<div id="content">
				<tiles:insertAttribute name="content"/>
			</div>
		</section>
	</div>  
	
</body>
</html>