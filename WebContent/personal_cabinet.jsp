<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/tags.jspf"%>
<html>
<c:if test="${userRole=='ADMINISTRATOR'}">
	<c:set var="title" value="Administrator Page"></c:set>
</c:if>
<c:if test="${userRole=='DISPATCHER'}">
	<c:set var="title" value="Dispatcher Page"></c:set>
</c:if>
<c:if test="${userRole=='DRIVER'}">
	<c:set var="title" value="Driver Page"></c:set>
</c:if>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
<c:set var="pageNumber" value="1"></c:set>
	<div id="wrap">
		<%@ include file="/WEB-INF/jspf/top.jspf"%>
		<div id="main">
			<h2><fmt:message key="personal_cabinet.label.pc"></fmt:message></h2>
			<ul>
				<c:if test="${userRole=='ADMINISTRATOR'}">
					<li><a
						href="http://localhost:8080/SummaryTask4/controller?command=useraccess"><fmt:message key="personal_cabinet.label.users"></fmt:message></a></li>
					<li><a href="http://localhost:8080/SummaryTask4/controller?command=racesaccess"><fmt:message key="personal_cabinet.label.races"></fmt:message></a></li>
					<li><a href="http://localhost:8080/SummaryTask4/controller?command=carsaccess"><fmt:message key="personal_cabinet.label.cars"></fmt:message></a></li>
					<li><a href="http://localhost:8080/SummaryTask4/controller?command=orderaccess"><fmt:message key="personal_cabinet.label.orders"></fmt:message></a></li>
					<li><a href="http://localhost:8080/SummaryTask4/controller?command=rideaccess"><fmt:message key="personal_cabinet.label.rides"></fmt:message></a></li>
					<li><a href="http://localhost:8080/SummaryTask4/controller?command=chatpage"><fmt:message key="personal_cabinet.label.chat"></fmt:message></a></li>
				</c:if>
				<c:if test="${userRole=='DISPATCHER'}">
					<li><a href="http://localhost:8080/SummaryTask4/controller?command=racesaccess"><fmt:message key="personal_cabinet.label.races"></fmt:message></a></li>
					<li><a href="http://localhost:8080/SummaryTask4/controller?command=carsaccess"><fmt:message key="personal_cabinet.label.cars"></fmt:message></a></li>
					<li><a href="http://localhost:8080/SummaryTask4/controller?command=orderaccess"><fmt:message key="personal_cabinet.label.orders"></fmt:message></a></li>
					<li><a href="http://localhost:8080/SummaryTask4/controller?command=rideaccess"><fmt:message key="personal_cabinet.label.rides"></fmt:message></a></li>
					<li><a href="http://localhost:8080/SummaryTask4/controller?command=chatpage"><fmt:message key="personal_cabinet.label.chat"></fmt:message></a></li>
				</c:if>
				<c:if test="${userRole=='DRIVER'}">
					<li><a href="http://localhost:8080/SummaryTask4/controller?command=racesaccess"><fmt:message key="personal_cabinet.label.races"></fmt:message></a></li>
					<li><a href="http://localhost:8080/SummaryTask4/controller?command=orderaccess"><fmt:message key="personal_cabinet.label.orders"></fmt:message></a></li>
					<li><a href="http://localhost:8080/SummaryTask4/controller?command=rideaccess"><fmt:message key="personal_cabinet.label.rides"></fmt:message></a></li>
					<li><a href="http://localhost:8080/SummaryTask4/controller?command=chatpage"><fmt:message key="personal_cabinet.label.chat"></fmt:message></a></li>
					</c:if>
			</ul>
		</div>

		<div id="sidebar">

			
		</div>
<x:mail/>
	</div>
</body>
</html>
