<%@ page contentType="text/html;charset=utf-8" isErrorPage="false"%> <!-- isErrorPage="true" : 이 view는 에러 났을 때 보여주는 페이지. 예외를 view에 전달할 필요가 없다. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>error400.jsp</title>
</head>
<body>
<h1>예외가 발생했습니다.</h1>
발생한 예외 : ${pageContext.exception}<br>
예외 메시지 : ${pageContext.exception.message}<br>
<ol>
<c:forEach items="${pageContext.exception.stackTrace}" var="i">
	<li>${i.toString()}</li>
</c:forEach>
</ol>
</body>
</html>