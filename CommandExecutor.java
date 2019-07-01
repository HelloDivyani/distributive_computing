import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CommandExecutor implements CommandExecutorInterface {

	public CommandExecutor() throws RemoteException {
	}

	public int totalServer() throws RemoteException
	{
		
	}

	
	public String run(String commandString)  {
		String result = "";
		String line;
		try {
			// start the shell command running as a child processes
			Process child = Runtime.getRuntime().exec(parseCommand(commandString));

			// open a BufferedReader to read the output of the child process
			BufferedReader output = new BufferedReader(new InputStreamReader(child.getInputStream()));
			// while the child process is still outputting, add the output to the result string
			while ((line = output.readLine()) != null) {
				result = result.concat(line);
				result = result.concat("\n");
			}

			result = result.concat("\n");
			// add "END_MESSAGE" to the result string. When the client sees END_MESSAGE it
			// will know that the server is done sending
			result = result.concat("END_MESSAGE");
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Run Function");
		return result;
	}

	/**
	 * Converts the digit string into its respective shell command.
	 * 
	 * @param inputString		A string containing a single digit, 1-6;
	 * @return			A string containing the shell command to run		
	 */
	private String parseCommand(String inputString) {
		int inputInt = Integer.parseInt(inputString);
		String commandString = "";
		switch (inputInt) {
			// Date
			case 1:
				commandString = "date";
				break;

				// Uptime
			case 2:
				commandString = "uptime";
				break;

				// Memory use
			case 3:
				commandString = "free";
				break;

				// netstat
			case 4:
				commandString = "netstat";
				break;

				// current users
			case 5:
				commandString = "who";
				break;

				// running processes
			case 6:
				commandString = "ps -e";
				break;
		}
		System.out.println("Are u here");
		return commandString;
	}
}
