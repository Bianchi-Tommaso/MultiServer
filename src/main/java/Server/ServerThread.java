package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread
{
    ServerSocket server = null;
    Socket client = null;
    String stringRicevuta = null;
    String stringModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public ServerThread(Socket socket, ServerSocket server)
    {
        this.client = socket;
        this.server = server;
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

            if(stringRicevuta == null || stringRicevuta.equals("STOP"))
            {
                outVersoClient.writeBytes(stringRicevuta + "Server In Chiusura..." + '\n');
                System.out.println("Server in Chiusura: " + stringRicevuta);
                break;
            }
            else
            {
                outVersoClient.writeBytes(stringRicevuta.toUpperCase() + " Ricevuta e ritrasmessa in MAIUSCOLO" + '\n');
                System.out.println("Stampa sul Server: " + stringRicevuta);
            }
        }

        outVersoClient.close();
        inDalClient.close();
        System.out.println("Chiusura Socket: " + client);
        client.close();
        server.close();
    }
}
