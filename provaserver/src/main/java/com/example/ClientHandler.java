package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClientHandler extends Thread {
    private Socket s;
    private PrintWriter pr = null;
    private BufferedReader br = null;
    public int x;
    private boolean condizione=true;
    private String output;
    static String nomeServer = "ServerVivoli";

    public ClientHandler(Socket s,int y) {
        x=y;
        this.s = s;
        try {
            // per parlare
            pr = new PrintWriter(s.getOutputStream(), true);
            // per ascoltare
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {

            System.out.println(br.readLine());
            pr.println("Ciao come ti chiami?"); // invio messaggio
            String nome = br.readLine(); //? ricevo: il nome
            String nomeM = nome.toUpperCase(); //! conversione in maiuscolo
            System.out.println("Utente "+nomeM);
            pr.println("Benvenuto "+ nomeM+" sei l'utente numero "+ x); // invio messaggio
            
            pr.println("Ciao "+ nomeM +" piacere di averti conosciuto, alla prossima!!"); // invio il bmi
            
            //? parte 3
            while(condizione){
                pr.println("che comando vuoi inserire?");
                String comando=br.readLine();
                
                if(comando.equals("data")){
                    Calendar cal = new GregorianCalendar();
                    int giorno = cal.get(Calendar.DAY_OF_MONTH);
                    int mese = cal.get(Calendar.MONTH);
                    int anno = cal.get(Calendar.YEAR);
                    output = giorno + "-" + (mese + 1) + "-" + anno;
                    pr.println(output);
                }else if(comando.equals("ora")){
                    String timestamp = ZonedDateTime.now(ZoneId.of("Europe/Berlin"))
                    .format(DateTimeFormatter.ofPattern("hh.mm.ss a"));

                    pr.println("L'ora attuale è: " + timestamp);
                }else if(comando.equals("fine")){
                    System.out.println("Connessione chiusa con utente n. " + x);
                    s.close();
                }else if(comando.equals("id")){
                    pr.println("Sei l'utente numero: " + x);
                }else if(comando.equals("nome")){
                    pr.println("Il nome del server è: " + nomeServer);
                }else{
                    pr.println("Il comando inserito non è valido");
                }
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
