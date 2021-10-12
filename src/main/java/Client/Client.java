package Client;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client
{
    private String nomeServer;
    private int portaServer;
    Socket mioSocket;
    BufferedReader tastiera;
    String stringUtente;
    String stringRicevutaDalServer;
    DataOutput outVersoServer;
    BufferedReader inDalServer;

    public Client(String nomeServer, int portaServer) 
    {
        this.nomeServer = nomeServer;
        this.portaServer = portaServer;
    }

    public Socket Connetti()
    {
        System.out.println("2 CLIENT partito in esecuzione...");

        try
        {
            tastiera = new BufferedReader(new InputStreamReader(System.in));

            mioSocket = new Socket(nomeServer, portaServer);

            outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
        }
        catch(UnknownHostException e)
        {
            System.out.print("Host Sconosciuto");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore Durante La Connessione");
            System.exit(1);
        }

        return mioSocket;
    }

    public void Comunica()
    {
        try
        {
            for(;;)
            {
                System.out.println("Inserisci La Stringa Da Trasmettere Al Server " + '\n');
                stringUtente = tastiera.readLine();

                System.out.println("5... Invio La Stringa Al Server E Attendo");
                outVersoServer.writeBytes(stringUtente + '\n');            

                System.out.println("8 Ho La Risposta Dal Server... ");
                stringRicevutaDalServer = inDalServer.readLine();
                System.out.println(stringRicevutaDalServer);

                if(stringUtente == null || stringUtente.equals("FINE") || stringUtente.equals("STOP") || stringRicevutaDalServer.contains("STOP"))
                {
                    System.out.println("CLIENT: Termina Elaborazione E Chiude Connessione");
                    inDalServer.close();
                    mioSocket.close();
                    break;
                }
            }

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore Durante La Connessione");
            System.exit(1);
        }
    }

}