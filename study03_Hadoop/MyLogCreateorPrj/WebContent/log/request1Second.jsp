<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1초마다 URL을 호출하여 로그를 생성하는 JSP 페이지이다.</title>
<script src="/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	var logCnt = 0; //로그 찍은 횟수 저장을 위한 변수입니다.
	
	//JSP 페이지가 완전히 로딩 완료(</html> 태그까지 실행 완료) 되면 1번만 자동 실행됩니다.
	$(window).on("load", function() {
		// $는 js가 아닌 jQuery 문법입니다.
		
		//화면 로딩이 완료되면 첫번째로 실행합니다.
		doRequestURL();
		
		//2번째부터 채팅방 전체리스트를 1초마다 로딩합니다.
		// 컴퓨터는 밀리세컨트 (10-3제곱)	 단위로 처리하기 때문에 1000이 1초를 의미합니다.
		setInterval("doRequestURL();", 1000);
		// 일정 설정 간격을 두고 (1초마다) doRequestURL()함수를 실행합니다.
		
	});
	
	//URL 호출하기
	function doRequestURL() {
		
		logCnt++; //로그 호출수 1씩 증가
		
		//Ajax 호출하기 ( Ajx 비동기 데이터 호출 )
		$.ajax({
			url : "/log/result.jsp?logCnt="+ logCnt,
			type : "post",
			dataType : "JSON", //결과 형태를 JSON으로 받겠다.
			contentType : "application/json; charset=UTF-8",
			success : function(json) {
				$("#logView").html("My create logMessage : " + json.logMessage);
			}
		})
	}
</script>

</head>
<body>
<hr />
<br />
<br />
<h2>난 1초마다 URL을 호출하여 로그를 생성하는 JSP 페이지이다.</h2>
<br />
<hr />
<div id="logView">[결과표시]</div>
</body>
</html>