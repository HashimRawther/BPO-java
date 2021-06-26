package Frontend;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
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

import Backend.Ecom;
import Backend.Item;
import Backend.Query;
import Backend.Reciept;

//import net.proteanit.sql.DbUtils;



public class QueryPage extends JInternalFrame {

	private JPanel contentPane;
	private JTable empTable;
	public Connection con;
	private JTable queryTable;
	private JTextField queryField;
	private JTextField replyField;
	private int queryId;

	/**
	 * Launch the application.
	 */
	public static void Emp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueryPage frame = new QueryPage();
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
	public QueryPage() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 25, 772, 653);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Queries");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(41, 10, 145, 43);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(41, 45, 137, 2);
		contentPane.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 73, 661, 178);
		contentPane.add(scrollPane);
		
		String[][] rows = new String[15][2];
		String[] cols = new String[] {"Name", "Query"};
		
		Ecom qr = new Ecom();
		List<Query> qrs = qr.getQueries();
		int i=0;
		for( Query q:qrs)
		{
			rows[i][0] = q.name;
			rows[i][1] = q.query;
			i++;
		}
		queryTable = new JTable(rows, cols);
		
		scrollPane.setViewportView(queryTable);
		
		queryTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	
	        	String query = queryTable.getValueAt(queryTable.getSelectedRow(), 1).toString();
	            queryId = qrs.get(queryTable.getSelectedRow()).id;
	        	queryField.setText(query);
	        	
	        }
	    });
		

		
		JLabel lblNewLabel_1 = new JLabel("Query Expansion");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(41, 272, 137, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel mailStatus = new JLabel("");
		mailStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mailStatus.setBounds(171, 568, 283, 32);
		contentPane.add(mailStatus);
		
		JButton btnNewButton = new JButton("Reply");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String reply = replyField.getText();
				
				qr.Reply(queryId, reply);
				mailStatus.setText("Reply Mail Sent!");

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(41, 568, 120, 32);
		contentPane.add(btnNewButton);
		
		queryField = new JTextField();
		queryField.setBounds(41, 314, 661, 75);
		contentPane.add(queryField);
		queryField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Reply to Query");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(40, 399, 138, 32);
		contentPane.add(lblNewLabel_2);
		
		replyField = new JTextField();
		replyField.setBounds(41, 448, 661, 96);
		contentPane.add(replyField);
		replyField.setColumns(10);


	}
}
