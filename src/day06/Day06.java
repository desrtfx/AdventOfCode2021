package day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Day06 {
	
	static List<Integer> data = new ArrayList<>();
	
	
	
	static {

		try {

			List<String> lines = Files.readAllLines(Paths.get("Input/Day06.txt"));
			for (String l : lines) {
				String[] items = l.split(",");
				for(String item:items) {
					data.add(Integer.valueOf(item.trim()));
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}	
	

	public static long part01(List<Integer> data,int rounds) {
		long total = 0L;
		long[] ages = new long[9];
		for(int i = 0; i < ages.length; i++) {
			ages[i]=0L;
		}
		for(int item:data) {
			ages[item]++;
		}
		for(int round = 0; round < rounds; round++) {
			long count0 = ages[0];
			for( int i = 1; i < ages.length; i++) {
				ages[i-1] = ages[i];
			}
			ages[6] +=  count0;
			ages[8] = count0;
		}
		for(long l: ages) {
			total += l;
		}
		
		
		
		
		return total;
	}
	
	public static void main(String[] args) {
		List<Integer> test = new ArrayList<>();
		test.add(3);
		test.add(4);
		test.add(3);
		test.add(1);
		test.add(2);
		System.out.println("Test    -  18 generations: " + part01(test, 18));
		// part 1 - 80 days
		System.out.println("Part 01 -  80 generations: " + part01(data, 80));
		// part 2 : 256 days
		System.out.println("Part 02 - 256 generations: " + part01(data, 256));
		
	}	
	
	
}
