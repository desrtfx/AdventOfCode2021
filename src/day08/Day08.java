package day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

record Segment(String[] wiring, String[] output) {}


public class Day08 {
	
	static List<Segment> data = new ArrayList<>();
	
	static {
		try {
			List<String> input = Files.readAllLines(Paths.get("Input/Day08.txt"));
			for(String item : input) {
				String[] parts = item.split(" \\| ");
				String[] wiring = parts[0].trim().split("\s{1}");
				String[] output = parts[1].trim().split(" ");
				for(int i = 0; i < wiring.length; i++) {
					wiring[i] = sortString(wiring[i]);
				}
				for(int i = 0; i < output.length; i++) {
					output[i] = sortString(output[i]);
				}
				data.add(new Segment(wiring, output));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	}
	

	private static String sortString(String s) {
		char[] charArray = s.toCharArray();
		Arrays.sort(charArray);
		return String.valueOf(charArray);
	}
	
	
	public static int part01(List<Segment> data) {
		int sum = 0;
		for (Segment item : data) {
			for(String p:item.output()) {
				boolean valid = (p.length() == 2 || p.length() == 3 || p.length() == 4 || p.length() == 7);
				sum += (valid ? 1 : 0);
			}
		}
		
		return sum;
	}
	
	public static int part02(List<Segment> data) {
		// length 2 -> 1
		// length 3 -> 7
		// length 4	-> 4
		// length 5 -> 2, 3, 5
		// length 6 -> 0, 6, 9
		// length 7 -> 8
		// when sorted the lengths are:
		// 0 -> 2
		// 1 -> 3
		// 2 -> 4
		// 3 -> 5
		// 4 -> 5
		// 5 -> 5
		// 6 -> 6
		// 7 -> 6
		// 8 -> 6
		// 9 -> 7
		
		int sum = 0;
		for (Segment item : data) {
			String[] wiring = item.wiring();
			Arrays.sort(wiring, Comparator.comparingInt(String::length));
			String[] output = item.output();
			Map<String, Integer> map = new HashMap<>();
			Map<Integer, String> rmap = new HashMap<>();
			map.put(wiring[0], 1); rmap.put(1,  wiring[0]); // shortest (length 2) must be 1
			map.put(wiring[1], 7); rmap.put(7,  wiring[1]); // second shortest (length 3) must be 7
			map.put(wiring[2], 4); rmap.put(4,  wiring[2]); // third shortest (length 4) must be 4
			map.put(wiring[9], 8); rmap.put(8,  wiring[9]); // longest (length 7) must be 8

			// All length 6 digits: 0, 6, 9
			// 0 has the segments of 1 and 7, but not 4
			// 9 has the segments of 1, 4, and 7
			// 6 has none of 1, 4, and 7
			// all the length 6 digits are at indexes 6 to 8
		    for (int i = 6; i <= 8; i++) {
		    	String t = wiring[i];
		    	// check for 9
		    	if (matches(t, rmap.get(1)) && matches(t, rmap.get(4)) && matches(t, rmap.get(7))) {
		    		map.put(t, 9);
		    		rmap.put(9, t);
		    	} else {
		    		if (matches(t, rmap.get(1)) && matches(t, rmap.get(7)) && !matches(t, rmap.get(4))) {
		    			map.put(t, 0);
		    			rmap.put(0, t);
		    		} else {
		    			map.put(t, 6);
		    			rmap.put(6, t);
		    		}
		    	}
		    }
		    // length 5 digits
		    for (int i = 3; i <= 5; i++) {
		    	String t = wiring[i];
		    	// 3
		    	if (matches(t, rmap.get(1)) && matches(t, rmap.get(7))) {
		    		map.put(t, 3);
		    		rmap.put(3, t);
		    	} else {
		    		if (matches(rmap.get(6), t)) {
		    			map.put(t, 5);
		    			rmap.put(5,  t);
		    		} else {
		    			map.put(t,  2);
		    			rmap.put(5, t);
		    		}
		    	}
		    }
		
		    int num = 0;
		    for (int i = 0; i < output.length; i++) {
		    	num = (num * 10) + map.get(output[i]);
		    }
		    sum += num;
		}
		return sum;
	}
	
	
	public static boolean matches(String where, String what) {
		for (char c : what.toCharArray()) {
			if (where.indexOf(c) == -1) {
				return false;
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		System.out.println("Part 1: " + part01(data));
		System.out.println("Part 2: " + part02(data));
	}
	
	
	
}
