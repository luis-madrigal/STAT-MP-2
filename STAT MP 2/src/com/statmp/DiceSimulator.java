package com.statmp;

import java.util.Scanner;

public class DiceSimulator {
	
	private int nDice;
	private int trials;
	private Die[] dice;
	
	public DiceSimulator() {
		nDice = askInput("How many dice would you like to throw?");
		trials = askInput("How many trials?");
		start();
	}
	
	private int askInput(String question) {
		Scanner sc = new Scanner(System.in);
		System.out.println(question);
		return sc.nextInt();
	}
	
	public void start() {
		Logger.setFileName("log");
		dice = new Die[nDice];
		int[] results = new int[nDice];
		for(int i = 0; i < dice.length; i++) {
			dice[i] = new Die();
		}
		
		for(int i = 0; i < trials; i++) {
			for(int j = 0; j < dice.length; j++) {
				results[j] = dice[j].hurl();
			}
			Logger.getInstance().logTrial(i+1, results);
		}
	}

}
