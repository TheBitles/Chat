package sinGUI;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
	ArrayList<Socket> clientes = new ArrayList<Socket>(5);
	public static void main(String [] args) throws IOException {
		Server server = new Server();
		
	}
	@Override
	public void run() {
		//Socket nuevoCliente= server.accept();
	}
	public Server() throws IOException {
		
		int puertoEscuchaServer = 1234;
		System.out.println("SERVER: server on, listening on " + puertoEscuchaServer);
		ServerSocket server = new ServerSocket(puertoEscuchaServer);
		Socket nuevoCliente;
		System.out.println("SERVER: awaiting connection on " + server.getLocalPort());
		while (true) {
			nuevoCliente = server.accept();
			clientes.add( nuevoCliente );
			System.out.println("se conectó " + nuevoCliente.getLocalSocketAddress() + " a través de " + nuevoCliente.getPort());			
			}
		
		//server.close();
		
		/*
		try (
			 ServerSocket serverSocket = new ServerSocket(puertoEscuchaServer);
			 Socket clientSocket = serverSocket.accept();
			 PrintWriter out2 = new PrintWriter(clientSocket.getOutputStream(), true);                   
			 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			 DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
	        ) 
		{
			System.out.println(in.readLine());	        		
		}
		catch (IOException e) 
		{
	         System.out.println("Exception caught when trying to listen on port "
	                 + puertoEscuchaServer + " or listening for a connection");
	         System.out.println(e.getMessage());
		}
		*/
				
	}
}
