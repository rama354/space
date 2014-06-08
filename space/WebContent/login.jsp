<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/login/login.css" />
<!-- <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/common.css" />-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/main.js"></script>
<title>MWAS Login</title>
</head>
<body bgcolor="white">
	<div id="login-box">
	<form method="post" action="/space/facebook.htm">
		<label>Username : </label><input type="text" name="user" /> <label>Password
			:  </label><input type="password" name="password" />
		<table style="width: 321px;">
			<tr>
				<td><button type="submit" name="submit" value="signIn"
						class="signin"
						onclick=" return verifylogin(this.form.user.value,this.form.password.value);">Sign
						In</button></td>

				<td><button type="submit" name="submit" value="forgetPassword"
						class="forgetpwd">Forget Password</button></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>