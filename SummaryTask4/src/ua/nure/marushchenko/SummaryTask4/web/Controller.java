package ua.nure.marushchenko.SummaryTask4.web;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;

import ua.nure.marushchenko.SummaryTask4.Path;
import ua.nure.marushchenko.SummaryTask4.exception.AppException;
import ua.nure.marushchenko.SummaryTask4.web.command.Command;
import ua.nure.marushchenko.SummaryTask4.web.command.CommandContainer;


public class Controller extends HttpServlet{
	private static final long serialVersionUID = 6430757590746496139L;
	private static final Logger LOG = Logger.getLogger(Controller.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("post", "post");
		process(req, resp);
	}
	
	/**
	 * Main method of this controller.
	 */
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		LOG.debug("Controller starts");

		// extract command name from the request
		String commandName = request.getParameter("command");
		LOG.trace("Request parameter: command --> " + commandName);

		// obtain command object by its name
		Command command = CommandContainer.get(commandName);
		LOG.trace("Obtained command --> " + command);

		// execute command and get forward address
		String forward = Path.PAGE_ERROR_PAGE;
		
		try {
			forward = command.execute(request, response);
		} catch (AppException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
		}
		
		LOG.trace("Forward address --> " + forward);

		LOG.debug("Controller finished, now go to forward address --> " + forward);
		
		// go to forward
		request.getRequestDispatcher(forward).forward(request, response);
	}
	
}
