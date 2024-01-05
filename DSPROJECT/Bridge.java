import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Bridge extends Remote {
    void fileupload(byte[] data, String folderPath) throws RemoteException;
	byte[] filedownloads(String filename) throws RemoteException;
	void filerename(String pastfilename, String newfilename) throws RemoteException;
	boolean filedelete(String filename) throws RemoteException;
}