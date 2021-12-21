package day21;

import java.util.Random;

class Die {
	private int sides;
	private static int nextDeterministic = 0;
	private Random rng;
	
	public Die(int sides) {
		this.sides = sides;
		rng = new Random();
	}
	
	public static int getNextDeterministic() {
		int next = nextDeterministic + 1;
		if (next > 100) {
			next = 1;
		}
		nextDeterministic = next;
		return nextDeterministic;
	}
	
	public int roll() {
		return rng.nextInt(sides) + 1;
	}
}



public class Day21 {
	
	static int[] pPos = {2, 10};
	
	
	public static long part01() {
		long total = 0L;
		int[] scores = new int[2];
		scores[0] = 0;
		scores[1] = 0;
		int current = 0;
		int numRolls = 0;
		while (true) {
			int[] rolls = new int[3];
			int sum = 0;
			for (int i = 0; i < rolls.length; i++) {
				rolls[i] = Die.getNextDeterministic();
				sum += rolls[i];
				numRolls++;
			}
			int res = (pPos[current] + sum);
			while (res > 10) {
				res -= 10;
			}
			pPos[current] = res;
			scores[current] += res;
			if (scores[current] > 1000) {
				break;
			}
			current = (current + 1) % 2;
		}
		
		total = (long)scores[(current + 1) % 2] * numRolls;
		
		
		return total;
	}
	
	public static void main(String[] args) {
		System.out.println("Part 01: " + part01());
	}
	
	

}
