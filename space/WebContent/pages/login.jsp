<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/login/login.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/main.js"></script>
<title>MWAS Login</title>
</head>
<body bgcolor="white">
	<div id="login-box">
	<form method="post" action="/space/facebook.htm">			 
		<p>You are not connected to FB. Click below button</p>
		<p><button type="submit" name="submit"  
						class="signin">Connect to FaceBook</button></p>
	</form>
	</div>
</body>
</html>