package threaded.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends Thread {
	Thread t;
	int puerto;
	public ServerListener(int port){
		puerto = port;
	}
	@Override
	public void run() {
		int puertoEscuchaServer = puerto;
		 try (
	                ServerSocket serverSocket = new ServerSocket(puertoEscuchaServer);
	                Socket clientSocket = serverSocket.accept();
	                PrintWriter out2 =
	                    new PrintWriter(clientSocket.getOutputStream(), true);                   
	                BufferedReader in = new BufferedReader(
	                    new InputStreamReader(clientSocket.getInputStream()));
	        		DataOutputStream out = 
	        			new DataOutputStream(clientSocket.getOutputStream());
	            ) {
	        	
	        	while (in.readLine() != "\n") {
	        		out.writeUTF("this is my response");	        		
	        	}
	        }catch (IOException e) {
	            System.out.println("Exception caught when trying to listen on port "
	                    + puertoEscuchaServer + " or listening for a connection");
	                System.out.println(e.getMessage());
	            }
		
	}
	
	
}
