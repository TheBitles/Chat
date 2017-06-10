package threaded.chat;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Servidor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2368066290807734787L;
	private JPanel contentPane;
	private JTextField serverInfo;
	private JTextField txtMensaje;
	private JButton btnSend;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servidor frame = new Servidor();
					System.out.println("SERVER - constructor");
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
		
		JButton btnKick = new JButton("kick");
		btnKick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Participante echar = clientsList.getSelectedValue();
				try {
					System.out.println("echando a " + echar.getName());
					PrintWriter out = new PrintWriter(echar.getSocket().getOutputStream(), true);
					
					out.print("Z");
					echar.getSocket().close();
					lista.removeElement(echar);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnKick.setBounds(166, 34, 89, 23);
		contentPane.add(btnKick);
		
		txtMensaje = new JTextField();
		txtMensaje.setBounds(166, 115, 258, 20);
		contentPane.add(txtMensaje);
		txtMensaje.setColumns(10);
		
		btnSend = new JButton("send");
		btnSend.setEnabled(false);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Participante destinatario = clientsList.getSelectedValue();
				try {
					DataOutputStream output = new DataOutputStream(destinatario.getSocket().getOutputStream());
					System.out.println("SERVER: sending message to " + destinatario.getName() + ": " + txtMensaje.getText());
					output.writeChars(txtMensaje.getText() );
				} catch (IOException e) {
					System.err.println(e.getMessage());					
					e.printStackTrace();
				}
			}
		});
		btnSend.setBounds(166, 146, 89, 23);
		contentPane.add(btnSend);
		
		clientsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (clientsList.getModel().getSize() < 1)
					btnSend.setEnabled(false);
				else
					btnSend.setEnabled(true);
				if (lista.size() < 1 )
					btnSend.setEnabled(false);
				else
					btnSend.setEnabled(true);
			}
		});

		
		///ArrayList<Socket> clientes = new ArrayList<Socket>(5);
		int puertoEscuchaServer = 1234;
		
		System.out.println("SERVER: server on, listening on " + puertoEscuchaServer);
		ServerSocket server = new ServerSocket(puertoEscuchaServer);
		System.out.println("SERVER: awaiting connection on " + server.getLocalPort());
		
		Thread desconexionCliente = new Thread(){
			@Override
			public void run(){
				System.out.println("SERVER: thread desconexionCliente start");
				try {
					sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				while (true){
					for(int i = 0; i < lista.getSize(); i++){
						try {
							sleep(500);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						try{
							lista.get(i).getSocket().getOutputStream().write((byte) 162);
						}
						catch(IOException e){
							System.err.println("cliente " + i + ": " + lista.get(i).getName() + " desconectado");							
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
						System.out.println("nuevo cliente aceptado!");
						nuevoCliente.setKeepAlive(true);
						
						//clientes.add( nuevoCliente );
						DataInputStream input = new DataInputStream( nuevoCliente.getInputStream());
						String clientName = input.readUTF();
						System.out.println("client name: " + clientName);
						Participante nuevo = new Participante(clientName);
						nuevo.setSocket(nuevoCliente);
						nuevo.setIP(nuevoCliente.getInetAddress());
						
						lista.addElement(nuevo);
						
						
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
