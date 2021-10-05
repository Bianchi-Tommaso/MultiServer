package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgServer 
{
    public static void main(String args[]) throws IOException
    {
        
        String porta = "";      //Variabile di input per la porta del Server
        System.out.println("Inserire Porta Server");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        porta = br.readLine(); 
        
        Server server = new Server(Integer.valueOf(porta));

        server.Start();
    }
}
