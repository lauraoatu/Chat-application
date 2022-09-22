package com.laurik.socket2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientReader extends Thread {
    private ObjectInputStream ois;
    private String lastMessage = "mesaj initial ";
    private boolean messageSucces ;
    public ClientReader(ObjectInputStream ois) {
        super();
        this.ois = ois;
    }
    
    private synchronized void updateSucces(boolean data) {
        messageSucces = data;
    }
    
    public String getLastMessage(){
        messageSucces = false;
        return lastMessage ;
   }
    
    public boolean isMessage(){
        return messageSucces;
    }
    public void run(){
        while(true){
            try {
                lastMessage = ois.readObject().toString();
                updateSucces(true);
                System.out.println("Mesajul primit de ClientReader este:" + lastMessage);
            } catch (IOException ex) {
                System.out.println("Probleme in conexiune");
            } catch (ClassNotFoundException ex) {
                System.out.println("Mesaj incompatibil");
            }
        }
        
    }
    
}
