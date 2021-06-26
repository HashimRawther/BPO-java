package Frontend;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Backend.Company;
import Backend.Ecom;

public class ContactUs extends JFrame {
	private JTextField queryField;
	private JTextField feedField;
	private JTextField txtName;
	private JTextField txtEmail;
	private JPanel contentPane;
	private JComboBox comp;
	private JLabel queryStatus;
	private JLabel feedStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactUs frame = new ContactUs();
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
	public ContactUs() {
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100,772, 653);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Query");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(52, 120, 160, 30);
		getContentPane().add(lblNewLabel);
		
		queryField = new JTextField();
		queryField.setBounds(52, 163, 653, 127);
		getContentPane().add(queryField);
		queryField.setColumns(10);
		
		JButton btnQPost = new JButton("Post");
		btnQPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtName.getText();
				String cmp = comp.getSelectedItem().toString();
				String email = txtEmail.getText();
				String query = queryField.getText();
				Ecom q = new Ecom();
				q.addQuery(name, email, query, cmp);
				queryStatus.setText("Query sent");
			}
		});
		btnQPost.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnQPost.setBounds(52, 300, 111, 30);
		getContentPane().add(btnQPost);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(52, 340, 653, 2);
		getContentPane().add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Feedback");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(52, 352, 160, 30);
		getContentPane().add(lblNewLabel_1);
		
		feedField = new JTextField();
		feedField.setBounds(52, 392, 653, 127);
		getContentPane().add(feedField);
		feedField.setColumns(10);
		
		JButton btnFPost = new JButton("Post");
		btnFPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtName.getText();
				String cmp = comp.getSelectedItem().toString();
				//String email = txtEmail.getText();
				String feedback = feedField.getText();
				Ecom q = new Ecom();
				q.addFeedback(name, feedback, cmp);
				feedStatus.setText("Feedback sent");
				
			}
		});
		btnFPost.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFPost.setBounds(52, 529, 109, 30);
		getContentPane().add(btnFPost);
		
		txtName = new JTextField();
		txtName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtName.setText("");
			}
		});
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtName.setText("Name");
		txtName.setBounds(52, 27, 204, 30);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEmail.setText("");
			}
		});
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEmail.setText("Email");
		txtEmail.setColumns(10);
		txtEmail.setBounds(362, 27, 204, 30);
		getContentPane().add(txtEmail);
		
		Company c = new Company();
		List<String> companyList = c.getCompanies();

		String[] companies = companyList.toArray(new String[0]);
		
		comp = new JComboBox();
		comp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comp.setModel(new DefaultComboBoxModel(companies));
		comp.setBounds(52, 85, 204, 30);
		contentPane.add(comp);
		
		queryStatus = new JLabel("");
		queryStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		queryStatus.setBounds(173, 300, 234, 30);
		contentPane.add(queryStatus);
		
		feedStatus = new JLabel("");
		feedStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		feedStatus.setBounds(171, 529, 234, 30);
		contentPane.add(feedStatus);

	}
}
