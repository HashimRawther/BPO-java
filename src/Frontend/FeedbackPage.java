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

import Backend.Ecom;
import Backend.Feedback;
import Backend.Query;

//import net.proteanit.sql.DbUtils;



public class FeedbackPage extends JInternalFrame {

	private JPanel contentPane;
	public Connection con;
	private JTable feedTable;
	private JLabel lblNewLabel_1;
	private JTextField feedField;
	/**
	 * Launch the application.
	 */
	public static void Emp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeedbackPage frame = new FeedbackPage();
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
	public FeedbackPage() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 25, 772, 653);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Feedback");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(41, 10, 145, 43);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(41, 45, 137, 2);
		contentPane.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 112, 661, 147);
		contentPane.add(scrollPane);
		
		String[][] rows = new String[15][2];
		String[] cols = new String[] {"Name", "Feedback"};
		
		Ecom qr = new Ecom();
		List<Feedback> fds = qr.getFeedbacks();
		int i=0;
		for( Feedback f:fds)
		{
			rows[i][0] = f.name;
			rows[i][1] = f.feedback;
			i++;
		}
		feedTable = new JTable(rows, cols);
		
		scrollPane.setViewportView(feedTable);
		
		feedField = new JTextField();
		feedField.setBounds(41, 286, 666, 244);
		contentPane.add(feedField);
		feedField.setColumns(10);
		
		feedTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	
	        	String feedback = feedTable.getValueAt(feedTable.getSelectedRow(), 1).toString();
	            
	        	feedField.setText(feedback);
	        	
	        }
	    });
		
		
		lblNewLabel_1 = new JLabel("Click on Feedback to view");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(41, 63, 421, 29);
		contentPane.add(lblNewLabel_1);
		


	}
}
