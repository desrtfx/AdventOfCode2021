package day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day07 {
	
	//static List<Integer> data = new ArrayList<>();
	
	static int[] data;
	
	
	static {
		try {
			List<String> input = Files.readAllLines(Paths.get("Input/Day07.txt"));
			data = Arrays.stream(input.get(0).trim().split(",")).mapToInt(Integer::parseInt).toArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static int part01(int[] data) {
		int total = 0;
		int[] d = Arrays.copyOf(data, data.length);
		Arrays.sort(d);
		int median = d[d.length / 2];
		for(int item:data) {
			total += (Math.abs(item-median));
		}
		return total;
	}
	
	public static int part02(int[] data) {
		int total = 0;
		int sum = 0;
		for(int item : data) {
			sum += item;
		}
		int mean = sum/data.length;
		for(int item : data) {
			int diff = Math.abs(item - mean);
			total += diff * (diff +1) / 2;
		}
		return total;
	}
	
	
	public static void main(String[] args) {
		int[] test = {16,1,2,0,4,2,7,1,2,14};
		System.out.println("Test 1: " + part01(test));
		System.out.println("Part 1: " + part01(data));
		System.out.println("Test 2: " + part02(test));
		System.out.println("Part 2: " + part02(data));
	}
	
	
}
