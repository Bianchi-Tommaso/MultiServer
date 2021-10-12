package Client;

import java.io.BufferedReader;
import java.util.ArrayList;

public class GestioneThreadClient extends Thread
 {
    BufferedReader inDalServer;
    ArrayList<BufferedReader> clientRunningStream = new ArrayList<BufferedReader>();

    public GestioneThreadClient(BufferedReader inDalServer)
    {
        this.inDalServer = inDalServer;
    }

    public void run()
    {
        Controllo();
    }

    public void Controllo()
    {
        
    }
 }
