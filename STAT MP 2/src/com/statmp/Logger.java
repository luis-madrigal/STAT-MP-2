package com.statmp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Logger {
	
	private static final String fileName = "log";
	
	private static Logger instance;
	private ArrayList<String> log;
	private FileWriter writer;
	
	private Logger() {
		log = new ArrayList<String>();
		try {
			writer = new FileWriter(fileName+".txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Logger getInstance() {
		if(instance == null) {
			instance = new Logger();
		}
		return instance;
	}
	
	public void logTrial(int trialNum, int[] results) {
		log.add("Trial "+String.valueOf(trialNum)+": "+Arrays.toString(results));
	}

	public ArrayList<String> getLog() {
		return log;
	}

	public void setLog(ArrayList<String> log) {
		this.log = log;
	}
	
	public void generateLog(){
		try {
			for(int i = 0; i < Logger.getInstance().getLog().size(); i++) {
				
				
					writer.append(Logger.getInstance().getLog().get(i));
					writer.write(System.getProperty("line.separator"));
			
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
