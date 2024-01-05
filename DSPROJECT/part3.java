import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class part3 {
	public static void main(String[] args) {
		Timer timer = new Timer();
		TimerTask task = new HelperThread();
		timer.schedule(task, 100, 12000); // 6000 is the periodic value

	}
}

class HelperThread extends TimerTask {
	private static final String clientsyncfolder = "/Users/Documents/part3/client/";

	Set<String> oldfiles = new HashSet<>();
	Set<String> newfiles = new HashSet<>();
	HashMap<String,Long> oldhashmap = new HashMap<>();
	HashMap<String,Long> newhashmap = new HashMap<>();

	public void run() {
		File file = new File(clientsyncfolder);
		File[] filelist = file.listFiles();
		if (filelist != null){
		if (oldfiles !=null & oldfiles.size() > 0) { // First time creation
			for (File fil : filelist) {
				oldfiles.add(fil.getName());
				oldhashmap.put(fil.getName(), fil.lastModified());
				File clientfile = new File(clientsyncfolder + fil.getName());
				byte[] data = new byte[(int) clientfile.length()];

				try {
					FileInputStream fileInputStream = new FileInputStream(clientfile);
					fileInputStream.read(data, 0, data.length);
					Bridge stub = (Bridge) Naming.lookup("rmi://localhost:9990/FILESOFTWARE");
					stub.fileupload(data, fil.getName());
					fileInputStream.close();
					System.out.println("upload is done.");
				} catch (Exception e) {
					// TODO Auto-generated catch block

					e.printStackTrace();
				}
			}
		} else {
			for (File fil : filelist) {
				newfiles.add(fil.getName());
				newhashmap.put(fil.getName(), fil.lastModified());
			}

			Set<String> newtemp = new HashSet<>();
			newtemp.addAll(newfiles);

			Set<String> oldtemp = new HashSet<String>();
			oldtemp.addAll(oldfiles);

			oldtemp.removeAll(newfiles);
			// Foreah file in old temp write delete logic
			for (String old : oldtemp) {
				try {
					Bridge stub = (Bridge) Naming.lookup("rmi://localhost:9990/FILESOFTWARE");
					stub.filedelete(old);
					System.out.println("Delete file");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			newtemp.removeAll(oldfiles);
			// Foreach file in newtemp write new insert
			for (String ne : newtemp) {
				File clientfile = new File(clientsyncfolder + ne);
				byte[] data = new byte[(int) clientfile.length()];

				try {
					FileInputStream fileInputStream = new FileInputStream(clientfile);
					fileInputStream.read(data, 0, data.length);
					Bridge stub = (Bridge) Naming.lookup("rmi://localhost:9990/FILESOFTWARE");
					stub.fileupload(data, ne);
					fileInputStream.close();
					System.out.println("upload is done.");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			oldfiles = new HashSet<>();
			oldfiles.addAll(newfiles);
			newfiles = new HashSet<>();
			
			//Update file
			if(newhashmap.size() == oldhashmap.size()) {
				for (String key : newhashmap.keySet()) {
					Long currenttime = newhashmap.get(key);
					Long oldtime = oldhashmap.get(key);
					if(!currenttime.equals(oldtime)) {
						File clientfile = new File(clientsyncfolder + key);
						byte[] data = new byte[(int) clientfile.length()];

						try {
							FileInputStream fileInputStream = new FileInputStream(clientfile);
							fileInputStream.read(data, 0, data.length);
							Bridge stub = (Bridge) Naming.lookup("rmi://localhost:9990/FILESOFTWARE");
							stub.fileupload(data, key);
							fileInputStream.close();
							System.out.println("update is done.");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			oldhashmap = new HashMap<String,Long>();
			oldhashmap.putAll(newhashmap);
			newhashmap = new HashMap<String,Long>();

		}
		}

	}
}
