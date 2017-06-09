package threaded.chat;

import java.io.IOException;
import java.net.ServerSocket;

public class GeneradorDeThreads extends Thread {

	ServerSocket server;
	@Override
	public void run() {
		try {
			server = new ServerSocket(1234);
			server.accept();
			
			
			
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
