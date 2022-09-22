
package com.laurik.socket2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

    private static ServerSocket server;
    private static int port = 9000;
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        server = new ServerSocket(port);
     
        ClientThread clientThreads = new ClientThread();
        clientThreads.start();
        while(true){
            System.out.print("Waiting for the client request....");
            clientThreads.addSocket(server.accept());
            System.out.println("Client connected");
            
            
        }
            }
}
