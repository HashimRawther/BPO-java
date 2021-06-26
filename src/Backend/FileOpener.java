package Backend;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import java.util.Scanner;

public class FileOpener {
    JFileChooser file_chooser = new JFileChooser();
    public StringBuilder ab = new StringBuilder();

    public String pick() throws FileNotFoundException
    {
    	String path = "";
        if(file_chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File file = file_chooser.getSelectedFile();
            path = file.getAbsolutePath();
            Scanner input = new Scanner(file);

            while (input.hasNext())
            {
                ab.append(input.nextLine());
                ab.append("\n");
            }
            input.close();


        }
        else {
            ab.append("No File is Selected");
        }
        return path;
    }

}