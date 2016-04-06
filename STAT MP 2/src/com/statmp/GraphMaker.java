package com.statmp;

import org.rosuda.JRI.Rengine;

public class GraphMaker 
{
	private Rengine re;
	
	public GraphMaker()
	{
		 String[] Rargs = {"--vanilla"};
		 re = new Rengine(Rargs, false, null);
		 re.assign("Dir", "C:/Users/JeruelJr/Pictures/ST-STAT/MP2");
		 re.eval("setwd(Dir)");
	}
	
	private void initFreqAndProbGraph(int[] sum)
	{
		re.assign("sum", sum);
		
		re.eval("N = length(sum)");
		re.eval("range = max(sum) - min(sum)");
		re.eval("classes = ceiling(sqrt(N))");
		re.eval("width = ceiling(range/classes)");

		re.eval("intervals = c()");
		re.eval("num = min(sum)");
		re.eval("maxNum = max(sum)");
		re.eval("for(i in 0:classes) {intervals = c(intervals, num); if(num >= max(maxNum))break;num = num + width}");
		re.assign("xaxt.String", "n");
		re.assign("main.String", "Histogram for Dice Simulation");
		re.assign("xlab.String", "Dice Throw Result Sums");
	}
	
	public void plotFrequencyGraph(int[] sum)
	{
		initFreqAndProbGraph(sum);
		re.assign("ylab.String", "Frequency");
		re.assign("name", "FrequncyGraph.jpeg");
		re.eval("jpeg(name)");
		re.eval("hist(sum, breaks = intervals, xaxt=xaxt.String, main = main.String, xlab = xlab.String, ylab = ylab.String)");
		re.eval("axis(1, at = intervals);");
		re.eval("dev.off()");
	}
	
	public void plotProbabilityGraph(int[] sum)
	{
		initFreqAndProbGraph(sum);
		re.assign("ylab.String", "Probability");
		re.assign("name", "ProbabilityGraph.jpeg");
		re.eval("jpeg(name)");
		re.eval("hist(sum, breaks = intervals, xaxt=xaxt.String, main = main.String, xlab = xlab.String, ylab = ylab.String, prob = TRUE)");
		re.eval("axis(1, at = intervals);");
		re.eval("dev.off()");
	}
	
	
}
