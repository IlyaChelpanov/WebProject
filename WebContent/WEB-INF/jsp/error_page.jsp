<%@ page isErrorPage="true"%>
<%@ page import="java.io.PrintWriter"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/tags.jspf"%>

<html>

<c:set var="title" value="Error" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<%@ include file="/WEB-INF/jspf/top.jspf"%>
	<div id="wrap">
		<div id="main">
			<table id="main-container">

				<tr>
					<td class="content">
						<%-- CONTENT --%>

						<h2 class="error">The following error occurred</h2> <c:set
							var="code"
							value="${requestScope['javax.servlet.error.status_code']}" /> <c:set
							var="message"
							value="${requestScope['javax.servlet.error.message']}" /> <c:set
							var="exception"
							value="${requestScope['javax.servlet.error.exception']}" /> <c:if
							test="${not empty code}">
							<h3>Error code: ${code}</h3>
						</c:if> <c:if test="${not empty message}">
							<h3>${message}</h3>
						</c:if> <c:if test="${not empty exception}">
							<%
								exception.printStackTrace(new PrintWriter(out));
							%>
						</c:if> <%-- if we get this page using forward --%> <c:if
							test="${not empty requestScope.errorMessage}">
							<h3>${requestScope.errorMessage}</h3>
						</c:if>

					</td>
				</tr>



			</table>
		</div>
	</div>
</body>
</html>