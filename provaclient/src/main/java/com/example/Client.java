package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 3000);
        boolean condizione=true;

        // per parlare
        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);

        // per ascoltare
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        // per la tastiera
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));

        pr.println("Eccomi");
        System.out.println(br.readLine()); // rivevo: benvenuti dammi il tuo nome
        pr.println(tastiera.readLine()); //! invio da tastiera il nome 
        System.out.println(br.readLine()); //? ricevo: il nome e il numero del client ricevuti in quella sessione
        System.out.println(br.readLine()); //* rivevo i saluti dal server*/
        
        while(condizione){
            System.out.println(br.readLine());//che comando vuoi inviare?
            String x=tastiera.readLine();
            pr.println(x); //? invio il comando 
                if(x.equals("fine")){
                     break;
                }
            
            System.out.println(br.readLine());
        }
        s.close();
    }
}
