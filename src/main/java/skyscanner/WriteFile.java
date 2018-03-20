package skyscanner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WriteFile {
	private FileWriter file;	
	public WriteFile() {
        String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        File logFile = new File("./logs", timeLog + ".txt");
		new File("./logs").mkdirs();
        try {
			file = new FileWriter(logFile);
        	System.out.println(logFile.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public void addText(String textToAdd) {
    	BufferedWriter writer = new BufferedWriter(file);
        try {
	      	System.out.print(textToAdd);
            writer.write(textToAdd);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}