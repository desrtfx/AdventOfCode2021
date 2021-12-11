package day11;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day11 {

	static int[][] map = new int[10][10];
	static List<Point> neighbors = new ArrayList<>();

	static {
		try {
			List<String> lines = Files.readAllLines(Paths.get("Input/Day11.txt"));
			for (int row = 0; row < lines.size(); row++) {
				String line = lines.get(row);
				for (int col = 0; col < line.length(); col++) {
					map[row][col] = (int) (line.charAt(col) - '0');
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		neighbors.add(new Point(1, 0));
		neighbors.add(new Point(1, 1));
		neighbors.add(new Point(0, 1));
		neighbors.add(new Point(-1, 1));
		neighbors.add(new Point(-1, 0));
		neighbors.add(new Point(-1, -1));
		neighbors.add(new Point(0, -1));
		neighbors.add(new Point(1, -1));
	}

	public static int tick() {
		int count = 0;
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				map[row][col]++;
			}
		}
		boolean noMoreFlashes = false;
		List<Point> flashed = new ArrayList<>();
		while (!noMoreFlashes) {
			noMoreFlashes = true;
			for (int row = 0; row < 10; row++) {
				for (int col = 0; col < 10; col++) {
					Point p = new Point(col, row);
					if (!flashed.contains(p) && map[row][col] > 9) {
						flashed.add(p);
						count++;
						noMoreFlashes = false;
						for (Point n : neighbors) {
							Point cur = new Point(p.x + n.x, p.y + n.y);
							increment(cur);
						}
					}
				}
			}
		}
		for (Point p : flashed) {
			map[p.y][p.x] = 0;
		}
		return count;
	}

	public static void increment(Point p) {
		if ((p.y >= 0) && (p.y < 10) && (p.x >= 0) && (p.x < 10)) {
			map[p.y][p.x]++;
		}
	}

	public static int part01() {
		int count = 0;
		for (int i = 0; i < 100; i++) {
			count += tick();
		}
		return count;
	}

	public static int part02() {
		int step = 100;
		while (true) {
			step++;
			int count = tick();
			if (count == 100) {
				return step;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Part 1: " + part01());
		System.out.println("Part 2: " + part02());
	}
}
