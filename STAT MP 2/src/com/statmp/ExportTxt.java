package com.statmp;

import java.io.FileWriter;
import java.io.IOException;

public class ExportTxt {
	
	public void generate(String sFileName) {
		try
		{
			FileWriter writer = new FileWriter(sFileName+".txt");

			for(int i = 0; i < Logger.getInstance().getLog().size(); i++) {
				writer.append(Logger.getInstance().getLog().get(i));
				writer.write(System.getProperty("line.separator"));
			}
			
		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
	}


}
