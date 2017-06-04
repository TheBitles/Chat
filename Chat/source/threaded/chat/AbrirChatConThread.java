package threaded.chat;


public class AbrirChatConThread extends Thread {
	Thread t;
	

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		//MainWindow m = new MainWindow();
		//m.setVisible(true);
		
		Conversation v = new Conversation();
		v.setVisible(true);
		
	}


	public AbrirChatConThread() {
		// TODO Auto-generated constructor stub
	}


	public AbrirChatConThread(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}



}
