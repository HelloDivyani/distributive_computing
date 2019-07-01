import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CommandExecutorInterface extends Remote {
	public String getServerIP() throws RemoteException;
	public int totalServer() throws RemoteException;
	public String run(String commandString) throws RemoteException;
}
