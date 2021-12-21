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

	static int[] pPos = { 2, 10 };

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

		total = (long) scores[(current + 1) % 2] * numRolls;

		return total;
	}

	public static long part02() {
		int[] pos = new int[2];
		pos[0] = 1;
		pos[1] = 9;
			long[][][][] universes = new long[22][10][22][10];
			universes[0][pos[0]][0][pos[1]] = 1;
			for (int score1 = 0; score1 < 21; score1++) {
				for (int score2 = 0; score2 < 21; score2++) {
					for (int p1 = 0; p1 < 10; p1++) {
						for (int p2 = 0; p2 < 10; p2++) {
							for (int r1 = 1; r1 <= 3; r1++) {
								for (int r2 = 1; r2 <= 3; r2++) {
									for (int r3 = 1; r3 <= 3; r3++) {
										int nPos1 = (p1 + r1 + r2 + r3) % 10;
											if (score1 + nPos1 + 1 >= 21) {
												universes[Math.min(score1 + nPos1 + 1,
												21)][nPos1][score2][p2] += universes[score1][p1][score2][p2];
											} else {
												for (int r4 = 1; r4 <= 3; r4++) {
													for (int r5 = 1; r5 <= 3; r5++) {
														for (int r6 = 1; r6 <= 3; r6++) {
															int nPos2 = (p2 + r4 + r5 + r6) % 10;
															universes[Math.min(score1 + nPos1 + 1, 21)][nPos1][Math.min(
															score2 + nPos2 + 1,
															21)][nPos2] += universes[score1][p1][score2][p2];
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			long[] totalWins = new long[2];
			for (int p1 = 0; p1 < 10; p1++) {
				for (int p2 = 0; p2 < 10; p2++) {
					for (int score = 0; score < 21; score++) {
						totalWins[0] += universes[21][p1][score][p2];
						totalWins[1] += universes[score][p1][21][p2];
					}
				}
			}
		return Math.max(totalWins[0], totalWins[1]);
	}

	public static void main(String[] args) {
		System.out.println("Part 01: " + part01());
		System.out.println("Part 02: " + part02());
	}

}
