package assignment1.UserBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import assignment1.Board;

public class RankingSystem {
	
	
	
	public List getAllFile() throws IOException {
		List<String> allFile = new ArrayList<String>();
		List<Integer> allpoint = new ArrayList<Integer>();
		File[] files = new File("/UserList").listFiles();
		
		for (File file:files) {
			if(file.isFile()) {
				allFile.add(file.getName());
				
			}
		}
		for (int i = 0; i <= allFile.size(); i++) {
			String pointsline = Files.readAllLines(Paths.get(allFile.get(i) + ".txt")).get(3);
			String[] curr = pointsline.split("-");
			int point = Integer.parseInt(curr[1]);
			allpoint.add(point);
			
		}
		allpoint.sort(Comparator.reverseOrder());
		return allpoint;
		
	}

}
