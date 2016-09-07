package ua.nure.marushchenko.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.marushchenko.SummaryTask4.Path;
import ua.nure.marushchenko.SummaryTask4.db.DBManager;
import ua.nure.marushchenko.SummaryTask4.db.Role;
import ua.nure.marushchenko.SummaryTask4.db.entity.User;
import ua.nure.marushchenko.SummaryTask4.exception.AppException;

public class RegistrationCommand extends Command {

	private static final long serialVersionUID = 5180557392042306572L;

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();
		String forward = Path.REGISTRATION_PAGE;

		if (request.getAttribute("post") != null) {
			DBManager manager = DBManager.getInstance();

			// obtain login, password, first name, last name from a request

			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String confirmPasswordPassword = request.getParameter("confirmPassword");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			if (login == null || password == null || login.isEmpty() || password.isEmpty() || firstName == null
					|| lastName == null || firstName.isEmpty() || lastName.isEmpty()) {
				throw new AppException("Login/password/first name/ last name cannot be empty");
			}

			User user = manager.findUserByLogin(login);
			LOG.trace("Found in DB: user --> " + user);

			if (user != null) {
				throw new AppException("choose another login");
			} else if (!password.equals(confirmPasswordPassword)) {
				throw new AppException("password doesn't match");
			} else {
				User newUser = new User();
				newUser.setLogin(login);
				newUser.setPassword(password);
				newUser.setFirstName(firstName);
				newUser.setLastName(lastName);
				newUser.setRoleId((byte) 2);
				boolean created = manager.createUser(newUser);
				if (created) {
					forward = Path.PAGE_LOGIN;
					/*session.setAttribute("user", newUser);
					LOG.trace("Set the session attribute: user --> " + user);

					Role userRole = Role.getRole(newUser);

					LOG.trace("userRole --> " + userRole);

					session.setAttribute("userRole", userRole);
					LOG.trace("Set the session attribute: userRole --> " + userRole);*/
				}
			}
		}

		LOG.debug("Command finished");
		return forward;
	}

}
