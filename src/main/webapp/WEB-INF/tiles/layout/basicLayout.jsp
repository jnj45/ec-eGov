<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<tiles:insertAttribute name="head"/>
</head>
<body>
	<header id="b_header">
		
	</header>
	<div id="content">
		<tiles:insertAttribute name="content"/>
	</div>
	
	<footer id="foot">
		<tiles:insertAttribute name="foot"/>
	</footer>
	  
	
</body>
</html>