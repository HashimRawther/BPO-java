package Frontend;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import Backend.Employee;
import Backend.Transactions;
import org.jdatepicker.JDatePicker;

//import net.proteanit.sql.DbUtils;



public class TransactionData extends JInternalFrame {

	private JPanel contentPane;
	private JTable transTable;
	public Connection con;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	
	private String ChangeFormatDate(String StrDate)
	{
		Date date = new Date();  
		SimpleDateFormat oldFormat = new SimpleDateFormat("dd-MMM-yyyy"); 
		try {
			date = oldFormat.parse(StrDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(date);
	    SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    String strDate= newFormat.format(date);  
	    return strDate; 
	}
	
	private void renderTable( String[][] rows)
	{
		String[] cols = new String[] {"Date", "Target A/C", "Type", "Amount"};
		JTable transTable = new JTable( rows, cols);


		transTable.setBounds(41, 183, 661, 360);
		scrollPane.setViewportView(transTable);
	}
	
	public static void Emp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransactionData frame = new TransactionData();
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
	public TransactionData() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 25, 772, 653);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Transactions");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(41, 10, 145, 43);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(41, 45, 137, 2);
		contentPane.add(separator);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFrom.setBounds(41, 63, 50, 22);
		contentPane.add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTo.setBounds(346, 63, 50, 22);
		contentPane.add(lblTo);
		
		Transactions t = new Transactions();
		
		
		JDatePicker fromDate = new JDatePicker();
		fromDate.setBounds(99, 66, 202, 29);
		contentPane.add(fromDate);
		
		JDatePicker toDate = new JDatePicker();
		toDate.setBounds(406, 66, 202, 29);
		contentPane.add(toDate);
		
		JButton btnFilter = new JButton("Filter");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String from = fromDate.getFormattedTextField().getText().toString();
				from = ChangeFormatDate(from);
				String to = toDate.getFormattedTextField().getText().toString();
				to = ChangeFormatDate(to);
				
				String[][] transStr = new String[10][4];
				
				List<Transactions> filterTrans = t.getTransactions(from, to);
				
				Integer i=0;
				for( Transactions k:filterTrans)
				{
					transStr[i][0] = k.date.toString();
					transStr[i][1] = String.valueOf(k.target);
					transStr[i][2] = k.type;
					transStr[i][3] = String.valueOf(k.amount);
					i++;
				}
				
				renderTable( transStr);
				
				
			}
		});
		btnFilter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFilter.setBounds(618, 62, 85, 24);
		contentPane.add(btnFilter);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 105, 661, 381);
		contentPane.add(scrollPane);
		

		
		String[][] transStr = new String[10][4];
		

		List<Transactions> trans = t.getTransactions();
//		for( Employee k: emp)
//		{
//			System.out.println(k);
//		}

		Integer i=0;
		for( Transactions k:trans)
		{
			transStr[i][0] = k.date.toString();
			transStr[i][1] = String.valueOf(k.target);
			transStr[i][2] = k.type;
			transStr[i][3] = String.valueOf(k.amount);
			i++;
		}
		
		renderTable( transStr);
		
		JButton btnNewButton = new JButton("Add Transaction");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AddTransaction frame = new AddTransaction();
				frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(53, 553, 165, 30);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Add new Transaction");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(41, 496, 177, 30);
		contentPane.add(lblNewLabel_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(51, 536, 155, 2);
		contentPane.add(separator_1);


	}
}
