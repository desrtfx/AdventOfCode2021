package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 {

	static Map<Character, Character> pairs = new HashMap<>();
	static List<String> data;

	static {
		pairs.put('(', ')');
		pairs.put('[', ']');
		pairs.put('{', '}');
		pairs.put('<', '>');

		try {
			data = Files.readAllLines(Paths.get("Input/Day10.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static int part01() {
		Map<Character, Integer> points = new HashMap<>();
		points.put(')', 3);
		points.put(']', 57);
		points.put('}', 1197);
		points.put('>', 25137);
		int result = 0;
		Deque<Character> expected = new ArrayDeque<>();
		for (String line : data) {
			for (char ch : line.toCharArray()) {
				if (pairs.containsKey(ch)) {
					expected.push(pairs.get(ch));
				} else {
					if (ch != expected.pop()) {
						result += points.get(ch);
						break;
					}
				}

			}
		}
		return result;
	}

	public static long part02() {
		Map<Character, Long> points = new HashMap<>();
		points.put(')', 1L);
		points.put(']', 2L);
		points.put('}', 3L);
		points.put('>', 4L);
		List<Long> scores = new ArrayList<>();
		Deque<Character> expected = new ArrayDeque<>();
		for (String line : data) {
			for (char ch : line.toCharArray()) {
				if (pairs.containsKey(ch)) {
					expected.push(pairs.get(ch));
				} else {
					if (expected.peek() == ch) {
						expected.pop();
					} else {
						expected.clear();
						break;
					}
				}
			}
			long score = 0L;
			while (!expected.isEmpty()) {
				score = score * 5 + points.get(expected.pop());
			}
			if (score != 0) {
				scores.add(score);
			}
		}
		scores.sort(Comparator.naturalOrder());
		return scores.get(scores.size() / 2);
	}

	public static void main(String[] args) {
		System.out.println("Part 1: " + part01());
		System.out.println("Part 2: " + part02());
	}

}
