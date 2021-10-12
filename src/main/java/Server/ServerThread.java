package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Questa classe rappresenta il Thread che verrà lanciato dalla Classe Server. ServerThread gestisce
 * il flusso di stringhe che il Server e il Client si scambiano tra di loro.
 * @author Tommaso
 */

public class ServerThread extends Thread
{
    ServerSocket server = null;     //Variabile per avviare il server in una specifica Porta
    Socket client = null;           //Variabile per la connessione col Client
    String stringRicevuta = null;   //Variabile per l'input Client Stringa
    String stringModificata = null; //Variabile per modificare la stringa ricevuta dal Client
    BufferedReader inDalClient;     //Variabile input per leggere le Stringhe del Client
    DataOutputStream outVersoClient;//Variabile output per inviare le Stringhe al Client
    GestioneThread gestioneThread;  //Gestione dei Client
    GestioneThreadInputOutput test;

    public ServerThread(Socket socket, ServerSocket server, GestioneThread gestioneThread) //Costruttore 
    {
        this.client = socket;
        this.server = server;
        this.gestioneThread = gestioneThread;
    }

    /**
     *Metodo run del Thread che viene eseguito non appena viene lanciato col start() 
     */
    
    public void run()
    {
        try
        {
            Comunica(); //Chiamata del metodo
        }
        catch(Exception e)  
        {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Questo metodo permette la comunicazione dal Server al Client; Il metodo funziona con un for infinito
     * dove il Thread che gestisce il Client, sta in ascolto di una stringa. Dopo aver ricevuto una stringa
     * il Thread esegue un controllo dove verifica se la Stringa ricevuta è un comando per la disconnessione
     * del Client o per la Chiusura del Server, altrimenti il Thread trasforma la stringa ricevuta dal Client,
     * da minuscolo a maiuscolo.
     * @throws IOException 
     */
    
    public void Comunica() throws IOException
    {
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());

        //test.AggiungiClientStream(outVersoClient);

        for(;;)
        {
            stringRicevuta = inDalClient.readLine(); //Attesa di leggere una Stringa dal Client.
            
            //Controllo(gestioneThread);

            if(stringRicevuta == null || stringRicevuta.equals("STOP")) //Controllo Chiusura Server
            {
                outVersoClient.writeBytes(stringRicevuta + "Server In Chiusura..." + '\n');
                System.out.println("Server in Chiusura: " + stringRicevuta);
                gestioneThread.StopClient();
                server.close();
                break;
            }
            else if(stringRicevuta.equals("FINE"))  //Controllo Chiusura Connessione Client
            {
                System.out.println("Chiusura Socket: " + client);   
                outVersoClient.writeBytes(stringRicevuta + "Chiusura Connessione: " + client + '\n');    
                ChiudiConnessione(gestioneThread);
                break;
            }
            else
            {
                StampaAlClient();
            }
        }
    }
    
    /**
     * Controllo dei Client connessi al Server, ovvero finché il server ha dei thread connessi ai Client,
     * non si spegne.
     * @param gestisci
     * @throws IOException 
     */
     public void Controllo(GestioneThread gestisci) throws IOException
    {            /*
                if()  //Controllo se un Client ha scritto STOP per chiudere il Server
                {
                  outVersoClient.writeBytes(stringRicevuta.toUpperCase() + " Ricevuta e ritrasmessa in MAIUSCOLO " + " STOP" + '\n');
                  ChiudiConnessione(gestioneThread);
                  
                  if(gestisci.getSize() == 1)
                  {
                      server.close();   //Chiusrura Della Porta(Chiusura SocketServer)
                  }   
                }     
                */                 
    }
     
     /**
      * Chiusura Della connessione del Client con il Server.
      * @param gestisci
      * @throws IOException 
      */
    
    public void ChiudiConnessione(GestioneThread gestisci) throws IOException
    {
     System.out.println("Chiusura Connessione Con: " + client);   //Stampa con quale Client chiuderà la Connessione
     gestisci.RimuoviClient(client);
     outVersoClient.close();
     inDalClient.close();
     client.close();
    }
    
    /**
     * Stampa della Stringa in Maiuscolo e inviata in Input al Client
     * @throws IOException 
     */
    public void StampaAlClient() throws IOException
    {
        outVersoClient.writeBytes(stringRicevuta.toUpperCase() + " Ricevuta e ritrasmessa in MAIUSCOLO" + '\n');
         System.out.println("Stampa sul Server Stringa Ricevuta: " + stringRicevuta);
    }
}
