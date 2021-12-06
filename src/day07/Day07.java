package day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day07 {
	
	static List<Integer> data = new ArrayList<>();
	
	
	
	static {

		try {

			List<String> lines = Files.readAllLines(Paths.get("Input/Day07.txt"));
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

	public static void main(String[] args) {
		
	}
	
	
}
