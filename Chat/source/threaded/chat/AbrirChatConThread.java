package threaded.chat;

import java.util.HashMap;

public class AbrirChatConThread extends Thread {
	HashMap<String, String> participantes;

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		//MainWindow m = new MainWindow();
		//m.setVisible(true);
		
		Conversation v = new Conversation(participantes);
		v.setVisible(true);
		
	}


	public AbrirChatConThread(HashMap<String, String> participantesNuevoChat) {
		participantes = participantesNuevoChat;
	}


	public AbrirChatConThread(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}



}
