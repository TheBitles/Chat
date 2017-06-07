package threaded.chat;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;

public class test extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7433396317624135783L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					
					
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
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList<String> listaRosa = new JList<String>();
		listaRosa.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -5743784921452808173L;
			String[] values = new String[] {"uno", "dos"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		listaRosa.setBounds(5, 5, 424, 106);
		contentPane.add(listaRosa);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(5, 233, 424, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JList<String> listaRosa = null;
			    DefaultListModel<String> listModel = new DefaultListModel<String>();
			    
		        String strLine;

		    	strLine = "hola";
		        listModel.addElement(strLine);
		        System.out.println(strLine);
		    	strLine = "chau";
		        listModel.addElement(strLine);
		        System.out.println(strLine);
		        
		        ListModel<String> listaActual = listaRosa.getModel();
		        for (int i = 0; i < listaActual.getSize(); i++){
		        	listModel.addElement(listaActual.getElementAt(i));
		        }
		        
		        
		        listaRosa.setModel(listModel);
				
			}
		});
		contentPane.add(btnNewButton);
	}

}
