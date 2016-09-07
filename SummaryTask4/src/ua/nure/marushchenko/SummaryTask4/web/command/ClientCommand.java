package ua.nure.marushchenko.SummaryTask4.web.command;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.marushchenko.SummaryTask4.db.entity.Payment;
import ua.nure.marushchenko.SummaryTask4.db.entity.User;
import ua.nure.marushchenko.SummaryTask4.exception.AppException;
import ua.nure.marushchenko.SummaryTask4.Path;
import ua.nure.marushchenko.SummaryTask4.db.DBManager;
import ua.nure.marushchenko.SummaryTask4.db.bean.AccountUserBean;

public class ClientCommand extends Command {

	private static final long serialVersionUID = 1967967850475711468L;

	private static final Logger LOG = Logger.getLogger(ClientCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		HttpSession s = request.getSession(true);
		User user = (User) s.getAttribute("user");
		String login = user.getLogin();
		String forward = Path.CLIENT_PAGE;
		
		String cardNumber = request.getParameter("card");
		s.setAttribute("cardNumber", cardNumber);
		// get user accounts list
		List<AccountUserBean> accounts = DBManager.getInstance().findUserAccounts(login);
		LOG.trace("Found user accounts: userAccounts --> " + accounts);

		// get user payments list
		List<Payment> payments = DBManager.getInstance().findUserPayments(login);
		LOG.trace("Found user payments: userAccounts --> " + payments);

		request.setAttribute("accounts", accounts);
		LOG.trace("Set the request attribute: accounts --> " + accounts);
		
		request.setAttribute("payments", payments);
		LOG.trace("Set the request attribute: payments --> " + payments);

		LOG.debug("Command finished");
		return forward;
	}

}
