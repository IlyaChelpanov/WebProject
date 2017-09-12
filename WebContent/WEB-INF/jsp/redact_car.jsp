<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/tags.jspf"%>
<html>
<c:if test="${userRole ne 'ADMINISTRATOR'}">
	<body>Access Denied
	</body>
</c:if>
<c:if test="${userRole=='ADMINISTRATOR'}">
	<c:set var="title" value="Cars Page"></c:set>
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<body>
		<div id="wrap">
			<c:set var="pageNumber" value="3"></c:set>
			<c:set var="pageType" value="Cars Page"></c:set>
			<%@ include file="/WEB-INF/jspf/top.jspf"%>
			<div id="main">
				<h2>Redact Car</h2>

				<form action="controller" method="post">
					<input type="hidden" name="command" value="updatecar" /> <input
						type="hidden" name="subject" value="${redactcar.ID }" /> <label>Model</label><br>
					<input name="model" value="${redactcar.model}"><br> <label>Max
						Weight</label><br> <input name="weight"
						value="${redactcar.maxWeight}"><br> <label>Max
						Capacity</label><br> <input name="capacity"
						value="${redactcar.maxCapacity }"><br> <label>Max
						Speed</label><br> <input name="speed" value="${redactcar.maxSpeed }"><br>
					<c:if test="${redactcar.carCondition=='working' }">
						<label>In case any break, write description here.</label>
						<input name="description">
						<br>
					</c:if>

					<input type="submit" value="Update Car"
						onclick="return confirm('Are you sure?')">
				</form>
				<c:if test="${redactcar.carCondition=='broken' }">
					<form action="controller" method="post">
						<input type="hidden" name="command" value="repaircar" /> 
						<input
						type="hidden" name="subject" value="${redactcar.ID }" /><input
							type="submit" value="Repair Car">
					</form>
				</c:if>

			</div>

			<div id="sidebar"></div>
<x:mail/>
		</div>
	</body>
</c:if>
</html>