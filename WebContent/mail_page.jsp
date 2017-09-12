<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/tags.jspf"%>
<html>
<c:set var="title" value="Mail Page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<c:set var="pageNumber" value="1"></c:set>
<body>

	<div id="wrap">
		<%@ include file="/WEB-INF/jspf/top.jspf"%>

		<div id="main">

		<b>Send e-mail</b>
			<form id="login_form" action="controller" method="post">
			<input type="hidden" name="command" value="sendmail" />
				<table>
					<tr>
						<td><b>To(email):</b></td>
						<td><input name="to" type="text" size=40 value = "ilyachelpanovv@gmail.com"></td>
					</tr>
					<tr>
						<td><b>From(email):</b></td>
						<td><input name="from" type="text" size=40 value = "ilyachelpanovv@gmail.com"></td>
					</tr>
					<tr>
						<td><b>Subject:</b></td>
						<td><input name="subj" type="text" size=40></td>
					</tr>
				</table>
				<hr>
				<textarea name="body" rows=5 cols=45> Добрый день!</textarea>
				<br> <input type="submit" value="Send!">
			</form>

		</div>

		<div id="sidebar"></div>

		<div id="footer">

			<p>Prepared by Chelpanov Ilya</p>

		</div>
	</div>
</body>
</html>