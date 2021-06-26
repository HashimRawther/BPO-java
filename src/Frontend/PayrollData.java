package Frontend;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.util.List;

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

import Backend.Employee;
import Backend.Item;
import Backend.Reciept;
import Backend.Transactions;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//import net.proteanit.sql.DbUtils;



public class PayrollData extends JInternalFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable payTable;
	public Connection con;
	private JTextField curBal;
	private JTextField totSal;
	private JTable table;
	private List<Employee> emp;
	
	Transactions t = new Transactions();

	/**
	 * Launch the application.
	 */
	public static void Emp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollData frame = new PayrollData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void renderTable()
	{
		String[][] rows = new String[15][5];
		String[] cols = new String[] {"First Name", "Last Name", "Designation", "Salary", "Distributed"};

		emp = t.getSalaryList();
		
		int i=0;
		for( Employee e:emp)
		{
			rows[i][0] = e.first_name;
			rows[i][1] = e.last_name;
			rows[i][2] = e.designation;
			rows[i][3] = String.valueOf(e.salary);
			rows[i][4] = String.valueOf(e.sal_given);
			i++;
		}
		
		JTable payTable = new JTable( rows, cols);
		scrollPane.setViewportView(payTable);
	}

	/**
	 * Create the frame.
	 */
	public PayrollData() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 25, 772, 653);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Payroll");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(41, 32, 239, 43);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(41, 73, 115, 15);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Current Account Balance");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(52, 112, 258, 28);
		contentPane.add(lblNewLabel_1);
		
		curBal = new JTextField();
		curBal.setBounds(353, 110, 208, 28);
		contentPane.add(curBal);
		curBal.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Total Salary to be distributed");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(52, 168, 258, 30);
		contentPane.add(lblNewLabel_1_1);
		
		totSal = new JTextField();
		totSal.setColumns(10);
		totSal.setBounds(353, 168, 208, 30);
		contentPane.add(totSal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 248, 618, 258);
		contentPane.add(scrollPane);
		

		String[][] rows = new String[15][5];
		String[] cols = new String[] {"First Name", "Last Name", "Designation", "Salary", "Distributed"};

		emp = t.getSalaryList();
		
		int i=0;
		for( Employee e:emp)
		{
			rows[i][0] = e.first_name;
			rows[i][1] = e.last_name;
			rows[i][2] = e.designation;
			rows[i][3] = String.valueOf(e.salary);
			rows[i][4] = String.valueOf(e.sal_given);
			i++;
		}
		
		JTable payTable = new JTable( rows, cols);
		scrollPane.setViewportView(payTable);
		
		curBal.setText(String.valueOf(t.curBalance()));
		totSal.setText(String.valueOf(t.totSalary()));
		
		JButton btnNewButton = new JButton("Distribute Salary");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				
//				System.out.println("In btn");
//				System.out.println("In table ");
	        	int[] rowIndex = payTable.getSelectedRows();
	        	int[] ids = new int[rowIndex.length];
	        	int i=0;
	        	for(int r:rowIndex)
	        	{
	        		ids[i++]= emp.get(r).id;
	        	}
	        	t.distributeSalaries(ids);

	    		String[][] rows = new String[15][5];
	    		String[] cols = new String[] {"First Name", "Last Name", "Designation", "Salary", "Distributed"};

	    		emp = t.getSalaryList();
	    		
	    		i=0;
	    		for( Employee ek:emp)
	    		{
	    			rows[i][0] = ek.first_name;
	    			rows[i][1] = ek.last_name;
	    			rows[i][2] = ek.designation;
	    			rows[i][3] = String.valueOf(ek.salary);
	    			rows[i][4] = String.valueOf(ek.sal_given);
	    			i++;
	    		}
	    		
	    		JTable payTable = new JTable( rows, cols);
	    		scrollPane.setViewportView(payTable);
	        	
	    		curBal.setText(String.valueOf(t.curBalance()));
	    		totSal.setText(String.valueOf(t.totSalary()));
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(50, 516, 230, 28);
		contentPane.add(btnNewButton);

	}
}
