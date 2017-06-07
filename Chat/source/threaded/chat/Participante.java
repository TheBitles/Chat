package threaded.chat;


public class Participante {

	private String name;
	private String IP;
	
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
		IP = ip;
	}

	public String getIP() {
		return IP;
	}

}
