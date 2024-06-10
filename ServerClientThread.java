package lbj3;

import java.io.*;
import java.net.*;

class ServerClientThread extends Thread {
  Socket serverClient;
  int clientNo;
  ServerClientThread(Socket inSocket,int counter){
    serverClient = inSocket;
    clientNo=counter;
  }
  public void run(){
    try{
    	
    	BufferedReader br =new BufferedReader(new InputStreamReader(System.in));  	
    	
      DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
      DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
      String clientMessage="", serverMessage="";
      while(!clientMessage.equals("bye")){
        clientMessage=inStream.readUTF();
        System.out.println("From Client-" +clientNo+ ": "+clientMessage);
        
	    //считывание с консоли, что написал сервер и отправка клиенту
        serverMessage = br.readLine();
        outStream.writeUTF("From Server: "+serverMessage);
        
        outStream.flush();
      }
      inStream.close();
      outStream.close();
      serverClient.close();
    }catch(Exception ex){
      System.out.println(ex);
    }finally{
      System.out.println("Client -" + clientNo + " exit!! ");
    }
  }
}
