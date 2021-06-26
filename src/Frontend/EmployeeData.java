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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.JDatePicker;

import Backend.Company;
import Backend.Employee;


//import net.proteanit.sql.DbUtils;



public class EmployeeData extends JInternalFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private final JTextField SearchField = new JTextField();
	private JTable empTable;
	public Connection con;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtDepartment;
	private JTextField txtDesignation;
	private JTextField txtSalary;
	private JTextField txtAcNo;
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
	
	public static void Emp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeData frame = new EmployeeData();
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
	public EmployeeData() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 25, 772, 653);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SearchField.setBounds(22, 42, 211, 42);
		SearchField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search Employees");
		lblNewLabel.setBounds(22, 10, 300, 22);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(243, 42, 95, 42);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Name", "Company"}));
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filter = comboBox.getSelectedItem().toString();
				if( filter.equals("Company"))
				{
					Company c = new Company();
					List<Employee> emp = c.getEmployees();
//					for( Employee k: emp)
//					{
//						System.out.println(k);
//					}
					String[] col = {"First Name", "Last Name", "Department", "Designation"};
					String[][] empStr = new String[10][4];
					Integer i=0;
					for( Employee k:emp)
					{
						empStr[i][0] = k.first_name;
						empStr[i][1] = k.last_name;
						empStr[i][2] = k.department;
						empStr[i][3] = k.designation;
						i++;
					}
					JTable empTable = new JTable( empStr, col);
					scrollPane.setViewportView(empTable);

				}
				else
				{
					Company c = new Company();
					String search = SearchField.getText();
					List<Employee> emp = c.getEmployees(search);
//					for( Employee k: emp)
//					{
//						System.out.println(k);
//					}
					String[] col = {"First Name", "Last Name", "Department", "Designation"};
					String[][] empStr = new String[10][4];
					Integer i=0;
					for( Employee k:emp)
					{
						empStr[i][0] = k.first_name;
						empStr[i][1] = k.last_name;
						empStr[i][2] = k.department;
						empStr[i][3] = k.designation;
						i++;
					}
					JTable empTable = new JTable( empStr, col);
					scrollPane.setViewportView(empTable);
				}
				
			}
		});
		btnNewButton_1.setBounds(22, 94, 109, 33);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel);
		contentPane.add(SearchField);
		contentPane.add(comboBox);
		contentPane.add(btnNewButton_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 137, 713, 191);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("Add Employee");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(22, 349, 300, 33);
		contentPane.add(lblNewLabel_1);
		
		JDatePicker Dob = new JDatePicker();
		Dob.setBounds(358, 404, 202, 33);
		contentPane.add(Dob);
		
		JDatePicker Doj = new JDatePicker();
		Doj.setBounds(358, 460, 202, 33);
		contentPane.add(Doj);
		
		JButton btnAddEmp = new JButton("Add");
		btnAddEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fname = txtFirstName.getText();
				String lname = txtLastName.getText();
				String department = txtDepartment.getText();
				String designation = txtDesignation.getText();
				
				String dob = Dob.getFormattedTextField().getText().toString();
				dob = ChangeFormatDate(dob);
				String doj = Doj.getFormattedTextField().getText().toString();
				doj = ChangeFormatDate(doj);
				
//				System.out.println(dob+"::"+doj);
				Double sal = Double.parseDouble(txtSalary.getText());
				Integer acno = Integer.parseInt(txtAcNo.getText());
				
				Company c = new Company();
				c.addEmployee(fname, lname, designation, department, dob, doj, sal, acno);
				
			}
		});
		btnAddEmp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddEmp.setBounds(22, 527, 109, 33);
		contentPane.add(btnAddEmp);
		
		txtFirstName = new JTextField();
		txtFirstName.setText("First Name");
		txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtFirstName.setBounds(22, 404, 132, 33);
		contentPane.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setText("Last Name");
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtLastName.setColumns(10);
		txtLastName.setBounds(22, 460, 132, 33);
		contentPane.add(txtLastName);
		
		txtDepartment = new JTextField();
		txtDepartment.setText("Department");
		txtDepartment.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDepartment.setColumns(10);
		txtDepartment.setBounds(190, 404, 132, 33);
		contentPane.add(txtDepartment);
		
		txtDesignation = new JTextField();
		txtDesignation.setText("Designation");
		txtDesignation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDesignation.setColumns(10);
		txtDesignation.setBounds(190, 460, 132, 33);
		contentPane.add(txtDesignation);
		
		txtSalary = new JTextField();
		txtSalary.setText("Salary");
		txtSalary.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSalary.setColumns(10);
		txtSalary.setBounds(603, 404, 132, 33);
		contentPane.add(txtSalary);
		
		txtAcNo = new JTextField();
		txtAcNo.setText("A/C no");
		txtAcNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtAcNo.setColumns(10);
		txtAcNo.setBounds(603, 460, 132, 33);
		contentPane.add(txtAcNo);
		



	}
}
