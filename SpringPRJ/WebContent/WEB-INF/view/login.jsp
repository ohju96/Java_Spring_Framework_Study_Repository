<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/checklogin.do" method="Post">
	아이디 : <input type="text" name="id">
	비밀번호 : <input type="password" name="pwd">
	<input type="submit" value="login">
</form>
<br>

<form action="/Oh/register.do" method="Post">
<input type="submit" value="Create Account">
</form>

</body>
</html>