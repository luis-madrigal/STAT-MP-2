package com.statmp;

import java.util.Scanner;

public class DiceSimulator {
	
	private int nDices;
	private int trials;
	private Die[] dice;
	
	public DiceSimulator() {
		nDices = askInput("How many dices would you like to throw?");
		trials = askInput("How many trials?");
		start();
	}
	
	private int askInput(String question) {
		Scanner sc = new Scanner(System.in);
		System.out.println(question);
		return sc.nextInt();
	}
	
	public void start() {
		dice = new Die[nDices];
		int[] results = new int[nDices];
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
