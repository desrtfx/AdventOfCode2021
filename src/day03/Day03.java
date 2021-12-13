package day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day03 {

	static List<String> bins = new ArrayList<>();

	static {
		try {

			bins = Files.readAllLines(Paths.get("Input/Day03.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// bins is ArrayList<String> containing input

	public static void part1() {
		String gamma = "";
		String epsilon = "";

		for (int col = 0; col < bins.get(0).length(); col++) {
			int ones = 0;
			int zeroes = 0;
			for (int row = 0; row < bins.size(); row++) {
				if (bins.get(row).charAt(col) == '0')
					zeroes++;
				else
					ones++;
			}

			gamma += (ones > zeroes ? "1" : "0");
			epsilon += (ones < zeroes ? "1" : "0");
		}

		System.out.println(Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2));

	}

	public static void part2() {
		ArrayList<String> oxygen = new ArrayList<String>(bins);
		ArrayList<String> co2 = new ArrayList<String>(bins);

		int col = 0;
		while (oxygen.size() > 1) {
			int ones = 0;
			int zeroes = 0;
			for (int row = 0; row < oxygen.size(); row++) {
				if (oxygen.get(row).charAt(col) == '0')
					zeroes++;
				else
					ones++;
			}
			char max = (ones >= zeroes ? '1' : '0');
			for (int row = oxygen.size() - 1; row >= 0; row--) {
				if (oxygen.get(row).charAt(col) != max)
					oxygen.remove(row);
			}
			col++;
		}

		col = 0;
		while (co2.size() > 1) {
			int ones = 0;
			int zeroes = 0;
			for (int row = 0; row < co2.size(); row++) {
				if (co2.get(row).charAt(col) == '0')
					zeroes++;
				else
					ones++;
			}
			char min = (zeroes <= ones ? '0' : '1');
			for (int row = co2.size() - 1; row >= 0; row--) {
				if (co2.get(row).charAt(col) != min)
					co2.remove(row);
			}
			col++;
		}

		System.out.println(Integer.parseInt(oxygen.get(0), 2) * Integer.parseInt(co2.get(0), 2));
	}

	public static void main(String[] args) {
		part1();
		part2();
	}
	
	
	
}
