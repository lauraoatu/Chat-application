package com.laurik.socketclient2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient2 {

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        String hostname = "127.0.0.1";
        int port = 9000;
        String message;
        Scanner scanner = new Scanner (System.in);
               
           System.out.print("Connecting to server....");
            System.out.println("Introduceti username ul: ");
            String username = scanner.nextLine();
                   
             Socket socket = new Socket(hostname, port);
             System.out.println("Conected");
             
            
            ObjectOutputStream oosclient = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream scan = new ObjectInputStream(socket.getInputStream());
            System.out.println("InputStream Connected");
            //oosclient.writeChars(); 
            ReadData info = new ReadData(scan, username);//citeste data de la server int un alt thread 
            info.start();
             
            // System.out.println("Citire date Server:");

             //System.out.println(scan.readObject());
           
           //oosclient.writeObject("Hi server from client !");
            while(true){
                System.out.println("Inroduceti mesaj");
                message = scanner.nextLine();
                oosclient.writeObject(message);
                //oosclient.writeObject(scanner.nextLine());
            }
    }
}
