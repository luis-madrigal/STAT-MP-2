package com.statmp;

import java.util.ArrayList;
import java.util.Arrays;

public class Logger {
	
	private static Logger instance;
	private ArrayList<String> log;
	private ExportTxt export;
	
	private Logger() {
		log = new ArrayList<String>();
		export = new ExportTxt();
	}
	
	public static Logger getInstance() {
		if(instance == null) {
			instance = new Logger();
		}
		return instance;
	}
	
	public void logTrial(int trialNum, int[] results) {
		log.add("Trial "+String.valueOf(trialNum)+": "+Arrays.toString(results));
		export.generate("log");
	}
	
//	public void logAdd(Task task) {
//		this.log.add((new Date()).toString()+" Added "+task.getTask()+" ");
//		export.generate("log");
//	}
//	
//	public void logRemove(Task task) {
//		this.log.add((new Date()).toString()+" Removed "+task.getTask());
//		export.generate("log");
//		
//	}
//	
//	public void logMarked(Task task) {
//		if(task.isCompleted())
//			this.log.add((new Date()).toString()+" Marked "+task.getTask());
//		else
//			this.log.add((new Date()).toString()+" Unmarked "+task.getTask());
//		export.generate("log");
//
//	}

	public ArrayList<String> getLog() {
		return log;
	}

	public void setLog(ArrayList<String> log) {
		this.log = log;
	}
}
