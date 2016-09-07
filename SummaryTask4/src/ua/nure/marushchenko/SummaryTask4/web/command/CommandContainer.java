package ua.nure.marushchenko.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.marushchenko.SummaryTask4.web.command.NoCommand;

//import org.apache.log4j.Logger;

/**
 * Holder for all commands.
 * 
 * @author Yulia Marushchenko
 * 
 */
public class CommandContainer {
	
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<String, Command>();
	
	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("administrator", new AdministratorCommand());
		commands.put("client", new ClientCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("block", new BlockCommand());
		commands.put("payment", new PaymentCommand());
		commands.put("blockUser", new BlockUserCommand());
		commands.put("unblockUser", new UnblockUserCommand());
		
		
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
	
}