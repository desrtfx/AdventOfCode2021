package day09;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day09 {

	static int[][] heightmap;
	static List<Point> neighbors;

	static {

		neighbors = new ArrayList<>();
		neighbors.add(new Point(-1, 0));
		neighbors.add(new Point(0, -1));
		neighbors.add(new Point(1, 0));
		neighbors.add(new Point(0, 1));

		try {
			List<String> lines = Files.readAllLines(Paths.get("Input/Day09.txt"));
			heightmap = new int[lines.size()][lines.get(0).length()];
			for (int row = 0; row < lines.size(); row++) {
				for (int col = 0; col < lines.get(row).length(); col++) {
					heightmap[row][col] = (int) (lines.get(row).charAt(col) - '0');
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static int part01(int[][] heightmap) {
		int sum = 0;
		for (int row = 0; row < heightmap.length; row++) {
			for (int col = 0; col < heightmap[row].length; col++) {
				int h = heightmap[row][col];
				boolean localMin = true;
				for (Point p : neighbors) {
					if ((row + p.y >= 0) && (row + p.y < heightmap.length) && (col + p.x >= 0)
							&& (col + p.x < heightmap[row].length)) {
						if (h >= heightmap[row + p.y][col + p.x]) {
							localMin = false;
						}
					}
				}
				if (localMin) {
					sum += (h + 1);
				}
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		System.out.println("Part 1: " + part01(heightmap));
	}

}
