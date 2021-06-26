package Frontend;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Backend.Item;
import Backend.Reciept;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Receipts extends JInternalFrame {

	private JPanel contentPane;
	private JTextField cusName;
	private JTextField clientName;
	private JScrollPane scrollPane, ItemPane;; 
	private JTextField totalField;

	/**
	 * Launch the application.
	 */
	public static void Emp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receipts frame = new Receipts();
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
	public Receipts() {
		setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		setBounds(150, 25, 772, 653);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Receipts ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 10, 142, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Bill To");
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 60, 116, 40);
		contentPane.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 91, 122, 40);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1_2 = new JLabel("Customer Name");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBackground(Color.BLACK);
		lblNewLabel_1_2.setBounds(10, 110, 116, 40);
		contentPane.add(lblNewLabel_1_2);
		
		cusName = new JTextField();
		cusName.setBounds(229, 117, 265, 32);
		contentPane.add(cusName);
		cusName.setColumns(10);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Client Company Name");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1.setBackground(Color.BLACK);
		lblNewLabel_1_2_1.setBounds(10, 160, 158, 40);
		contentPane.add(lblNewLabel_1_2_1);
		
		clientName = new JTextField();
		clientName.setColumns(10);
		clientName.setBounds(229, 167, 265, 32);
		contentPane.add(clientName);
		
		scrollPane = new JScrollPane();

		scrollPane.setBounds(37, 210, 658, 157);
		
		Reciept r = new Reciept();
		List<Reciept> recs = r.getReciepts();

		String[] col = {"Id", "Client Company", "Name"};
		String[][] recStr = new String[10][3];
		Integer i=0;
		for( Reciept rec: recs)
		{
			recStr[i][0] = Integer.toString(rec.id);
			recStr[i][1] = rec.client_company;
			recStr[i][2] = rec.client_name;
			i++;
		}

		JTable recTable = new JTable( recStr, col);
		recTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	

	        	
	        	Double Total = 0.00;
	            Reciept temp = r.getReciept(Integer.parseInt(recTable.getValueAt(recTable.getSelectedRow(), 0).toString()));
	            
	        	cusName.setText(temp.client_name);
	        	clientName.setText(temp.client_company);
	        	
	            List<Item> items = temp.items;


	    		String[] col = {"Name", "Quantity", "Price"};
	    		String[][] itemStr = new String[items.size()+1][3];
	    		Integer i=0;
	    		for( Item item: items)
	    		{
	    			itemStr[i][0] = item.name;
	    			itemStr[i][1] = Integer.toString(item.quantity);
	    			itemStr[i][2] = Double.toString(item.price);
	    			Total+=item.price * item.quantity;
	    			i++;
	    		}
	    		totalField.setText(Total.toString());
	    		JTable itemTable = new JTable( itemStr, col);
	    		ItemPane.setViewportView(itemTable);
	            //System.out.println(temp);
	        }
	    });
		scrollPane.setViewportView(recTable);
		
		contentPane.add(scrollPane);
		
		ItemPane = new JScrollPane();
		ItemPane.setBounds(41, 392, 654, 157);
		contentPane.add(ItemPane);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Total");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2_1_1.setBackground(Color.BLACK);
		lblNewLabel_1_2_1_1.setBounds(486, 559, 79, 40);
		contentPane.add(lblNewLabel_1_2_1_1);
		
		totalField = new JTextField();
		totalField.setColumns(10);
		totalField.setBounds(575, 559, 120, 32);
		contentPane.add(totalField);
		
		JButton addReceipt = new JButton("Add Receipt");
		addReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				AddReceipt frame = new AddReceipt();
				frame.setVisible(true);
			}
		});
		addReceipt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addReceipt.setBounds(546, 23, 149, 40);
		contentPane.add(addReceipt);
	}
}
