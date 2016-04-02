package com.statmp;

import java.util.Scanner;

public class DiceSimulator {
	
	private int nDice;
	private int goalRoll;
	private int correctGuessCount;
	private long trials;
	private Die[] dice;
	
	public DiceSimulator() 
	{
		correctGuessCount=0;
		do
		{
		nDice = askInput("How many dice would you like to throw?");
		}while(nDice<1 || nDice>5);
		
		dice = new Die[nDice];
		for(int i = 0; i < dice.length; i++) 
		{
			dice[i] = new Die();
		}
		
		do
		{
		trials = askInput("How many trials?");
		}while(trials<1 || trials>100000);
		
		do
		{
		goalRoll = askInput("What's your goal roll?");
		}while(goalRoll<dice.length || goalRoll>dice.length*6);
		start();
	}
	
	private int askInput(String question) {
		Scanner sc = new Scanner(System.in);
		System.out.println(question);
		return sc.nextInt();
	}
	
	private int getRollTotal(int[] results)
	{
		int total=0;
		for(int i=0; i<results.length; i++)
		{
			total += results[i];
		}
		return total;
	}
	
	private void countCorrectGuesses(int[] results)
	{
		if(getRollTotal(results)==goalRoll)
			correctGuessCount++;
	}
	
	public void start() 
	{	
		int[] results = new int[nDice];
		for(int i = 0; i < trials; i++) 
		{
			for(int j = 0; j < dice.length; j++) 
			{
				results[j] = dice[j].hurl();
			}
			countCorrectGuesses(results);
			Logger.getInstance().logTrial(i+1, results);
		}
		Logger.getInstance().logCorrectGuessCount(correctGuessCount);
		
		Logger.getInstance().generateLog();
	}

}
