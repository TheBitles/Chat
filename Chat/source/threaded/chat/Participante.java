package threaded.chat;

import java.net.InetAddress;
import java.net.Socket;

public class Participante {

	private String name;
	private String stringIP;
	private InetAddress IP;
	private Socket socket;
	
	/**
	 * @param socket the socket to set
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}

	public String getName(){
		return name;
	}

	public String toString() {
		return name;
	}



	public Participante(String n) {
		name = n;
	}
	




	public void setIP(String ip) {
		stringIP = ip;
	}

	public String getIP() {
		return stringIP;
	}

	public void setIP(InetAddress inetAddress) {
		IP = inetAddress;
		
	}

}
