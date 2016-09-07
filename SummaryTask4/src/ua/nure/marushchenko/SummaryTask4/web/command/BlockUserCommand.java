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

public class BlockUserCommand extends Command{

	private static final Logger LOG = Logger.getLogger(BlockUserCommand.class);
	private static final long serialVersionUID = 4439380722733805739L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		HttpSession s = request.getSession(true);
		String login = request.getParameter("login");
		String forward = Path.ADMIN_PAGE;
		
		LOG.trace("Blocking user: login --> " + login);

		DBManager.getInstance().blockingUser(login);
		
		LOG.trace("Blocking user done: login --> " + login);


		LOG.debug("Command finished");
		return forward;
	}

}
