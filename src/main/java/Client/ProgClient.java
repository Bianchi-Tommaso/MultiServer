package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Questa classe ha lo scopo di lanciare il client per connettersi al Server. Il main prende in Input
 * l'ip del server e la porta associata del Server.
 * @author Tommaso
 */

public class ProgClient 
{
    public static void main( String[] args ) throws IOException
    {
        String ipAddress = "";      //Variabile di input per l'indirizzo IP per connettersi al Server
        String porta = "";          //Variabile di input per la porta del Server
        
        System.out.println("Inserire Indirizzo IP");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ipAddress = br.readLine();  
        
        System.out.println("Inserire Porta Server");        
        porta = br.readLine();  

        Client client = new Client(ipAddress, Integer.valueOf(porta));
        
        client.Connetti();      //Client tenta di connettersi con i parametri presi in Input
        client.Comunica();      //Qui il client comincia a comunicare con il Server
    }
}
