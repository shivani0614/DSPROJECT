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

import java.rmi.Remote;
import java.rmi.RemoteException;

public class Server extends UnicastRemoteObject implements Bridge{

	private Map<Integer, int[]> resultMap = new HashMap<>();
	private int operationCount;

	protected Server() throws RemoteException {
		super();
	}

	public void printMsg() {
		System.out.println("client accepted");
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

	public void operations(int[] arr, int operationType){
		operationCount++;
		Addition add = null;
		Sort sort = null;
		switch (operationType) {
			case 1:
				add = new Addition(arr, resultMap, operationCount);
				break;
			case 2:
				sort = new Sort(arr, resultMap, operationCount);
				break;
		}
	}

	public int[] returnResult(int operationNumber){
		return resultMap.get(operationNumber);
	}

	public Map<Integer, int[]> returnAllResults(){
		return resultMap;
	}
}

class Addition implements Runnable{
	private int[] inputArray;
	private int operationCount;
	private Map<Integer, int[]> resultMap;
	
	Addition(int[] inputArray, Map<Integer, int[]> resultMap, int operationCount) {
		this.inputArray = inputArray;
		this.resultMap = resultMap;
		this.operationCount = operationCount;
		new Thread(this).start();
	}
	
	public void run(){
		int res = inputArray[0] + inputArray[1];
		int[] resultArray = new int[1];
		resultArray[0] = res;
		resultMap.put(operationCount, resultArray);
	}
}

class Sort implements Runnable{
	private int[] inputArray;
	private int operationCount;
	private Map<Integer, int[]> resultMap;
	
	Sort(int[] inputArray, Map<Integer, int[]> resultMap, int operationCount) {
		this.inputArray = inputArray;
		this.resultMap = resultMap;
		this.operationCount = operationCount;
		new Thread(this).start();
	}
	
	public void run(){
		Arrays.sort(inputArray);
		resultMap.put(operationCount, inputArray);
	}
}
