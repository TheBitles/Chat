package threaded.chat;

import java.net.InetAddress;
import java.net.Socket;

public class Participante {

	private String name;
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

	public String showYourself() {
		return "Participante [name=" + name + ", IP=" + IP + ", socket=" + socket + ", is closed:" + socket.isClosed() + 
				", isconnected: " + socket.isConnected() +  "]";
	}

	public Participante(String n) {
		name = n;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}

	public void setIP(InetAddress inetAddress) {
		IP = inetAddress;
	}

}
