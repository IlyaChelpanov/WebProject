package ua.nure.chelpanov.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;





public class CommandContainer {
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	private static Map<String, Command> commands = new TreeMap<String, Command>();
	static {

		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("useraccess", new UserAccessCommand());
		commands.put("adduser", new AddUserCommand());
		commands.put("redactusers", new RedactUsersCommand());
		commands.put("updateuser", new UpdateUserCommand());
		commands.put("racesaccess", new RacesAccessCommand());
		commands.put("sortrace", new SortRaceCommand());
		commands.put("addrace", new AddRaceCommand());
		commands.put("redactrace", new RedactRacesCommand());
		commands.put("updaterace", new UpdateRaceCommand());
		commands.put("makeorder", new OrderCommand());
		commands.put("orderaccess", new OrderAccessCommand());
		commands.put("acceptorder", new OrderAcceptCommand());
		commands.put("rideaccess", new RideAccessCommand());
		commands.put("carsaccess", new CarsAccessCommand());
		commands.put("executeride", new ExecuteRideCommand());
		commands.put("addcar", new AddCarCommand());
		commands.put("redactcars", new RedactCarCommand());
		commands.put("updatecar", new UpdateCarCommand());
		commands.put("repaircar", new RepairCarCommand());
		commands.put("brokencaraccess", new BrokenCarAccess());
		commands.put("noCommand", new NoCommand());
		commands.put("sendmail", new SendMailCommand());
		commands.put("chatpage", new ChatAccessCommand());
		commands.put("sendmessage", new SendMessageCommand());
	}
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
}
