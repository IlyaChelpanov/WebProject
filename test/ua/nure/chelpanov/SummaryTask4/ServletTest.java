package ua.nure.chelpanov.SummaryTask4;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.chelpanov.SummaryTask4.datebase.DBManager;
import ua.nure.chelpanov.SummaryTask4.exceptions.AppException;
import ua.nure.chelpanov.SummaryTask4.exceptions.DBException;
import ua.nure.chelpanov.SummaryTask4.web.Controller;
import ua.nure.chelpanov.SummaryTask4.web.command.LoginCommand;
import ua.nure.chelpanov.SummaryTask4.web.command.NoCommand;


public class ServletTest extends Mockito{
	@Test()
	public void testLogin() throws IOException, ServletException, AppException{
		LoginCommand lc = new LoginCommand();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		when(request.getParameter("username")).thenReturn("admin");
		when(request.getParameter("password")).thenReturn("11e8419d");
		assertTrue(lc.execute(request, response).equals(Path.PAGE_PERSONAL_CABINET));
	}


@Test()
public void testNoCommand(){
	NoCommand nc = new NoCommand();
	HttpServletRequest request = mock(HttpServletRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);
	when(request.getParameter("command")).thenReturn("noCommand");
	assertTrue(nc.execute(request, response).equals(Path.PAGE_ERROR_PAGE));
}

}
