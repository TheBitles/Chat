package threaded.chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTextField;

public class Servidor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2368066290807734787L;
	private JPanel contentPane;
	private JTextField serverInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servidor frame = new Servidor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Servidor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList<String> clientsList = new JList<String>();
		clientsList.setBounds(5, 37, 151, 219);
		contentPane.add(clientsList);
		
		serverInfo = new JTextField();
		serverInfo.setBounds(5, 11, 86, 20);
		contentPane.add(serverInfo);
		serverInfo.setColumns(10);
		
		int puertoEscuchaServer = 1234;
		
		//acá necesito crear un thread generador de threads
		
		Thread t = new Thread();
		
		
	}
}
