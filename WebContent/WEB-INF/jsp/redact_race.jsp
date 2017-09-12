<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/tags.jspf"%>
<html>
<c:set var="title" value="Employees Page"></c:set>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<div id="wrap">
		<c:set var="pageNumber" value="3"></c:set>
		<c:set var="pageType" value="Races Page"></c:set>
		<%@ include file="/WEB-INF/jspf/top.jspf"%>
		<div id="main">
			<h2>Redact Race</h2>

			<form id="add_form" action="controller" method="post">
				<input type="hidden" name="command" value="updaterace" /> <input
					type="hidden" name="subject" value="${redactrace.ID}" /> <label>Date
					of Ride</label><br> <input name="date" value="${redactrace.date}"><br>
				<label>From</label><br> <input name="from"
					value="${redactrace.startCity}"><br> <label>To</label><br>
				<input name="to" value="${redactrace.endCity }"><br> <label>Time</label><br>
				<input name="time" value="${redactrace.timeOfArrive }"><br>
				<label>Type Of Race</label><br> <select name="racetype">
					<option>Cargo, less than 5 tonnes</option>
					<option>Cargo from 5 to 10 tonnes</option>
					<option>Cargo more than 10 tonnes</option>
					<option>Passenger less than 10 people</option>
					<option>Passenger less than 25 people</option>
					<option>Passenger less than 50 people</option>
				</select><br> <label>Race Status</label><br> <select
					name="racestatus">
					<option>Free</option>
					<option>Engaged</option>
					<option>Declined</option>
				</select> <input type="submit" value="Update Race"
					onclick="return confirm('Are you sure?')">
			</form>

		</div>

		<div id="sidebar"></div>
<x:mail/>
	</div>
</body>
</html>