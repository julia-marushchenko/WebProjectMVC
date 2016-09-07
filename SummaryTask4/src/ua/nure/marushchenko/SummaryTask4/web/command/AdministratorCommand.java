package ua.nure.marushchenko.SummaryTask4.web.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;

import ua.nure.marushchenko.SummaryTask4.Path;
import ua.nure.marushchenko.SummaryTask4.db.DBManager;
import ua.nure.marushchenko.SummaryTask4.db.bean.AccountUserBean;
import ua.nure.marushchenko.SummaryTask4.db.entity.CreditCard;
import ua.nure.marushchenko.SummaryTask4.db.entity.User;
import ua.nure.marushchenko.SummaryTask4.exception.AppException;

public class AdministratorCommand extends Command {

	private static final long serialVersionUID = 3241435364772732274L;

	private static final Logger LOG = Logger.getLogger(ClientCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		HttpSession s = request.getSession(true);
		String forward = Path.ADMIN_PAGE;

		// get users list
		DBManager m = DBManager.getInstance();
		List<User> users = m.findUsers();
		
		//List<Integer> cards = new ArrayList<>();
		int cardNumber = 0;
		for(User u : users){
			int id = u.getCardId();
			cardNumber = m.findCardById(id);
			u.setCardNumber(cardNumber);
		}
		LOG.trace("Found user accounts: userAccounts --> " + users);

		request.setAttribute("users", users);
		LOG.trace("Set the request attribute: payments --> " + users);
		
		/*request.setAttribute("cards", cards);*/
		
		LOG.debug("Command finished");

		return forward;
	}

}
