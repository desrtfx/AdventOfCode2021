package day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BingoCard {
	private int[][] board = new int[5][5];
	private int total = 0;

	public BingoCard(List<String> rows) {
		for (int i = 0; i < rows.size(); i++) {
			String[] items = rows.get(i).split("\s+");
			for (int j = 0; j < items.length; j++) {
				board[i][j] = Integer.valueOf(items[j].trim());
				total += board[i][j];
			}
		}
	}

	public boolean checkNum(int num) {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col] == num) {
					board[row][col] = -1;
					total -= num;
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkWin() {
		// rows:
		for (int row = 0; row < board.length; row++) {
			boolean win = true;
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col] != -1) {
					win = false;
				}
			}
			if (win) {
				return true;
			}
		}

		for (int col = 0; col < board[0].length; col++) {
			boolean win = true;
			for (int row = 0; row < board.length; row++) {
				if (board[row][col] != -1) {
					win = false;
				}
			}
			if (win) {
				return true;
			}
		}
		return false;
	}

	public int getTotal() {
		return total;
	}
	
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				out.append(String.format("%3d", board[row][col]));
			}
			out.append("\n");
		}
		return out.toString();
	}
	
}

public class Day04 {

	static List<String> data;
	static int[] drawnNumbers;
	static List<BingoCard> cards;

	public static void init(){
		cards = new ArrayList<>();
		List<String> data = new ArrayList<>();
		try {

			data = Files.readAllLines(Paths.get("Input/Day04.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < data.size(); i++) {
			// first line
			if (data.get(i).isEmpty() || data.get(i).length() == 0) {
				continue;
			}
			if (i == 0) {
				String[] items = data.get(i).split(",");
				drawnNumbers = new int[items.length];
				for (int j = 0; j < items.length; j++) {
					drawnNumbers[j] = Integer.valueOf(items[j].trim());

				}
			} else {
				List<String> rows = new ArrayList<>();
				for (int j = 0; j < 5; j++) {
					rows.add(data.get(i).trim());
					i++;
				}
				BingoCard card = new BingoCard(rows);
				cards.add(card);
			}
		}
	}

	public static int part01(List<BingoCard> cards, int[] numbers) {
		int result = 0;
		for(int i = 0; i < numbers.length; i++) {
			for(int b = 0; b < cards.size(); b++) {
				BingoCard c = cards.get(b);
				c.checkNum(numbers[i]);
				if (c.checkWin()) {
					return c.getTotal() * numbers[i];
				}
			}
		}

		return result;
	}
	
	public static int part02(List<BingoCard> cards, int[] numbers) {
		Map<Integer,Integer> rounds = new HashMap<>();
		int maxRounds = Integer.MIN_VALUE;
		for(int i = 0; i < numbers.length; i++) {
			for(int b = 0; b < cards.size(); b++) {
				BingoCard c = cards.get(b);
				c.checkNum(numbers[i]);
				if (c.checkWin() && (i>4)) {
					if (maxRounds < i) {
						maxRounds = i;
					}
					rounds.put(i, c.getTotal() * numbers[i]);
					cards.remove(b);
					b--;
				}
			}
		}

		return rounds.get(maxRounds);
		
	}
	
	

	public static void main(String[] args) {
		init();
		System.out.println("Part 1: " + part01(cards, drawnNumbers));
		init();
		System.out.println("Part 2: " + part02(cards, drawnNumbers));
	}

}
