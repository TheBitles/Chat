package threaded.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

public class VentanaPrincipal extends JFrame {
	//ArrayList<Participante> arlistparticipantes = new ArrayList<Participante>(5);
	HashMap<String, String> participantes = new HashMap<String, String>(5);
	
	
	DefaultListModel<String> listModel = new DefaultListModel<String>();
	//https://coderanch.com/t/345063/java/JList-add-simple-element-existing
	
	/**
	 * Initialize the default model and keep a reference to it.
class MyFrame {
  DefaultListModel listModel = new DefaultListModel();
  JList list = new JList(listModel);
  
  public void actionPerformed(ActionEvent evt) {
    listModel.addElement("new");
  }
}
	 */
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2336317283266036476L;
	private JPanel contentPane;




	/**
	 * Create the frame.
	 * @param myIP 
	 * @param titulo 
	 */
	public VentanaPrincipal(String user, String IP) {
		
		

		
        String serverName = "localhost";
        int portNumber = 1234;
        
        

		
		setTitle(user + "@" + IP); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBounds(100, 100, 429, 386); contentPane = new JPanel(); 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane); contentPane.setLayout(null);
		

		
		JList<String> listConectados = new JList<String>(listModel);
		listConectados.setBounds(10, 11, 201, 239);
		
		
		contentPane.add(listConectados);
		
		JButton btnBroadcast = new JButton("Broadcast");
		btnBroadcast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBroadcast.setBounds(249, 227, 89, 23);
		contentPane.add(btnBroadcast);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(221, 206, 117, 10);
		contentPane.add(horizontalStrut);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(10, 261, 328, 1);
		contentPane.add(horizontalStrut_1);
		
		JButton btnAgregarParticipante = new JButton("agregar participante");
		btnAgregarParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				participantes.put("John", "192.168.100.105");
				participantes.put("Paul", "192.168.100.106");
				participantes.put("Ringo", "192.168.100.107");
				listModel.addElement("John @ 192.168.100.105");
				listModel.addElement("Paul @ 192.168.100.106");
				listModel.addElement("Ringo @ 192.168.100.107");				
			}
		});
		btnAgregarParticipante.setBounds(23, 294, 131, 23);
		contentPane.add(btnAgregarParticipante);
		
		JTextArea txtrServerMessage = new JTextArea();
		txtrServerMessage.setLineWrap(true);
		txtrServerMessage.setText("server message");
		txtrServerMessage.setBounds(221, 45, 182, 128);
		contentPane.add(txtrServerMessage);
		
		ActionListener nuevoChat = new ActionListener() {		

			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> seleccionados = listConectados.getSelectedValuesList();
				nuevoChat(seleccionados);
				//AbrirChatConThread threadChat = new AbrirChatConThread(seleccionados);
				//threadChat.start();			
			}
		};
		
		JButton btnLlamarASegunda = new JButton("Iniciar Chat");
		btnLlamarASegunda.addActionListener(nuevoChat);
		btnLlamarASegunda.setBounds(221, 11, 89, 23);
		contentPane.add(btnLlamarASegunda);
		
		
		
		
		Thread conexion = new Thread(){
        	@Override
        	public void run() {
                try (
                    Socket serverSocket = new Socket(serverName, portNumber);
            		PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader( new InputStreamReader(serverSocket.getInputStream()));
                    BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in));
                    DataInputStream fromServer = new DataInputStream(serverSocket.getInputStream());
                	DataOutputStream toServer = new DataOutputStream(serverSocket.getOutputStream());
                ) 
                {
                	
                	//out.println(user);
                	toServer.writeUTF(user);
                	String mensaje = fromServer.readLine();// = fromServer.readUTF();
                	System.out.println("mensaje: " + mensaje);
                	while (mensaje != "kick"){
                		System.out.println("mensaje: " + mensaje );
                		sleep(777);
                		txtrServerMessage.setText( txtrServerMessage.getText() + mensaje );
                		mensaje = fromServer.readLine();
                		
                	}
                	System.err.println("OUT OF THE WHILE!!");
                	out.flush();
                	/*
                    String userInput;
                    while ((userInput = stdIn.readLine()) != "") {
                        out.println(userInput);
                        System.out.println("server says: " + in.readLine());
                    }
                    */
                    
                } catch (UnknownHostException e) {
                    System.err.println("Server " + serverName + " is unkown");
                    System.exit(1);
                } catch (IOException e) {
                    System.err.println("Couldn't get I/O for the connection to " +
                        serverName);
                    System.exit(1);
                } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
            	
            		
        		
        	}
        };
		
		conexion.start();
		
		
	}


	protected void nuevoChat(List<String> seleccionados) {
		HashMap<String, String> participantesNuevoChat = new HashMap<String, String>(seleccionados.size());
		for (String seleccionado : seleccionados){
			participantesNuevoChat.put( seleccionado, participantes.get(seleccionado));
		}
		
		
		new AbrirChatConThread(participantesNuevoChat).start();
	}
}
