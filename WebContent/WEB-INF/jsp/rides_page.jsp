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
			<c:if test="${empty rides }">
				<h2>There is no rides yet</h2>
			</c:if>
			<c:if test="${!empty rides }">
				<h2>Here is all rides:</h2>
				<table>
					<tr>
						<th>ID</th>
						<th>Race Number</th>
						<th>Car</th>
						<th>From</th>
						<th>To</th>
						<th>Date</th>
						<th>Time</th>
						<th>Status</th>
					</tr>
					<c:forEach items="${rides}" var="item">
						<tr>
							<td>${item.ID }</td>
							<td>${item.raceNumberID }</td>
							<td>${item.car.model }</td>
							<td>${item.race.startCity }</td>
							<td>${item.race.endCity }</td>
							<td>${item.race.date }</td>
							<td>${item.race.timeOfArrive }</td>
							<td><c:if test="${item.rideStatus == 1}">Not executed</c:if>
								<c:if test="${item.rideStatus == 2}">Executed</c:if> 
									
						</tr>
					</c:forEach>
				</table>
				<c:if test="${userRole=='DRIVER'}">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="executeride" /> <select
						name="ride"><c:forEach items="${rides }" var="item">
							<option><c:out value="${item.ID}-${item.race.date} "></c:out></option>
						</c:forEach></select> <input type="submit" value="Execute"><br>
					<h3>Has the car been broken during the ride?</h3>
					<label>Break description</label><br>
					<input name="breakdescrip">
				</form>
				</c:if>
			</c:if>
		</div>
<x:mail/>
	</div>
</body>
</html>