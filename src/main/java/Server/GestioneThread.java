package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GestioneThread 
{
    ServerSocket server;
    boolean STOP = false;
    ArrayList<Socket> clientRunning = new ArrayList<Socket>();
    
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
    
    public boolean isEmpty()
    {
        return clientRunning.isEmpty();
    }
    
    public int getSize()
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
