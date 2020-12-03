package it.ifonz.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import it.ifonz.common.FileReader;

public class Day03 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		var lines = FileReader.readLines("src/main/resources/d03.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> input)  {
		List<String> map = new ArrayList<>(input);
		for (int i = 0; i < map.size(); i++) {
			String line = map.get(i);
			String newLine = "";
			for (int j = 0; j < line.length()*4; j++) {
				newLine = newLine.concat(line);
			}
			map.set(i, newLine);
		}
		int i=0;
		int j=0;
		int trees = 0;
		while (i < map.size()) {
			trees += map.get(i).charAt(j) == '#' ? 1 : 0;
			j+=3;
			i++;
		}
		System.out.println(trees);
	}

	public static void part2(List<String> input) {
		List<String> map = new ArrayList<>(input);
		for (int i = 0; i < map.size(); i++) {
			String line = map.get(i);
			String newLine = "";
			for (int j = 0; j < line.length()*4; j++) {
				newLine = newLine.concat(line);
			}
			map.set(i, newLine);
		}
		System.out.println(traverseRule(map, 1, 1)*
				traverseRule(map, 3, 1)*
				traverseRule(map, 5, 1)*
				traverseRule(map, 7, 1)*
				traverseRule(map, 1, 2));
	}
	
	private static long traverseRule(List<String> map, int right, int down) {
		
		int i=0;
		int j=0;
		int trees = 0;
		while (i < map.size()) {
			trees += map.get(i).charAt(j) == '#' ? 1 : 0;
			j+=right;
			i+=down;
		}
		return trees;
	}

}
