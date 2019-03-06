<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>MONGODB TEST</title>
	</head> 
	<body>
		<h3>MONGODB TEST</h3>
		<ul>
			<c:forEach items="${list}" var="s">
				<li>${s}</li>
			</c:forEach>
		</ul>
	</body>
</html>
