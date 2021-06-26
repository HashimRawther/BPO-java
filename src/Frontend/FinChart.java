package Frontend;
import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import Backend.Transactions;



public class FinChart extends JInternalFrame {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	enum Months{
		JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
	};
	int year;
	int month;
	String[] months= {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
	
	public FinChart(int year) {
        super("Debit and Credit Transactions vs Month");
        this.year=year;
        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);
        setSize(640, 420);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
    }
	
	public FinChart(int year, String monthStr)
	{
		
		super("Debit and Credit Transactions vs Day");
		for(int i=0; i<months.length; i++)
            if(monthStr.equals(months[i]))
            {
                this.month=i+1;
                break;
            }
        this.year=year;

        //System.out.println(this.month);
        JPanel chartPanel = createChartPanelMonth();
        add(chartPanel, BorderLayout.CENTER);
        setSize(640, 420);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
	}
 
    private JPanel createChartPanel() {
        // creates a line chart object
        // returns the chart panel
    	 String chartTitle = "Totla Amount vs Month";
    	    String categoryAxisLabel = "Month";
    	    String valueAxisLabel = "Total Amount";
    	 
    	    CategoryDataset dataset = createDataset();
    	 
    	    JFreeChart chart = ChartFactory.createLineChart(chartTitle,
    	            categoryAxisLabel, valueAxisLabel, dataset);
    	 
    	    return new ChartPanel(chart);
    }
 
    private JPanel createChartPanelMonth() {
        // creates a line chart object
        // returns the chart panel
         String chartTitle = "Totla Amount vs Month";
            String categoryAxisLabel = "Month";
            String valueAxisLabel = "Total Amount";

            CategoryDataset dataset = createDatasetMonth();

            JFreeChart chart = ChartFactory.createLineChart(chartTitle,
                    categoryAxisLabel, valueAxisLabel, dataset);

            return new ChartPanel(chart);
    }
 
    private CategoryDataset createDatasetMonth() {
        // creates chart dataset...
        // returns the dataset
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            String series1 = "Debit";
            String series2 = "Credit";

            Transactions t=new Transactions();
            Map<Integer,Double> debit=t.report("Debit", this.year,this.month); 
           for(int i=1; i<=31; i++)
               if(debit.containsKey(i)==true)
                       dataset.addValue(debit.get(i), series1, String.valueOf(i));
               else
                    dataset.addValue(0, series1, String.valueOf(i));

           Map<Integer,Double> credit=t.report("Credit", this.year,this.month); 
           for(int i=1; i<=31; i++)
               if(credit.containsKey(i)==true)
                       dataset.addValue(credit.get(i), series2, String.valueOf(i));
               else
                    dataset.addValue(0, series2, String.valueOf(i));

            return dataset;
    }
    
    private CategoryDataset createDataset() {
        // creates chart dataset...
        // returns the dataset
    	 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	    String series1 = "Debit";
    	    String series2 = "Credit";

    	    Transactions t=new Transactions();
    	    //System.out.println(this.year);
    	    Map<String,Double> debit=t.report("Debit", this.year); 
    	    
    	    for(Months month:Months.values()) {
    	    	if(debit.containsKey(String.valueOf(month))==true)
    	    		dataset.addValue(debit.get(String.valueOf(month)), series1, String.valueOf(month));
    	    	else
    	    		dataset.addValue(0, series1,  String.valueOf(month));
    	    		
    	    }
    	    
    	    //System.out.println(this.year);
    	    Map<String,Double> credit=t.report("Credit", this.year); 

    	    for(Months month:Months.values()) {
    	    	if(credit.containsKey(String.valueOf(month))==true)
    	    		{
    	    			
    	    			dataset.addValue(credit.get(String.valueOf(month)), series2, String.valueOf(month));
    	    		}
    	    	else
    	    		dataset.addValue(0, series2,  String.valueOf(month));
    	    		
    	    }
    	  	    
    	    return dataset;
    }
 
}


