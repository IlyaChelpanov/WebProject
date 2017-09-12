<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/tags.jspf"%>
<html>
<c:set var="title" value="Races Page"></c:set>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<div id="wrap">
		<c:set var="pageNumber" value="3"></c:set>
		<c:set var="pageType" value="Users Page"></c:set>
		<%@ include file="/WEB-INF/jspf/top.jspf"%>
		<div id="dynamic">
			<c:if test="${empty orders}">
				<h2>There is no orders yet</h2>
			</c:if>
			<c:if test="${!empty orders}">
				<h2>Here is all orders maden:</h2>
				<table>
					<tr>
						<th>ID</th>
						<th>Race Number</th>
						<th>From</th>
						<th>To</th>
						<th>Date</th>
						<th>Time</th>
						<th>Needed Weight</th>
						<th>Needed Capacity</th>
						<th>Needed Speed</th>
						<th>Status</th>
					</tr>
					<c:forEach items="${orders}" var="item">
						<tr>
							<td>${item.ID }</td>
							<td>${item.raceID }</td>
							<td>${item.race.startCity }</td>
							<td>${item.race.endCity }</td>
							<td>${item.race.date}</td>
							<td>${item.race.timeOfArrive}</td>
							<td>${item.requiredWeight}</td>
							<td>${item.requiredCapacity}</td>
							<td>${item.requireSpeed}</td>
							<c:if test="${item.orderStatusID == 1 }">
								<td>Confirmed</td>
							</c:if>
							<c:if test="${item.orderStatusID == 2 }">
								<td>Not Confirmed</td>
							</c:if>
							<c:if test="${item.orderStatusID == 3 }">
								<td>Declined</td>
							</c:if>
						</tr>
					</c:forEach>
				</table>

				<c:if test="${userRole ne 'DRIVER'}">
					<form id="accept_order" action="controller" method="post">
						<input type="hidden" name="command" value="acceptorder" /> <select
							name="order">
							<c:forEach items="${orders }" var="item">
								<option>
									<c:out value="${item.ID }"></c:out>
								</option>
							</c:forEach>
						</select> <select name="car">
							<c:forEach items="${cars}" var="item">
								<option>
									<c:out value="${item.ID}-${item.model }"></c:out>
								</option>
							</c:forEach>
						</select> <input type="submit" value="Confirm">
					</form>
				</c:if>
			</c:if>
		</div>
		<x:mail/>
	</div>
</body>
</html>