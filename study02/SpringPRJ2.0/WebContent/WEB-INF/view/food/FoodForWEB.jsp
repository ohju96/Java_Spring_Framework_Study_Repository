<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "poly.util.CmmUtil" %>
 <%
 //Controller�κ��� ���޹��� ������
 String res = CmmUtil.nvl((String)request.getAttribute("res"), "0");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
���ﰭ��ķ�۽� �Ĵ� Ȩ���������� <%=res %>���� �����Ϻ��� �ݿ��ϱ����� �Ĵ� ������ �����Ǿ����ϴ�.
</body>
</html>