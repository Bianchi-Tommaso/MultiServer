package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread
{
    ServerSocket server = null;
    Socket client = null;
    String stringRicevuta = null;
    String stringModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    GestioneThread gestioneThread;

    public ServerThread(Socket socket, ServerSocket server, GestioneThread gestioneThread)
    {
        this.client = socket;
        this.server = server;
        this.gestioneThread = gestioneThread;
    }

    public void run()
    {
        try
        {
            Comunica();
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    public void Comunica() throws IOException
    {
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());

        for(;;)
        {
            stringRicevuta = inDalClient.readLine();
            
            Controllo(gestioneThread);

            if(stringRicevuta == null || stringRicevuta.equals("STOP"))
            {
                outVersoClient.writeBytes(stringRicevuta + "Server In Chiusura..." + '\n');
                System.out.println("Server in Chiusura: " + stringRicevuta);
                gestioneThread.Stop();
                break;
            }
            else if(stringRicevuta.equals("FINE"))
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
    
     public void Controllo(GestioneThread gestisci) throws IOException
    {            
                if(gestisci.getStop())
                {
                  outVersoClient.writeBytes(stringRicevuta.toUpperCase() + " Ricevuta e ritrasmessa in MAIUSCOLO" + "STOP" + '\n');
                  ChiudiConnessione(gestioneThread);
                  
                  if(gestisci.getSize() == 1)
                  {
                      server.close();
                  }   
                }                      
    }
    
    public void ChiudiConnessione(GestioneThread gestisci) throws IOException
    {
     System.out.println("Chiusura Connessione Con: " + client);   
     gestisci.RimuoviClient(client);
     outVersoClient.close();
     inDalClient.close();
     client.close();
    }
    
    public void StampaAlClient() throws IOException
    {
        outVersoClient.writeBytes(stringRicevuta.toUpperCase() + " Ricevuta e ritrasmessa in MAIUSCOLO" + '\n');
         System.out.println("Stampa sul Server Stringa Ricevuta: " + stringRicevuta);
    }
}
