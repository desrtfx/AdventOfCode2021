package day13;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day13 {
	
	static List<String> input;
	static List<Point> points;
	static List<String> folds;
	
	static {
		input = null;
		try {
			input = Files.readAllLines(Paths.get("Input/Day13.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		points = new ArrayList<>();
		folds = new ArrayList<>();
		for (String l: input) {
			if (l.isEmpty()) {
				continue;
			}
			if((l.charAt(0) >= '0') && (l.charAt(0)<='9')) {
				String[] fields = l.split(",");
				Point p = new Point(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]));
				points.add(p);
			} else {
				if (l.charAt(0) == 'f') {
					String r = l.replace("fold along ", "");
					folds.add(r);
				}
			}
		}
	}
	
	public static List<Point> fold(List<Point> points, char along, int axis) {
		for(Point p : points) {
			if(along == 'x') {
				if(p.x > axis) {
					p.x = axis - (p.x - axis);
				}
			} else {
				if(p.y > axis) {
					p.y = axis - (p.y - axis);
				}
			}
		}
		Set<Point> reduced = new HashSet<>(points);
		List<Point> p1 = new ArrayList<>(reduced);
		return p1;
	}
	
	public static int part01(List<Point> points) {
		List<Point> p = new ArrayList<>(points);
		String fold = folds.get(0);
		char along = fold.charAt(0);
		int axis = Integer.parseInt(fold.substring(2));
		p = fold(p, along, axis);
		return p.size();
	}
	
	public static String part02(List<Point> points) {
		List<Point> p = new ArrayList<>(points);
		for(String fold : folds) {
			char along = fold.charAt(0);
			int axis = Integer.parseInt(fold.substring(2));
			p = fold(p, along, axis);			
		}
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		for(Point cur: p) {
			minX = Math.min(minX, cur.x);
			minY = Math.min(minY, cur.y);
			maxX = Math.max(maxX, cur.x);
			maxY = Math.max(maxY, cur.y);
		}
		char[][] result = new char[maxY-minY+1][maxX-minX+1];
		for(Point cur: p) {
			result[cur.y-minY][cur.x-minX] = '#';
		}
		StringBuilder s = new StringBuilder();
		for(int y = 0; y < result.length; y++) {
			s.append(result[y]);
			s.append("\n");
		}
		return s.toString();
	}

	public static void main(String[] args) {
		System.out.println("Part 1: " + part01(points));
		System.out.println("Part 2: \n" + part02(points));
	}
	
}
