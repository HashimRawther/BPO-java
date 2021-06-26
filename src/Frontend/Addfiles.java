package Frontend;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Backend.Documents;
import Backend.FileOpener;

//import net.proteanit.sql.DbUtils;



public class Addfiles extends JInternalFrame {

	private JPanel contentPane;
	private JTable fileTable;
	public Connection con;
	private JLabel lblNewLabel_1;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void Emp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Addfiles frame = new Addfiles();
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
	
	private void displayTable()
	{
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {},
				new String[] {
					"File", "File Path"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.setBounds(74, 207, 601, 160);
		
		Documents d = new Documents();
		List<String> fps = d.getFilePaths();
		String[] fileInfo = fps.toArray(new String[0]);

		
		for(int i=0; i<fps.size(); i++)
		{
			String fileName = fps.get(i).substring(fps.get(i).lastIndexOf("\\")+1);
			//System.out.println(fps.get(i));
			Vector row = new Vector();
			row.add(fileName);
			row.add(fps.get(i));
			model.addRow(row);
		}
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	
	        	String fp = table.getValueAt(table.getSelectedRow(), 1).toString();
				File file = new File(fp);
				try {
					Desktop desktop = Desktop.getDesktop();
					if(file.exists())         //checks file exists or not
						desktop.open(file);
				}catch(Exception fileEx) {}

	        }
		});
		scrollPane = new JScrollPane();
		scrollPane.setBounds(74, 236, 601, 207);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		

	}
	
	public Addfiles() {
		


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 25, 772, 653);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel fileStatus = new JLabel("");
		fileStatus.setBounds(298, 103, 372, 33);
		contentPane.add(fileStatus);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(74, 103, 157, 33);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = new String();
				FileOpener obj = new FileOpener();
				try {
					path = obj.pick();
				}catch(Exception e1)
				{
					e1.printStackTrace();
				}
			Documents d = new Documents();
			Integer flag = d.addFile(path);
			if(flag == 0)
			{
				fileStatus.setText("File already exists!");
			}
			else
			{
				fileStatus.setText("File added Successfully!");
			}
			displayTable();
			}
			});
		contentPane.setLayout(null);
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Add File / Documents");
		lblNewLabel.setBounds(74, 28, 299, 41);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);		
		
		lblNewLabel_1 = new JLabel("Available Files");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(74, 185, 299, 33);
		contentPane.add(lblNewLabel_1);
		
		displayTable();
		



		

	}
}
