
package com.laurik.socket2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientThread extends Thread{
    private ArrayList<Socket> clientList = new ArrayList<Socket>();
    private ArrayList<ObjectOutputStream> oosClientList = new ArrayList<ObjectOutputStream>();
    private ArrayList<ClientReader> clientReaderList = new ArrayList<ClientReader>();
    public ClientThread (){
    super();
    }
    
    public synchronized void addSocket(Socket client) throws IOException{
        oosClientList.add(new ObjectOutputStream(client.getOutputStream()));
        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
        ClientReader reader = new ClientReader(ois);
        reader.start();
        clientReaderList.add(reader);
               
        clientList.add(client);
    }
    
    
    @Override
    public void run(){
         while(true){
            //System.out.println("Numarul Clientilor este: " + clientList.size());
            for(int i = 0 ; i< clientList.size() ; i++){
                try {
                    ClientReader clientReader = clientReaderList.get(i);
                    if(clientReader.isMessage()){
                        String message = clientReader.getLastMessage();
                        System.out.println("messajul este: "+ message);
                        for(int j = 0 ; j < clientList.size(); j++){
                            if(i != j){
                                ObjectOutputStream oos= oosClientList.get(j);
                                oos.writeObject(message);
                            }
                        }
                    }
                } catch (IOException ex) {
                    System.out.println("Eroare de conexiune");
                }
               
            }
        }
    }
    
}
