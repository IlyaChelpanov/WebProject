<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/tags.jspf"%>
<html>
<c:if test="${userRole ne 'ADMINISTRATOR'}">
	<body>Access Denied
	</body>
</c:if>
<c:if test="${userRole=='ADMINISTRATOR'}">
	<c:set var="title" value="Employees Page"></c:set>
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<body>
		<div id="wrap">
			<c:set var="pageNumber" value="3"></c:set>
			<c:set var="pageType" value="Users Page"></c:set>
			<%@ include file="/WEB-INF/jspf/top.jspf"%>
			<div id="main">
				<h2>Redact User</h2>

				<form id="add_form" action="controller" method="post">
					<input type="hidden" name="command" value="updateuser" /> 
					<input type="hidden" name="subject" value="${redactuser.username }"/>
					<label>Full
						Name</label><br> <input name="fullName"
						value="${redactuser.fullName}"><br> <label>Username</label><br>
					<input name="username" value="${redactuser.username}"><br>
					<label>Password</label><br> <input name="password"
						value="${redactuser.password }"><br> <label>Role</label><br>
					<select name="role">
						<option>Administrator</option>
						<option>Dispatcher</option>
						<option>Driver</option>
					</select><br> <label>E-mail</label><br> <input name="email"
						value="${redactuser.email }"><br> <input
						type="submit" value="Update User"
						onclick="return confirm('Are you sure?')">
				</form>

			</div>

			<div id="sidebar"></div>
<x:mail/>
		</div>
	</body>
</c:if>
</html>