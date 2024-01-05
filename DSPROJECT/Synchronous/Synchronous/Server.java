import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.AlreadyBoundException;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server extends UnicastRemoteObject implements Bridge {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Server() throws RemoteException {
		super();
	}

	public static void main(String args[]) {

		try {
			Registry registry = LocateRegistry.createRegistry(9990);
			registry.bind("FILESOFTWARE", new Server());
			System.out.println("Started Server....");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

	public void printMsg() {
		System.out.println("client accepted");
	}

	public int add(int num1, int num2) {
		int c = num1 + num2;
		return c;
	}

	public int[] sorting(int[] arr) {
		Arrays.sort(arr);
		return arr;
	}

	public void table() {
		JFrame jFrame = new JFrame();

		String[][] data = {
				{ "03", "Sam" },
				{ "04", "Derek" },
				{ "05", "Ben" } };

		String[][] name = { { "ID" }, { "jghv" } };
		JTable table = new JTable(data, name);
		table.setBounds(30, 40, 230, 280);

		JScrollPane jScrollPane = new JScrollPane(table);
		jFrame.add(jScrollPane);
		jFrame.setSize(350, 300);
		jFrame.setVisible(true);
	}
}