package com.example;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public static void main(String[] args) throws Exception { 

    int x=0; //?variabile contatore dei client

    ServerSocket ss = new ServerSocket(3000);
    System.out.println("Server in ascolto sulla porta 3000");
    boolean running = true;
    while (running) {
      Socket s = ss.accept();
      System.out.println("Client connesso");
      x++; //!incremento varibile
      ClientHandler client = new ClientHandler(s,x);
      client.start();
    }
    ss.close();
  }
}
