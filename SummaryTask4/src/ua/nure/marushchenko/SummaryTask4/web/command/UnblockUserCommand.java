package ua.nure.marushchenko.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.marushchenko.SummaryTask4.Path;
import ua.nure.marushchenko.SummaryTask4.db.DBManager;
import ua.nure.marushchenko.SummaryTask4.exception.AppException;

public class UnblockUserCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1666281073841052291L;
	
	private static final Logger LOG = Logger.getLogger(UnblockUserCommand.class);
	

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		HttpSession s = request.getSession(true);
		String login = request.getParameter("login");
		String forward = Path.ADMIN_PAGE;
		
		LOG.trace("Unblocking user: login --> " + login);

		DBManager.getInstance().unblockingUser(login);
		
		LOG.trace("Unblocking user done: login --> " + login);


		LOG.debug("Command finished");
		return forward;
	}

}
