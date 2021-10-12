package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class GestioneThreadInputOutput extends Thread
{
    DataOutputStream outVersoClient;
    ArrayList<DataOutputStream> clientRunningStream = new ArrayList<DataOutputStream>();

    public GestioneThreadInputOutput(DataOutputStream outVersoClient)
    {
        this.outVersoClient = outVersoClient;
    }

    public GestioneThreadInputOutput()
    {

    }

    public void run()
    {

    }

    public void AggiungiClientStream(DataOutputStream x)
    {
        clientRunningStream.add(x);
    }
    
    public void RimuoviClientStream(DataOutputStream x)
    {
        clientRunningStream.remove(x);
    }

    public void AvvisaClient() throws IOException
    {
        for(int i = 0; i < clientRunningStream.size(); i++)
            clientRunningStream.get(i).writeBytes("ATTENZIONE CHIUSURA SERVER:STOP");
    }
}
