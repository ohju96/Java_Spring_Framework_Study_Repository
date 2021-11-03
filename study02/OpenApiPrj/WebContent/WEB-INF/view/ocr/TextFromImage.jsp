<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@ page import = "poly.util.CmmUtil" %>
 <% 
 //컨트롤러부터 전달 받은 데이터
 String res = CmmUtil.nvl((String) request.getAttribute("res"));
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>이미지로부터 텍스트 인식 결과</title>
</head>
<body>
<h2>이미지 인식 결과</h2>
<hr/>
이미지로부터 텍스트 인식 결과는 <%=res %> 입니다.
</body>
</html>