import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bridge extends Remote {
	void printMsg() throws RemoteException;

	int add(int num1, int num2) throws RemoteException;

	int[] sorting(int[] arr) throws RemoteException;
}
