package Frontend;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Services extends JFrame {

	private JPanel contentPane;
	private JPanel InternalPanel;;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Services frame = new Services();
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
	public Services() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 25, 956, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 936, 713);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();	
		tabbedPane.addTab("Data Entry ", null, panel, null);
		panel.setLayout(null);
		
		JButton employeeBtn = new JButton("Employee");
		employeeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				InternalPanel.removeAll();
				EmployeeData intFrame = new EmployeeData();
				InternalPanel.add(intFrame);
				
				intFrame.setVisible(true);
			
						
			}
			
		});
		employeeBtn.setBounds(10, 23, 109, 38);
		panel.add(employeeBtn);
		
		JButton receiptsBtn = new JButton("Receipts");
		receiptsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InternalPanel.removeAll();
				Receipts intFrame = new Receipts();
				InternalPanel.add(intFrame);
				//InternalPanel.nextFocus();
				intFrame.setVisible(true);
			}
		});
		receiptsBtn.setBounds(10, 89, 109, 38);
		panel.add(receiptsBtn);
		
		JButton documentsBtn = new JButton("Documents");
		documentsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InternalPanel.removeAll();
				Addfiles t = new Addfiles();
				InternalPanel.add(t);
				t.setVisible(true);
			}
		});
		documentsBtn.setBounds(10, 161, 109, 38);
		panel.add(documentsBtn);
		
		InternalPanel = new JPanel();
		InternalPanel.setBounds(149, 23, 772, 653);
		panel.add(InternalPanel);
		InternalPanel.setLayout(new CardLayout(0, 0));
		
//		String check = "Data management is an administrative process \n"
//				+ " that includes acquiring, validating, storing, protecting, \n"
//				+ "and processing required data to ensure the accessibility, reliability, "
//				+ "and timeliness of the data for its users. Organizations and enterprises "
//				+ "are making use of Big Data more than ever before to inform business decisions "
//				+ "and gain deep insights into customer behavior, trends, and opportunities "
//				+ "for creating extraordinary customer experiences.";
//		JTextField dataEntry =new JTextField();
//		dataEntry.setText(check);
//		dataEntry.setAlignmentY(TOP_ALIGNMENT);
//		dataEntry.setAlignmentX(LEFT_ALIGNMENT);
//		InternalPanel.add(dataEntry);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Accounts ", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel AccPanel = new JPanel();
		AccPanel.setBounds(149, 23, 772, 653);
		panel_1.add(AccPanel);
		AccPanel.setLayout(new CardLayout(0, 0));
		
		JButton btnTransaction = new JButton("Transaction");
		btnTransaction.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				AccPanel.removeAll();
				TransactionData intFrame = new TransactionData();
				AccPanel.add(intFrame);
				
				intFrame.setVisible(true);
			}
		});
		btnTransaction.setBounds(10, 23, 109, 38);
		panel_1.add(btnTransaction);
		
		JButton btnPayroll = new JButton("Payroll");
		btnPayroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccPanel.removeAll();
				PayrollData intFrame = new PayrollData();
				AccPanel.add(intFrame);
				
				intFrame.setVisible(true);
			}
		});
		btnPayroll.setBounds(10, 161, 109, 38);
		panel_1.add(btnPayroll);

		
		JButton btnBalanceSheet = new JButton("Report");
		btnBalanceSheet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccPanel.removeAll();
				FinReport intFrame = new FinReport();
				AccPanel.add(intFrame);
				
				intFrame.setVisible(true);
			}
		});
		btnBalanceSheet.setBounds(10, 89, 109, 38);
		panel_1.add(btnBalanceSheet);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Ecommunication ", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel EcomPanel = new JPanel();
		EcomPanel.setBounds(149, 23, 772, 653);
		panel_2.add(EcomPanel);
		EcomPanel.setLayout(new CardLayout(0, 0));
		
		JButton btnQueries = new JButton("Queries");
		btnQueries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EcomPanel.removeAll();
				QueryPage intFrame = new QueryPage();
				EcomPanel.add(intFrame);
				
				intFrame.setVisible(true);
			}
		});
		btnQueries.setBounds(10, 23, 109, 38);
		panel_2.add(btnQueries);
		
		JButton btnFeedback = new JButton("Feedback");
		btnFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EcomPanel.removeAll();
				FeedbackPage intFrame = new FeedbackPage();
				EcomPanel.add(intFrame);
				
				intFrame.setVisible(true);
			}
		});
		btnFeedback.setBounds(10, 89, 109, 38);
		panel_2.add(btnFeedback);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SignIn backToLogin = new SignIn();
				backToLogin.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(10, 733, 109, 32);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\acer\\eclipse-workspace\\test\\Pic\\exit_3_24.png"));
		btnNewButton_1.setBounds(914, 733, 32, 32);
		contentPane.add(btnNewButton_1);
		

		setUndecorated(true);
	}
}
