package ua.nure.marushchenko.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.marushchenko.SummaryTask4.Path;
import ua.nure.marushchenko.SummaryTask4.db.DBManager;
import ua.nure.marushchenko.SummaryTask4.db.Role;
import ua.nure.marushchenko.SummaryTask4.db.bean.AccountUserBean;
import ua.nure.marushchenko.SummaryTask4.db.entity.Payment;
import ua.nure.marushchenko.SummaryTask4.db.entity.User;
import ua.nure.marushchenko.SummaryTask4.exception.AppException;

public class BlockCommand extends Command {

	private static final Logger LOG = Logger.getLogger(BlockCommand.class);

	private static final long serialVersionUID = -6960046124941879416L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");
		String forward = Path.CLIENT_PAGE;
		HttpSession s = request.getSession(true);
		int cardNumber = Integer.parseInt(request.getParameter("card"));
		if (s.getAttribute("userRole") == Role.ADMIN) {
			forward = Path.ADMIN_PAGE;
		} else {
			forward = Path.CLIENT_PAGE;		
		}
		LOG.trace("Blocking card: cardNumber --> " + cardNumber);

		DBManager.getInstance().blockingCard(cardNumber);

		LOG.trace("Blocking card done: cardNumber --> " + cardNumber);

		LOG.debug("Command finished");
		return forward;

	}

}
