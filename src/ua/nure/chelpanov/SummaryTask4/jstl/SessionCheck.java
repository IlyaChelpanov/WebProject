package ua.nure.chelpanov.SummaryTask4.jstl;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import ua.nure.chelpanov.SummaryTask4.Path;

public class SessionCheck extends TagSupport {

	private static final long serialVersionUID = 544690803301673849L;

	public int doStartTag() throws JspException {

		HttpSession session = pageContext.getSession();
		if (session == null) {
			try {
				pageContext.forward(Path.PAGE_LOGIN);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		return SKIP_BODY;
	}
}
