<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/tags.jspf"%>
<html>
<c:set var="title" value="Chat Page"></c:set>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<div id="wrap">
		<c:set var="pageNumber" value="3"></c:set>
		<c:set var="pageType" value="Chat Page"></c:set>
		<%@ include file="/WEB-INF/jspf/top.jspf"%>
		<div id="dynamic">
			<c:if test="${empty messages}">
				<h2>There is no messages yet</h2>
			</c:if>
			<c:if test="${!empty messages}">
				<h2>Here is all messages:</h2>
				<ul>
					<c:forEach items="${messages}" var="item">
						<li><c:out
								value="${item.user.username } : ${item.message } : ${item.date }"></c:out></li>
					</c:forEach>
				</ul>
			</c:if>
			<form id="send_message" action="controller" method="post">
				<input type="hidden" name="command" value="sendmessage" /> <input
					name="message" required />
					<input type="submit" value="Send">
			</form>
		</div>
		<x:mail />
	</div>
</body>
</html>

