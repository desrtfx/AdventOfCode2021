package day05;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day05 {

	static final Pattern PATTERN = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");

	static class Line {
		Point start;
		Point end;

		public Line(String data) {
			Matcher m = PATTERN.matcher(data);
			if (m.find()) {
				int startX = Integer.parseInt(m.group(1));
				int startY = Integer.parseInt(m.group(2));
				int endX = Integer.parseInt(m.group(3));
				int endY = Integer.parseInt(m.group(4));

				start = new Point(startX, startY);
				end = new Point(endX, endY);
			}
		}

		public boolean isHorizontal() {
			return (start.y == end.y);
		}

		public boolean isVertical() {
			return (start.x == end.x);
		}

		public List<Point> points() {
			List<Point> points = new ArrayList<>();
			if (isHorizontal()) {
				int startX = Math.min(start.x, end.x);
				int endX = Math.max(start.x, end.x);
				for (int x = startX; x <= endX; x++) {
					points.add(new Point(x, start.y));
				}
			} else if (isVertical()) {
				int startY = Math.min(start.y, end.y);
				int endY = Math.max(start.y, end.y);
				for (int y = startY; y <= endY; y++) {
					points.add(new Point(start.x, y));
				}
			} else {
				int dx = end.x - start.x;
				int dy = end.y - start.y;
				int sgnX = (int)Math.signum(dx);
				int sgnY = (int)Math.signum(dy);
				
				Point p = new Point(start.x, start.y);
				while ((p.x != end.x) && (p.y != end.y)) {
					points.add(p);
					p = new Point(p.x + sgnX, p.y + sgnY);

				}
				points.add(end);

			}
			return points;
		}

	}

	static List<Line> lines = new ArrayList<>();

	static {

		try {

			List<String> data = Files.readAllLines(Paths.get("Input/Day05.txt"));
			for (String l : data) {
				lines.add(new Line(l));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static int part01(List<Line> lines) {
		Map<Point, Integer> grid = new HashMap<>();
		for (Line l : lines) {
			if (l.isHorizontal() || l.isVertical()) {
				List<Point> points = l.points();
				for (Point p : points) {
					grid.put(p, grid.getOrDefault(p, 0) + 1);
				}
			}
		}
		int total = 0;
		for (Integer i : grid.values()) {
			if (i > 1) {
				total++;
			}
		}
		return total;
	}

	public static int part02(List<Line> lines) {
		Map<Point, Integer> grid = new HashMap<>();
		for (Line l : lines) {
			List<Point> points = l.points();
			for (Point p : points) {
				grid.put(p, grid.getOrDefault(p, 0) + 1);
			}
		}
		int total = 0;
		for (Integer i : grid.values()) {
			if (i > 1) {
				total++;
			}
		}
		return total;
	}

	public static void main(String[] args) {
		System.out.println(part01(lines));
		System.out.println(part02(lines));
		
	}

}
