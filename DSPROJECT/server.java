import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.AlreadyBoundException;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;



public class server extends UnicastRemoteObject implements Bridge{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String serverfolder = "/home/shivani/Desktop/DSPROJECT/server/";

    protected server() throws RemoteException {
        super();
    }

    @Override
    public void fileupload(byte[] data, String filename) {
        try{
        	String serverfolder = "/home/shivani/Desktop/DSPROJECT/server/";
            File file = new File(serverfolder + filename);
            file.createNewFile();
            FileOutputStream serverfile = new FileOutputStream(file);
            serverfile.write(data);
            serverfile.flush();
            serverfile.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
        
    }


    @Override
    public void filerename(String pastfilename, String newfilename) {
    	String serverfolder = "/home/shivani/Desktop/DSPROJECT/server/";
        File file = new File(serverfolder+pastfilename);
        File file2 = new File(serverfolder+newfilename);
        file.renameTo(file2);
        
    }

    @Override
    public boolean filedelete(String filename) {
    	String serverfolder = "/home/shivani/Desktop/DSPROJECT/server/";
    	File file = new File(serverfolder + filename);
		return file.delete();
        
    }

    public static void main(String args[]){

		try {
			Registry registry = LocateRegistry.createRegistry(9990);
			registry.bind("FILESOFTWARE", new server());
			System.out.println("Started Server....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public byte[] filedownloads(String filename) {
		String serverfolder = "/home/shivani/Desktop/DSPROJECT/server/";
		File file = new File(serverfolder+filename);
		byte[] data = new byte[(int) file.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			
			fileInputStream.read(data, 0, data.length);
			fileInputStream.close();
			

		} catch (Exception e) {

			e.printStackTrace();
		}

		return data;
	}
    
}
