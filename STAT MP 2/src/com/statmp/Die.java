package com.statmp;

import java.util.Random;

public class Die {
	
	private int num;
	private Random rand;
	
	public Die() {
		rand = new Random();
		num = 1;
	}
	
	public int hurl() {
		int randNum = 1+rand.nextInt(DieFaces.faces.length);
		num = randNum;
		return num;
	}

}
