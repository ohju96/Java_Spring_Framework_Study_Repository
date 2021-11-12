<%@ page language="java" contentType="text/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
 <%-- 코드 내용의 공백을 제거해 줍니다. JSON 구현시 무조건 작성해 줍니다.  주석은 항상 지금 이 형석으로 작성합니다. 아니면 오류가 납니다.--%>
<%
	// 로그 찍은 함수
	String logCnt = request.getParameter("logCnt");

	// 로그 찍을 문구
	String logMessage = "Log Number[" + logCnt + "]";
	
	// 로그 찍기
	System.out.println("logMessage : " + logMessage);
 %>
 {"logMessage":"<%=logMessage%>"}
