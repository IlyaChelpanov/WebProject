<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/tags.jspf"%>
<html>
<c:set var="title" value="Login" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<c:set var="pageNumber" value="1"></c:set>
<body>

	<div id="wrap">
		<%@ include file="/WEB-INF/jspf/top.jspf"%>

		<div id="main">

			<h2><fmt:message key="login_jsp.label.authorise"></fmt:message></h2>
			<form id="login_form" action="controller" method="post">
				<input type="hidden" name="command" value="login" />
				<fieldset>
				
					<legend><fmt:message key="login_jsp.label.login"></fmt:message></legend>
					<input name="username" required/><br/>
				</fieldset>
				<br/>
				<fieldset>
					<legend><fmt:message key="login_jsp.label.password"></fmt:message></legend>
					<input type="password" name="password" required/>
				</fieldset>
				<br/> <input type="submit" value="Login">
			</form>

		</div>

		<div id="sidebar">
		</div>
<x:mail/>
	</div>
</body>
</html>