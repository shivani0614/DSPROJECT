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
import java.lang.*;
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
                System.out.println("Please choose any one operation : 1-> Add or 2-> Sort or 3-> Exit");
                option = sc.nextInt();
                switch (option) {
                    case 1: {
                        System.out.println("Addition of Two Integers");
                        System.out.println("Enter first number: ");
                        int a = sc.nextInt();
                        System.out.println("Enter second number:: ");
                        int b = sc.nextInt();
                        System.out.println("Result of adding the two numbers = "+stub.add(a, b));
                        break;
                    }
                    case 2: {
                        System.out.println("Sorting an array");
                        System.out.println("Enter number of elements in the array: ");
                        int n = sc.nextInt();
                        int array[] = new int[n];
                        for (int i = 0; i < n; i++) {
                            array[i] = sc.nextInt();
                        }
                        System.out.println("Array elemets are:");
                        for (int i = 0; i < n; i++) {
                            System.out.println(array[i]);
                        }
                        int[] sortedArray = stub.sorting(array);
                        System.out.print("Sorted array: ");
                        for(int i: sortedArray){
                            System.out.print(i+" ");
                        }
                        System.out.println();
                        break;
                    }
                    case 3: {
                        System.out.println("Program will now exit ");
                        System.exit(0);
                    }
                }
            } while (option != 3);

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

}