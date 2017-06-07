package threaded.chat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Conversation extends JFrame {
	ArrayList<Participante> participantes;
	/**
	 * 
	 */
	private static final long serialVersionUID = 551908498875986417L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public Conversation(HashMap<String, String> participantes2) {
		setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 12));
		setTitle("Chat with " + participantes2.keySet().toString());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 434, 180);
		contentPane.add(scrollPane);
		
		JTextArea txtChatWindow = new JTextArea();
		txtChatWindow.setMargin(new Insets(3, 3, 3, 3));
		txtChatWindow.setEditable(false);
		scrollPane.setViewportView(txtChatWindow);
		txtChatWindow.setAutoscrolls(true);
		txtChatWindow.setWrapStyleWord(true);
		txtChatWindow.setLineWrap(true);
		txtChatWindow.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 13));
		txtChatWindow.setForeground(Color.YELLOW);
		txtChatWindow.setBackground(Color.BLACK);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 202, 347, 48);
		contentPane.add(scrollPane_1);
		
		
		JTextArea txtMessageToSend = new JTextArea();
		txtMessageToSend.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 13));
		txtMessageToSend.setWrapStyleWord(true);
		txtMessageToSend.setMargin(new Insets(3, 3, 3, 3));
		txtMessageToSend.setCaretColor(Color.WHITE);
		
		KeyListener keyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n'){
					if (txtMessageToSend.getText() != "") {
						txtChatWindow.append(txtMessageToSend.getText());
						txtMessageToSend.setText("");
					}		
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		scrollPane_1.setViewportView(txtMessageToSend);
		txtMessageToSend.addKeyListener(keyListener);
		txtMessageToSend.setBackground(Color.BLACK);
		txtMessageToSend.setForeground(Color.YELLOW);
		
		
		JButton btnSend = new JButton("Send");
		btnSend.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		btnSend.setContentAreaFilled(false);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtMessageToSend.getText() != "") {
					txtChatWindow.append(txtMessageToSend.getText() + '\n');
					txtMessageToSend.setText("");
					txtMessageToSend.grabFocus();
				}		
			}
		});
		btnSend.setBounds(367, 205, 77, 23);
		contentPane.add(btnSend);
		

	}

}
