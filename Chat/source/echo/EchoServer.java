package echo;

import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        
        
        int portNumber = 1234;
        
        try (
            ServerSocket serverSocket = new ServerSocket(1234);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            out.println("start writing");
            while ((inputLine = in.readLine()) != "\n") {
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}