import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class Client {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        try {
            Bridge stub = (Bridge) Naming.lookup("rmi://localhost:9990/FILESOFTWARE");
            stub.printMsg();
            int option;
            do {
                System.out.println(
                        "Please choose any one operation : 1-> Add or 2-> Sort or 3-> Get Result of Operation 4-> Display all results  5-> Exit");
                option = sc.nextInt();
                switch (option) {
                    case 1: {
                        System.out.println("Addition of Two Integers");
                        int[] arr = new int[2];
                        System.out.println("Enter first number: ");
                        arr[0] = sc.nextInt();
                        System.out.println("Enter second number:: ");
                        arr[1] = sc.nextInt();
                        stub.operations(arr, 1);
                        break;
                    }
                    case 2: {
                        System.out.println("Sorting an array");
                        System.out.println("Enter number of elements in the array: ");
                        int n = sc.nextInt();
                        int array[] = new int[n];
                        System.out.println("Enter elements of the array: ");
                        for (int i = 0; i < n; i++) {
                            array[i] = sc.nextInt();
                        }
                        stub.operations(array, 2);
                        break;
                    }
                    case 3: {
                        System.out.println("Enter the operation you want results for: ");
                        int operationNumber = sc.nextInt();
                        int[] sortedArray = stub.returnResult(operationNumber);
                        System.out.print("Result: ");
                        for (int i : sortedArray) {
                            System.out.print(i + " ");
                        }
                        System.out.println();
                        break;
                    }
                    case 4: {
                        Map<Integer, int[]> resultMap = stub.returnAllResults();
                        System.out.println("Operation Number       Result");
                        for (int i : resultMap.keySet()) {
                            int[] arr = resultMap.get(i);
                            System.out.print(i + "                          ");
                            for (int j : arr) {
                                System.out.print(j + " ");
                            }
                            System.out.println();
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("Program will now exit ");
                        System.exit(0);
                    }
                }
            } while (option != 5);

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

}