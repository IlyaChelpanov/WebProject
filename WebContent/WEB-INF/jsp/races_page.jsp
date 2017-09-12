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
			<h2>Here is all races registered:</h2>

			<form id="sort_form" action="controller" method="post">
				<input type="hidden" name="command" value="sortrace" /> <input
					type="submit" value="Sort by:"> <select name="sortType"><option>ID</option>
					<option>Date</option>
					<option>Status</option></select>
			</form>

			<table>
				<tr>
					<th>ID</th>
					<th>Date of Ride</th>
					<th>From</th>
					<th>To</th>
					<th>Time</th>
					<th>Type of Race</th>
					<th>Status</th>
				</tr>
				<c:forEach items="${races}" var="item">
					<tr>
						<td>${item.ID }</td>
						<td>${item.date }</td>
						<td>${item.startCity }</td>
						<td>${item.endCity }</td>
						<td>${item.timeOfArrive }</td>
						<td><c:if test="${item.typeOfRace==1 }">Cargo, less than 5 tonnes. </c:if>
							<c:if test="${item.typeOfRace==2 }">Cargo from 5 to 10 tonnes.</c:if>
							<c:if test="${item.typeOfRace==3 }">Cargo more than 10 tonnes.</c:if>
							<c:if test="${item.typeOfRace==4 }">Passenger less than 10 people.</c:if>
							<c:if test="${item.typeOfRace==5 }">Passenger less than 25 people.</c:if>
							<c:if test="${item.typeOfRace==6 }">Passenger less than 50 people.</c:if></td>
						<td><c:if test="${item.raceStatusID==1 }">Free </c:if> <c:if
								test="${item.raceStatusID==2 }">Engaged</c:if> <c:if
								test="${item.raceStatusID==3 }">Denied</c:if></td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${userRole ne 'DRIVER'}">
				<form id="redact_form" action="controller" method="post">
					<input type="hidden" name="command" value="redactrace" /> <input
						type="submit" name="rt" value="Redact"> <input
						type="submit" name="rt" value="Decline"
						onclick="return confirm('Are you sure? All orders and rides will be deleted')">
					<select name="rasesubject"><c:forEach items="${races }"
							var="item">
							<option><c:out value="${item.ID}"></c:out></option>
						</c:forEach>
					</select>
				</form>
			</c:if>
			<br>
			<c:if test="${userRole eq 'DRIVER'}">
				<h2>Please, set car characteristics</h2>
				<form id="order_form" action="controller" method="post">
					<input type="hidden" name="command" value="makeorder" /> <label>Weight</label><br>
					<input name="weight" /><br> <label>Capacity</label><br> <input
						name="capacity" /><br> <label>Speed</label><br> <input
						name="speed" /><br> <input type="submit"
						value="Make an Order" onclick="return confirm('Are you sure?')">
					<select name="orderrace">
						<c:forEach items="${races }" var="item">
							<option><c:out value="${item.ID }"></c:out></option>
						</c:forEach>
					</select>
				</form>
			</c:if>
			<c:if test="${userRole ne 'DRIVER'}">
				<h2>Add new Race</h2>
				<form id="add_form" action="controller" method="post">
					<input type="hidden" name="command" value="addrace" /> <label>Date
						of Race</label><br> <input name="dateOfRace"><br> <label>From</label><br>
					<input name="from"><br> <label>To</label><br> <input
						name="to"><br> <label>Time of Arrive</label><br>
					<input name="timeOfArrive"><br> <label>Type of
						race</label><br> <select name="typeOfRace">
						<option>Cargo, less than 5 tonnes.</option>
						<option>Cargo from 5 to 10 tonnes.</option>
						<option>Cargo more than 10 tonnes.</option>
						<option>Passenger less than 10 people.</option>
						<option>Passenger less than 25 people.</option>
						<option>Passenger less than 50 people.</option>
					</select><br> <input type="submit" value="Add Race"
						onclick="return confirm('Are you sure?')">
				</form>
			</c:if>
		</div>
		<x:mail/>
	</div>
</body>
</html>