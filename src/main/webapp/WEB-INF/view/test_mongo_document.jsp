<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>mongodb git test</title>
	</head> 
	<body>
		<h3>MONGODB TEST</h3>
		<c:forEach items="${lists}" var="l">
			<ul>
				<li>${l._id}</li>
				<li>${l.name}</li>
				<li>${l.metas}</li>			
			</ul>
		</c:forEach>
	</body>
</html>
