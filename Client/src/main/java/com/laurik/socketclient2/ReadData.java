
package com.laurik.socketclient2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadData extends Thread{
    ObjectInputStream dataFromServer ;
    String username;
    
    public ReadData(ObjectInputStream dataFromServer , String username){
        super();
        this.dataFromServer  = dataFromServer ;
        this.username = username;
        
    }
    
     public ReadData(ObjectInputStream dataFromServer){
         super();
         this.dataFromServer  = dataFromServer ;
     }
     public void run(){
         while(true){
             try {
                 String messageFromClients = dataFromServer.readObject().toString();
                String messageList[] = messageFromClients.split(":", 2);
                if(username == messageList[0]){
                   System.out.println(messageList[1] ); 
                }
                 
             } catch (IOException ex) {
                System.out.println("Problema de conexiune");
             } catch (ClassNotFoundException ex) {
                System.out.println("Obiect necunoscut");
             }
         }
         
     }
}
