package Frontend;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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


import Backend.Company;
import Backend.Employee;
import javax.swing.JSeparator;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDesktopPane;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.CardLayout;

//import net.proteanit.sql.DbUtils;



public class FinReport extends JInternalFrame {

	private JPanel contentPane;

	public Connection con;

	/**
	 * Launch the application.
	 */
	public static void Emp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinReport frame = new FinReport();
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
	public FinReport() {

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 25, 772, 653);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Year");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(53, 68, 51, 21);
		contentPane.add(lblNewLabel);
		
		JComboBox finYear = new JComboBox();
		
		finYear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		finYear.setModel(new DefaultComboBoxModel(new String[] {"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"}));
		finYear.setBounds(162, 68, 109, 21);
		contentPane.add(finYear);
		
		JComboBox finMonth = new JComboBox();
		finMonth.setModel(new DefaultComboBoxModel(new String[] {"All Months", "JAN", "FEB", "MAR", "APR", "MAY ", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"}));
		finMonth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		finMonth.setBounds(162, 99, 109, 21);
		contentPane.add(finMonth);
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMonth.setBounds(53, 99, 51, 21);
		contentPane.add(lblMonth);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(53, 152, 640, 21);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(53, 54, 156, 21);
		contentPane.add(separator_1);
		
		JLabel lblFinancialReport = new JLabel("Financial Report");
		lblFinancialReport.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFinancialReport.setBounds(53, 23, 189, 21);
		contentPane.add(lblFinancialReport);
		
		JComboBox filter = new JComboBox();
		filter.setFont(new Font("Tahoma", Font.PLAIN, 16));
		filter.setModel(new DefaultComboBoxModel(new String[] {"Year wise", "Year & Month wise"}));
		filter.setBounds(537, 68, 156, 21);
		contentPane.add(filter);
		
		JPanel chartPanel = new JPanel();
		chartPanel.setBounds(53, 167, 640, 420);
		contentPane.add(chartPanel);
		chartPanel.setLayout(new CardLayout(0, 0));
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(filter.getSelectedItem().toString().equals("Year wise"))
				{
					int year = Integer.parseInt(finYear.getSelectedItem().toString());
					chartPanel.removeAll();
					FinChart fy = new FinChart(year);
					chartPanel.add(fy);
					fy.setVisible(true);
				}
				else
				{
					chartPanel.removeAll();
					int year = Integer.parseInt(finYear.getSelectedItem().toString());
					
					String month = finMonth.getSelectedItem().toString();
					
					FinChart fm = new FinChart(year, month);
					chartPanel.add(fm);
					fm.setVisible(true);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(537, 101, 156, 21);
		contentPane.add(btnNewButton);


	}
}
