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
import java.util.ArrayList;

import javax.swing.DefaultListModel;
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
	 * @throws IOException 
	 */
	public Servidor() throws IOException {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		DefaultListModel<Participante> lista = new DefaultListModel<Participante>();
		JList<Participante> clientsList = new JList<Participante>(lista);
		clientsList.setBounds(5, 37, 151, 219);
		contentPane.add(clientsList);
		
		serverInfo = new JTextField();
		serverInfo.setBounds(5, 11, 86, 20);
		contentPane.add(serverInfo);
		serverInfo.setColumns(10);
		
		ArrayList<Socket> clientes = new ArrayList<Socket>(5);
		int puertoEscuchaServer = 1234;
		
		System.out.println("SERVER: server on, listening on " + puertoEscuchaServer);
		ServerSocket server = new ServerSocket(puertoEscuchaServer);
		System.out.println("SERVER: awaiting connection on " + server.getLocalPort());
		
		Thread desconexionCliente = new Thread(){
			@Override
			public void run(){
				while (true){
					for(int i = 0; i < lista.getSize(); i++){
						if (!lista.get(i).getSocket().isConnected()){
							lista.remove(i);
						}
					}
				}
			}
		};
		Thread aceptarNuevoCliente = new Thread(){			
			@Override
			public void run(){
				try {
					while (true){
						
						System.out.println("esperando");
						Socket nuevoCliente = server.accept();
						
						clientes.add( nuevoCliente );
						Participante nuevo = new Participante("nombre");
						nuevo.setSocket(nuevoCliente);
						nuevo.setIP(nuevoCliente.getInetAddress());
						lista.addElement(nuevo);
						
						System.out.println("aceptado!");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		aceptarNuevoCliente.start();
		desconexionCliente.start();
		
	}
}
