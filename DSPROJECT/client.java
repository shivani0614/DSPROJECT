import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class client {

     
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Type of Operation : upload or delete or rename or download ");
        String type = sc.nextLine();
        try {
            Bridge stub = (Bridge) Naming.lookup("rmi://localhost:9990/FILESOFTWARE");
            String clientfolder = "/home/shivani/Desktop/DSPROJECT/client/";
            if(type.equals("upload")){
                System.out.println("Enter Client File name");
                String clientfilename = sc.nextLine();
                File clientfile = new File(clientfolder+clientfilename);
			    byte[] data = new byte[(int) clientfile.length()];
			    FileInputStream fileInputStream = new FileInputStream(clientfile);
			    fileInputStream.read(data, 0, data.length);
			    stub.fileupload(data, clientfilename);
			    fileInputStream.close();
			    System.out.print("upload is done.");
			    
            }else if(type.equals("delete")){
                System.out.println("Enter Server File Name");
                String serverfilename = sc.nextLine();
                boolean delete = stub.filedelete(serverfilename);
                if(delete == true) {
                	System.out.println("Delete is done");
                }else {
                	System.out.println("Delete is failed");
                }
            }
            else if(type.equals("rename")){
                System.out.println("Enter Current File Name");
                String currentfilename = sc.nextLine();
                System.out.println("Enter New File Name");
                String newfilename = sc.nextLine();
                stub.filerename(currentfilename, newfilename);
                System.out.println("rename is done");

            }else if(type.equals("download")){
                 System.out.println("Enter Server File Name to Download");
                 String serverfilename = sc.nextLine();
                 byte[] mydata = stub.filedownloads(serverfilename);
                 //Creating file at client side
                 File clientpathfile = new File(clientfolder +serverfilename);
			     clientpathfile.createNewFile();
                 //Writing data to client side file
			     FileOutputStream filedata = new FileOutputStream(clientfolder + serverfilename);
			     filedata.write(mydata);
			     filedata.flush();
			     filedata.close();
			     System.out.println("download is done");
            } 
        } 
        catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
