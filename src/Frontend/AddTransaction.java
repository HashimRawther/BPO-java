package Frontend;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.JDatePicker;

import Backend.Transactions;

public class AddTransaction extends JFrame {

	private JPanel contentPane;
	private JTextField transTarget;
	private JTextField transAmt;
	private JTextField transPurpose;

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
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTransaction frame = new AddTransaction();
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
	public AddTransaction() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 25, 956, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Target A/C");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(116, 112, 163, 24);
		contentPane.add(lblNewLabel);
		
		transTarget = new JTextField();
		transTarget.setBounds(348, 112, 210, 27);
		contentPane.add(transTarget);
		transTarget.setColumns(10);
		
		JLabel lblAddTransactions = new JLabel("Add Transactions");
		lblAddTransactions.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddTransactions.setBounds(48, 29, 163, 24);
		contentPane.add(lblAddTransactions);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAmount.setBounds(116, 178, 163, 24);
		contentPane.add(lblAmount);
		
		transAmt = new JTextField();
		transAmt.setColumns(10);
		transAmt.setBounds(348, 178, 210, 27);
		contentPane.add(transAmt);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(48, 63, 187, 24);
		contentPane.add(separator);
		
		JLabel lblTypeOfTransaction = new JLabel("Type of Transaction");
		lblTypeOfTransaction.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTypeOfTransaction.setBounds(116, 319, 163, 24);
		contentPane.add(lblTypeOfTransaction);
		
		JComboBox transType = new JComboBox();
		transType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		transType.setModel(new DefaultComboBoxModel(new String[] {"Credit", "Debit"}));
		transType.setBounds(351, 319, 86, 25);
		contentPane.add(transType);
		
		JLabel lblPurpose = new JLabel("Purpose");
		lblPurpose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPurpose.setBounds(116, 383, 163, 24);
		contentPane.add(lblPurpose);
		
		transPurpose = new JTextField();
		transPurpose.setColumns(10);
		transPurpose.setBounds(348, 383, 488, 122);
		contentPane.add(transPurpose);
		
		JLabel transStatus = new JLabel("");
		transStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		transStatus.setBounds(348, 581, 377, 27);
		contentPane.add(transStatus);
		
		JDatePicker transDate = new JDatePicker();
		transDate.setBounds(348, 253, 210, 35);
		contentPane.add(transDate);
		
		JButton btnNewButton = new JButton("Add Transaction");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Transactions t = new Transactions();
				int target = Integer.parseInt(transTarget.getText());
				Double amt = Double.parseDouble(transAmt.getText());
				String date = transDate.getFormattedTextField().getText().toString();
				date = ChangeFormatDate(date);
				String purpose = transPurpose.getText();
				String type = transType.getSelectedItem().toString();
				Integer status = t.addTransactions(target, type , amt, purpose, date);
				if(status == 1)
				{
					transStatus.setText("Transaction Added");
				}
				else
				{
					transStatus.setText("Transaction upload Failed!");
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(348, 536, 187, 35);
		contentPane.add(btnNewButton);

		
		JLabel lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(116, 253, 129, 24);
		contentPane.add(lblNewLabel_1);
		

	}
}
