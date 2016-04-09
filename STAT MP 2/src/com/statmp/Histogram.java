package com.histograms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;

public class Histogram extends JPanel{
	
	public static final int FREQ_HIST = 1;
	public static final int PROB_HIST = 2;
	public static final int IDEAL_HIST = 3;

	
	private int[] results;
	private int[] breakWidths;
	private int[] breakHeights;
	
	
	private Break[] breaks;
	private double[] counts;
	
	private int noOfDice;
	private int height;
	private int width;
	private int interval;
	private int goalRoll;
	
	public Histogram(int[] results, int noOfDice, int height, int width, int goalRoll) {
		this.results = results;
		this.noOfDice = noOfDice;
		this.height = height;
		this.width = width;
		this.goalRoll = goalRoll;
		
		this.breaks = new Break[(noOfDice*6)-noOfDice+1];
		this.breakHeights = new int[breaks.length];
		this.breakWidths = new int[breaks.length];
		
		this.initComponents();
		this.setBreaks();
		this.countFreq();
	}
	
	public Histogram(int noOfDice, int height, int width) {
		this.noOfDice = noOfDice;
		this.height = height;
		this.width = width;
		
		this.breaks = new Break[(noOfDice*6)-noOfDice+1];
		this.breakHeights = new int[breaks.length];
		this.breakWidths = new int[breaks.length];
		
		this.initComponents();
		this.setBreaks();
		this.findIdealProb();
	}
	
	private void initComponents() {
		this.setLayout(null);
		this.setBounds(0, 0, width, height);
	}
	
	private void setBreaks() {
		int maxPoss = noOfDice*6;
		int counter = noOfDice;
		int i = 0;
		interval = width/breaks.length;
		int n = 0;
		while(counter <= maxPoss) {
			breaks[i] = new Break(counter);
			breakWidths[i] = n;
			n += interval;
			counter++;
			i++;
		}
	}
	
	private void findIdealProb() {
		if(noOfDice == 1) {
			for(int i = 1; i <= 6; i++) {
				breaks[i-noOfDice].incFrequency();
			}
		} else if(noOfDice == 2) {
			for(int i = 1; i <= 6; i++) {
				for(int j = 1; j <= 6; j++) {
					breaks[i+j-noOfDice].incFrequency();
				}
			}
		} else if(noOfDice == 3) {
			for(int i = 1; i <= 6; i++) {
				for(int j = 1; j <= 6; j++) {
					for(int k = 1; k <= 6; k++) {
						breaks[i+j+k-noOfDice].incFrequency();
					}
				}
			}
		} else if(noOfDice == 4) {
			for(int i = 1; i <= 6; i++) {
				for(int j = 1; j <= 6; j++) {
					for(int k = 1; k <= 6; k++) {
						for(int l = 1; l <= 6; l++) {
							breaks[i+j+k+l-noOfDice].incFrequency();
						}
					}
				}
			}
		} else if(noOfDice == 5) {
			for(int i = 1; i <= 6; i++) {
				for(int j = 1; j <= 6; j++) {
					for(int k = 1; k <= 6; k++) {
						for(int l = 1; l <= 6; l++) {
							for(int m = 1; m <= 6; m++) {
								breaks[i+j+k+l+m-noOfDice].incFrequency();
							}
						}
					}
				}
			}
		}
		int possCombinations = (int) Math.pow(6.0, (double)noOfDice);
		for(int i = 0; i < breaks.length; i++) {
			breaks[i].setProb(possCombinations);
			System.out.print(breaks[i].getFreq()+",");
		}
		System.out.println();
		this.scaleGraph(possCombinations);
	}
	
	private void countFreq() {
		for(int i = 0; i < results.length; i++) {
			for(int j = 0; j < breaks.length; j++) {
				if(results[i] == breaks[j].getNum()) {
					breaks[j].incFrequency();
				}
			}
		}
	}
	
	private void scaleGraph(int N) {
		int scaleMult = 1;
		double[] freqPerc = new double[breaks.length];
		for(int i = 0; i < breaks.length; i++) {
			freqPerc[i] = ((double)breaks[i].getFreq()/N);
			for(int j = 1; j <= height; j++) {
				if(freqPerc[i] == 0) {
					breakHeights[i] = 0;
					break;
				}
				else if(((double)j/height) > freqPerc[i]) {
					breakHeights[i] = j;
					break;
				}				
			}
		}
		int maxBHeight = 0;
		for(int i = 0; i < breakHeights.length; i++) {
			if(maxBHeight < breakHeights[i])
				maxBHeight = breakHeights[i];
		}
		while((scaleMult+1)*maxBHeight < height) {
			scaleMult++;
		}
		for(int i = 0; i < breakHeights.length; i++) {
			breakHeights[i] *= scaleMult;
		}
//		System.out.println();
//		ArrayList<Break> dupVals = new ArrayList<Break>();
//		for(int i = 0; i < breakHeights.length; i++) {
//			for(int j = 0; j < breakHeights.length; j++) {
//				if(breakHeights[i] == breakHeights[j] && i != j && breaks[i].getFreq() != breaks[j].getFreq()) {
//					dupVals.add(breaks[i]);
//					break;
//				}
//			}
//		}
//		int n = dupVals.size();
//		int k;
//		Break temp;
//		for(int i = n; i >= 0; i--) {
//			for(int j = 0; j < n -1 ; j++) {
//				k = j+1;
//				if(dupVals.get(j).getFreq() > dupVals.get(k).getFreq()) {
//					temp = dupVals.get(j);
//					dupVals.set(j, dupVals.get(k));
//					dupVals.set(k, temp);
//				}
//			}
//		}
//		int add = 1;
//		for(int i = 0; i < dupVals.size(); i++) {
//			for(int j = 0; j < breaks.length; j++) {
//				if(breaks[j].equals(dupVals.get(i)) && i != j) {
//					System.out.println("SAME: "+dupVals.get(i).getFreq());
//					breaks[j] = dupVals.get(i);
//					breakHeights[j]+= add;		
//					add++;
//				}
//			}
//		}
//		System.out.println();
	}
	
	public void graph(int type) {
		this.scaleGraph(results.length);
		for(int i = 0; i < breaks.length; i++) {
			if(type == Histogram.PROB_HIST)
				breaks[i].setProb(results.length);
			if(breaks[i].getNum() == goalRoll)
				breaks[i].initLabel(breakWidths[i], breakHeights[i], interval, height, Color.red, type);
			else if(breaks[i].getFreq() == 0)
				breaks[i].initEmptyLabel(breakWidths[i], 100, interval, height);
			else
				breaks[i].initLabel(breakWidths[i], breakHeights[i], interval, height, Color.darkGray, type);
			this.add(breaks[i]);
		}
		this.repaint();
	}
	
	public void graphIdeal() {
		
		for(int i = 0; i < breaks.length; i++) {
			breaks[i].initLabel(breakWidths[i], breakHeights[i], interval, height, Color.darkGray, Histogram.IDEAL_HIST);
			this.add(breaks[i]);
		}
		this.repaint();
	}

}
