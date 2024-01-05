import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface Bridge extends Remote {
	void printMsg() throws RemoteException;

	void operations(int[] arr, int operationType) throws RemoteException;

	Map<Integer, int[]> returnAllResults() throws RemoteException;

	int[] returnResult(int operationNumber)  throws RemoteException;
}