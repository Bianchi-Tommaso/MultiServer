package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Questa classe serve per lanciare i thread per la gestione di ogni connessione accettata dal Server.
 * Lancia un Thread, poi si rimette in attesa e attende una nuova richiesta dai Client.
 * @author Tommaso
 */

public class Server
{
    private int porta;                  //Porta del Server
    ServerSocket server = null;         //Variabile per avviare il server in una specifica Porta
    String stringRicevuta = null;       //Variabile per l'input Client Stringa
    String stringModificata = null;     //Variabile per modificare la stringa ricevuta dal Client
    BufferedReader inDalClient;         //Variabile input per leggere le Stringhe del Client
    DataOutputStream outVersoClient;    //Variabile output per inviare le Stringhe al Client
    GestioneThread gestioneThread;
    
    public Server(int porta) 
    {
        this.porta = porta;
    }
    
    public void Start()
    {
        try
        {
            server = new ServerSocket(porta);   //Il Server si avvia aprendo la porta

            System.out.println("Server partito");
            
            GestioneThread gestisciClient = new GestioneThread();
            //GestioneThreadInputOutput test = new GestioneThreadInputOutput();
            //test.start();

            for(;;) //For per instanziare un Thread ogni volta che si connette un client
            {
                System.out.println("Server in attesa...");
                Socket socket = server.accept();    //Il Server attende

                System.out.println("Server Socket: " + socket);
                ServerThread serverThread = new ServerThread(socket, server, gestisciClient); //Passaggio dei parametri
                
                gestisciClient.AggiungiClient(socket); //Aggiungi il client all'Array

                serverThread.start(); //Lancio il Thread che gestisce un Client
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
