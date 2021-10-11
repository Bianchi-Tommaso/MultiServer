package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Questa classe ha il compito di contenere i Client che si connettono al Server, per poter soddisfare 
 * le richieste prima di Spegnere completamente il Server.
 * @author Tommaso
 */

public class GestioneThread 
{
    ServerSocket server;    
    boolean STOP = false;
    ArrayList<Socket> clientRunning = new ArrayList<Socket>();  //Contenitore Dei Client connessi
    
    public GestioneThread(ServerSocket server)
    {
        this.server = server;
    }

    public void AggiungiClient(Socket x)
    {
        clientRunning.add(x);
    }
    
    public void RimuoviClient(Socket x)
    {
        clientRunning.remove(x);
    }
    
    public boolean isEmpty()    //Controlla se l'array è vuoto
    {
        return clientRunning.isEmpty();
    }
    
    public int getSize()        //Ritorna la grandezza
    {
        return clientRunning.size();
    }
    
    public ArrayList<Socket> getSocket()
    {
        return clientRunning;
    }
    
    public void Stop() throws IOException
    {
        this.STOP = true;
    }
    
    public boolean getStop()
    {
        return STOP;
    }
}
