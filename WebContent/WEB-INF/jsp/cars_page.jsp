<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/tags.jspf"%>
<html>
<c:if test="${userRole eq 'DRIVER'}">
	<body>Access Denied
	</body>
</c:if>
<c:if test="${userRole ne 'DRIVER'}">
	<c:set var="title" value="Cars Page"></c:set>
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<body>
		<div id="wrap">
			<c:set var="pageNumber" value="3"></c:set>
			<c:set var="pageType" value="Users Page"></c:set>
			<%@ include file="/WEB-INF/jspf/top.jspf"%>
			<div id="main">
				<h2>Here is all aviable cars:</h2>
				<table>
					<tr>
						<th>Car №</th>
						<th>Model</th>
						<th>Max Weight</th>
						<th>Max Capacity</th>
						<th>Max Speed</th>
						<th>Car Condition</th>
					</tr>
					<c:forEach items="${cars}" var="item">
						<tr>
							<td>${item.ID }</td>
							<td>${item.model }</td>
							<td>${item.maxWeight }</td>
							<td>${item.maxCapacity }</td>
							<td>${item.maxSpeed }</td>
							<td>${item.carCondition }</td>


						</tr>
					</c:forEach>
				</table>
				<a href = "http://localhost:8080/SummaryTask4/controller?command=brokencaraccess">Show broken cars</a>
				<c:if test="${userRole eq 'ADMINISTRATOR' }">
					<form id="redact_cars" action="controller" method="post">
						<input type="hidden" name="command" value="redactcars" /> <select
							name="carnum">
							<c:forEach items="${cars }" var="item">
								<option>${item.ID}</option>
							</c:forEach>
						</select> <input type="submit" name="rt" value="Redact Car"> <input
							type="submit" name="rt" value="Delete"
							onclick="return confirm('Are you sure?')">
					</form>
				</c:if>
			</div>

			<div id="sidebar">
				<c:if test="${userRole eq 'ADMINISTRATOR' }">
					<h2>Add new Car</h2>

					<form id="add_form" action="controller" method="post">
						<input type="hidden" name="command" value="addcar" /> <label>Model</label><br>
						<input name="Model"><br> <label>Max Weight</label><br>
						<input name="weight"><br> <label>Max Capacity</label><br>
						<input name="capacity"><br> <label>Max Speed</label><br>
						<input name="speed"><br> <input type="submit"
							value="Add Car" onclick="return confirm('Are you sure?')">
					</form>
				</c:if>

			</div>
<x:mail/>
		</div>
	</body>
</c:if>
</html>