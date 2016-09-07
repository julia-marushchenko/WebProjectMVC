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

public class PaymentCommand extends Command {

	private static final Logger LOG = Logger.getLogger(PaymentCommand.class);

	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		String forward = Path.PAGE_PAYMENT;
		
		//int cardNumber = Integer.parseInt(request.getParameter("card"));
		HttpSession s = request.getSession(true);
		//s.setAttribute("cardNumber", cardNumber);
		//int cardNumber = (int)s.getAttribute("cardNumber");
		if (request.getAttribute("post") != null) {
			
			// obtain login and password from a request
			DBManager manager = DBManager.getInstance();
			int amount = Integer.parseInt(request.getParameter("amount"));
			int account = Integer.parseInt(request.getParameter("account"));

			LOG.trace("Request parameter: amount --> " + amount);
			LOG.trace("Request parameter: account --> " + account);

			int card = (Integer)s.getAttribute("cardNumber");
			
			manager.makePayment(amount, account, card);
			forward = Path.CLIENT_PAGE;
		}
		LOG.debug("Command finished");
		return forward;
	}

}
