<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/tags.jspf"%>
<html>
<c:if test="${userRole ne 'ADMINISTRATOR'}">
<body>
Access Denied
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
			<h2><fmt:message key="users_page.label.users"></fmt:message></h2>
			<table>
				<tr>
					<th>Full Name</th>
					<th>Username</th>
					<th>Password</th>
					<th>Role</th>
					<th>E-Mail</th>
				</tr>
				<c:forEach items="${users}" var="item">
					<tr>
						<td>${item.fullName }</td>
						<td>${item.username }</td>
						<td>${item.password }</td>
						<td><c:if test="${item.roleID==1 }">Administrator</c:if> <c:if
								test="${item.roleID==2 }">Dispatcher</c:if> <c:if
								test="${item.roleID==3 }">Driver</c:if></td>
						<td>${item.email }</td>

					</tr>
				</c:forEach>
			</table>
			<form id="redact_users" action="controller" method="post">
				<input type="hidden" name="command" value="redactusers" /> <select
					name="user">
					<c:forEach items="${users }" var="item">
						<option>${item.username }</option>
					</c:forEach>
				</select> <input type="submit" name="rt" value="Redact User"> <input
					type="submit" name="rt" value="Delete" onclick="return confirm('Are you sure?')">
			</form>
		</div>

		<div id="sidebar">

			<h2>Add new User</h2>

			<form id="add_form" action="controller" method="post">
				<input type="hidden" name="command" value="adduser" /> <label>Full
					Name</label><br> <input name="fullName"><br> <label>Username</label><br>
				<input name="username"><br> <label>Password</label><br>
				<input name="password"><br> <label>Role</label><br>
				<select name="role">
					<option>Administrator</option>
					<option>Dispatcher</option>
					<option>Driver</option>
				</select><br> <label>E-mail</label><br> <input name="email"><br>
				<input type="submit" value="Add User"
					onclick="return confirm('Are you sure?')">
			</form>



		</div>
<x:mail/>
	</div>
</body>
</c:if>
</html>