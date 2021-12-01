package day01;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day01 {
	
	static int[] data;

	static {
		try {
			data = Files.readAllLines(Paths.get("Input/Day01.txt")).stream().mapToInt(Integer::parseInt).toArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public static int part01(int[] data) {
		int count = 0;
		for(int i = 1; i < data.length; i++) {
			if (data[i-1]<data[i]) {
				count++;
			}
		}
		
		return count;
	}
	
	public static int part02(int[] data) {
		int count = 0;
		int sum = data[0] + data[1] + data[2];
		for(int i = 1; i < data.length-2; i++) {
			int sum2 = data[i] + data[i+1] + data[i+2];
			if (sum < sum2) {
				count++;
			}
			sum = sum2;
		}
		
		return count;		
	}
	
	public static void main(String[] args) {
		int[] test = new int[] { 199, 200, 208, 210, 200, 207, 240, 269, 260, 263 };
		System.out.println("Test part 1: " + part01(test));
		System.out.println("Part 1: " + part01(data));

		System.out.println("Test part 2: " + part02(test));
		System.out.println("Part 2: " + part02(data));

	}
	
}
