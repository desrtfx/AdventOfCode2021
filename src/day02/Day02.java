package day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;




public class Day02 {
	
	static class Cmd{
		String cmd;
		int dist;
		
		Cmd(String data){
			String[] info = data.split(" ");
			cmd = info[0];
			dist = Integer.valueOf(info[1]);
			
		}
		
		
		
	}
	
	
	
	
	static Cmd[] data;

	static {
		try {
			
			List<String> lines = Files.readAllLines(Paths.get("Input/Day02.txt"));
			data = new Cmd[lines.size()];
			for(int i = 0; i < lines.size(); i++) {
				data[i] = new Cmd(lines.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	static long part01(Cmd[] data) {
		long depth = 0L;
		long dist = 0L;
		
		for(int i=0; i < data.length; i++) {
			switch(data[i].cmd) {
				case "forward":
					dist += data[i].dist;
					break;
				case "up":
					depth -= data[i].dist;
					break;
				case "down":
					depth += data[i].dist;
					break;
				default:
					break;
			}
		}
		return depth * dist;
	}
	
	static long part02(Cmd[] data) {
		long depth = 0L;
		long dist = 0L;
		long aim = 0L;
		
		for(int i=0; i < data.length; i++) {
			switch(data[i].cmd) {
				case "forward":
					dist += data[i].dist;
					depth += aim * data[i].dist;
					break;
				case "up":
					aim -= data[i].dist;
					break;
				case "down":
					aim += data[i].dist;
					break;
				default:
					break;
			}
		}
		return depth * dist;
	}	
	
	
	
	

	public static void main(String[] args) {
		System.out.println("Part 1: " + part01(data));
		System.out.println("Part 2: " + part02(data));
	}
	
	
}
