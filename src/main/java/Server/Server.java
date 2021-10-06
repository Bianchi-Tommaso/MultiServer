package Server;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{
    private int porta;
    ServerSocket server = null;
    String stringRicevuta = null;
    String stringModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    GestioneThread gestioneThread;
    
    public Server(int porta) 
    {
        this.porta = porta;
    }
    
    public void Start()
    {
        try
        {
            server = new ServerSocket(porta);   

            System.out.println("Server partito");
            
            GestioneThread gestisciClient = new GestioneThread(server);

            for(;;)
            {
                System.out.println("Server in attesa...");
                Socket socket = server.accept();

                System.out.println("Server Socket: " + socket);
                ServerThread serverThread = new ServerThread(socket, server, gestisciClient);
                
                gestisciClient.AggiungiClient(socket);

                serverThread.start();
            }
        }
        catch(Exception e)
        {
            System.out.println("Errore Durante La Connessione");
            System.exit(1);
        }

    }
  
   

    /*
    public void Comunica()
    {
        try
        {
            System.out.println("3 Benvenuto Client, Scrivi Una Frase E la Trasformo In Maiuscolo. Attendo...");
            stringRicevuta = inDalClient.readLine();
            System.out.println("6 Ricevuta la Stringa Dal Client: " + stringRicevuta);

            stringModificata = stringRicevuta.toUpperCase();
            System.out.println("7 Invio La Stringa Modificata Al Client... ");
            outVersoClient.writeBytes(stringModificata + '\n');

            System.out.println("9 SERVER: Fine Elaborazione .... Buona Notte!!!");
            client.close();
        }   
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore Durante La Connessione");
            System.exit(1);
        }
    }
    */
}
