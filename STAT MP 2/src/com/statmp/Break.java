package com.histograms;

import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Break extends JLabel{
	
	private int num;
	private int freq;
	private double prob;
	
	public Break(int num) {
		this.num = num;
		this.freq = 0;
	}
	
	public void incFrequency() {
		freq++;
	}
	
	public void setProb(int N) {
		prob = (double)freq/N;
	}
	
	public void initLabel(int x, int y, int width, int height, Color color, int type) {
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		setBorder(border);
		setBounds(x, height-y, width, y);
		setBackground(color);
		setOpaque(true);
		setToolTip(type);
	}
	
	public void initEmptyLabel(int x, int y, int width, int height, int type) {
		setBounds(x, height-y, width, y);
		setToolTip(type);
	}
	
	private void setToolTip(int type) {
		if(type == Histogram.FREQ_HIST)
			setToolTipText("Result Sum: "+num+",Frequency: "+freq);
		else if(type == Histogram.PROB_HIST || type == Histogram.IDEAL_HIST) {
			DecimalFormat df = new DecimalFormat("#.########");
			setToolTipText("Result Sum: "+num+",Probability: "+df.format(prob));
		}
	}
		
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}
	
	public double getProb() {
		return prob;
	}

}