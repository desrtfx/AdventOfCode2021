package day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {
	
	static class Pair {
		private String[] data = new String[2];
		
		public Pair(String first, String second) {
			this.data[0] = first;
			this.data[1] = second;
		}
		
		public String get(int index) {
			return data[index];
		}
	} 
	
	static String polymer;
	static Map<String, Pair> map = new HashMap<>();;
	static Map<String, Long> pairs = new HashMap<>();
	static Map<Character, Long> freq = new HashMap<>();
	
	static {
		List<String> input = null;
		try {
			input = Files.readAllLines(Paths.get("Input/Day14.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		polymer = input.get(0);
		freq.put(polymer.charAt(0), 1L);
		
		for(int i = 1; i < polymer.length(); i++) {
			String key = polymer.substring(i-1, i+1);
			pairs.put(key, 1L + pairs.getOrDefault(key, 0L));
			freq.put(polymer.charAt(i), 1L + freq.getOrDefault(polymer.charAt(i), 0L));
		}
		
		
		
		for(int i = 2; i < input.size(); i++) {
			String[] tmp = input.get(i).split(" -> ");
			map.put(tmp[0], new Pair(tmp[0].charAt(0)+tmp[1], tmp[1]+tmp[0].charAt(1)));
			pairs.put(tmp[0], pairs.getOrDefault(tmp[0],0L));
		}
	}
	
	public static long part01() {
		poly(10);
		long min = Long.MAX_VALUE;
		long max = Long.MIN_VALUE;
		for(Long l : freq.values()) {
			min = Math.min(min, l);
			max = Math.max(max, l);
		}
		return max - min;
	}

	public static long part02() {
		poly(30);
		long min = Long.MAX_VALUE;
		long max = Long.MIN_VALUE;
		for(Long l : freq.values()) {
			min = Math.min(min, l);
			max = Math.max(max, l);
		}
		return max - min;
	}

	public static void poly(int steps) {
		for(int step = 0; step < steps; step++) {
			Map<String, Long> np = new HashMap<>();
			for(String key : pairs.keySet()) {
				long num = pairs.get(key);
				if(num > 0L) {
					String tmp = map.get(key).get(0);
					np.put(tmp, num + np.getOrDefault(tmp, 0L));
					tmp = map.get(key).get(1);
					np.put(tmp, num + np.getOrDefault(tmp, 0L));
					freq.put(tmp.charAt(0), num + freq.getOrDefault(tmp.charAt(0), 0L));
				
				
				}
			}
			pairs = np;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Part 1: " + part01());
		System.out.println("Part 2: " + part02());
		
	}

	
}
