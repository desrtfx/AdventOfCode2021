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
	static int height;
	static int width;
	static List<Point> localMins = new ArrayList<>();

	static {

		neighbors = new ArrayList<>();
		neighbors.add(new Point(-1, 0));
		neighbors.add(new Point(0, -1));
		neighbors.add(new Point(1, 0));
		neighbors.add(new Point(0, 1));

		try {
			List<String> lines = Files.readAllLines(Paths.get("Input/Day09.txt"));
			height = lines.size() + 2;
			width = lines.get(0).length() + 2;
			heightmap = new int[height][width];
			for (int row = 0; row < lines.size(); row++) {
				for (int col = 0; col < lines.get(row).length(); col++) {
					heightmap[row + 1][col + 1] = (int) (lines.get(row).charAt(col) - '0');
				}
			}
			for (int col = 0; col < width; col++) {
				heightmap[0][col] = 9;
				heightmap[height - 1][col] = 9;
			}
			for (int row = 0; row < height; row++) {
				heightmap[row][0] = 9;
				heightmap[row][width - 1] = 9;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static int part01(int[][] heightmap) {
		int sum = 0;
		for (int row = 1; row < height - 1; row++) {
			for (int col = 1; col < width - 1; col++) {
				int h = heightmap[row][col];
				boolean localMin = true;
				for (Point p : neighbors) {
					if (h >= heightmap[row + p.y][col + p.x]) {
						localMin = false;
					}
				}
				if (localMin) {
					sum += (h + 1);
					localMins.add(new Point(col, row));
				}
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		System.out.println("Part 1: " + part01(heightmap));
	}

}
