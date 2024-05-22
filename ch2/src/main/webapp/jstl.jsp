<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%> //jstl core 선언
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> //jstl formate 선언
<html>
<head>
	<title>JSTL</title>
</head>
<body>
<c:set var="to"   value="10"/> <!-- EL은 lv 사용X => 저장소에 저장하기 위함 -->
<c:set var="arr"  value="10,20,30,40,50,60,70"/> 
<c:forEach var="i" begin="1" end="${to}">
	${i}
</c:forEach>
<br>
<c:if test="${not empty arr}"> <!-- if문 --> <!-- 배열이 비어있지 않으면 -->
	<c:forEach var="elem" items="${arr}" varStatus="status"> <!-- for문 --> <!-- status - 1. Count 2. index -->
		${status.count}. arr[${status.index}]=${elem}<BR>
	</c:forEach>
</c:if>	
<c:if test="${param.msg != null}">
	msg=${param.msg} 
	msg=<c:out value="${param.msg}"/> <!-- 태그가 그대로 출력 => 태그 테러 방지 가능 -->
</c:if>
<br>
<c:if test="${param.msg == null}">메시지가 없습니다.<br></c:if>
<c:set var="age" value="${param.age}"/>
<c:choose> <!-- else if문 -->
	<c:when test="${age >= 19}">성인입니다.</c:when>
	<c:when test="${0 <= age && age < 19}">성인이 아닙니다.</c:when>
	<c:otherwise>값이 유효하지 않습니다.</c:otherwise>
</c:choose>
<br>
<c:set var="now" value="<%=new java.util.Date() %>"/>
Server time is <fmt:formatDate value="${now}" type="both" pattern="yyyy/MM/dd HH:mm:ss"/> <!-- 날짜 데이터형식 포맷 -->
</body>
</html>