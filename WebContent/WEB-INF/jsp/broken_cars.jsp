<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/tags.jspf"%>
<html>
<c:set var="title" value="Cars Page"></c:set>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<div id="wrap">
		<c:set var="pageNumber" value="3"></c:set>
		<c:set var="pageType" value="Cars Page"></c:set>
		<%@ include file="/WEB-INF/jspf/top.jspf"%>
		<div id="dynamic">
			<c:if test="${empty brokencars }">
				<h2>There is no broken cars</h2>
			</c:if>
			<c:if test="${!empty brokencars }">
				<h2>Here is all broken cars:</h2>
				<table>
					<tr>
						<th>Car№</th>
						<th>Model</th>
						<th>Break description</th>

					</tr>
					<c:forEach items="${brokencars}" var="item">
						<tr>
							<td>${item.car.ID }</td>
							<td>${item.car.model }</td>
							<td>${item.description }</td>
					</c:forEach>
				</table>
			</c:if>
		</div>
		<x:mail />
	</div>
</body>
</html>