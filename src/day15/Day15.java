package day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day15 {

	
	static {
		try {
			List<String> lines = Files.readAllLines(Paths.get("Input/Day09.txt"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
	
}
