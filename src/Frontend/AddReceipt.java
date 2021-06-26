package Frontend;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Backend.Item;
import Backend.Reciept;
public class AddReceipt extends JFrame {
	private JTextField cliName;
	private JTextField compName;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddReceipt frame = new AddReceipt();
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
	private Object[] getRowAt(int row, DefaultTableModel model) 
	{
	    Object[] result = new Object[model.getColumnCount()+1];
	    
	    try {
		     for (int i = 0; i < model.getColumnCount(); i++) {
		         result[i] = model.getValueAt(row, i);
		     }


	    }catch(Exception gr) {
	    	
	    }
	     return result;
	}
	
	public AddReceipt() {
		setBounds(100, 100, 772, 653);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(135, 206, 250));
		
		JLabel lblNewLabel = new JLabel("Client Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(33, 65, 163, 29);
		getContentPane().add(lblNewLabel);
		
		JLabel lblClientCompany = new JLabel("Client Company");
		lblClientCompany.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblClientCompany.setBounds(33, 115, 163, 29);
		getContentPane().add(lblClientCompany);
		
		JLabel lblNewLabel_1 = new JLabel("Add Items");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(33, 154, 215, 39);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Add Receipt");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(33, 10, 215, 39);
		getContentPane().add(lblNewLabel_1_1);
		
		cliName = new JTextField();
		cliName.setBounds(206, 62, 170, 32);
		getContentPane().add(cliName);
		cliName.setColumns(10);
		
		compName = new JTextField();
		compName.setColumns(10);
		compName.setBounds(206, 115, 170, 32);
		getContentPane().add(compName);
		
		table = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
				},
				new String[] {
					"Name", "Quantity", "Price"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Integer.class, Double.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		table.setModel(tableModel);
		table.setBounds(33, 252, 686, 206);

		
		
		JButton btnNewButton = new JButton("Add Receipt");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				List<Item> items = new ArrayList<Item>();
				Integer size = table.getRowCount();
				
				Reciept r= new Reciept();
				r.client_company = compName.getText();
				r.client_name = cliName.getText();
				
				for(int i=0; i<size; i++)
				{
					Object[] row = getRowAt(i, tableModel);
					
					try {
						
						Integer q = (Integer) row[1];
						String n = (String) row[0];
						Double p = (Double) row[2];
						items.add( new Item( q, n, p ) );
						System.out.println(q+n+p);
					}catch( Exception ex){
						break;
					}

				
				}
				
				r.items = items;
				r.addReciept(r);
				dispose();
				
		}
		});
		

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(33, 494, 142, 39);
		getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 203, 686, 206);
		
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

	}
}
