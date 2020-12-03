package it.ifonz.days.optimized;

import java.io.IOException;
import java.util.List;

import it.ifonz.common.FileReader;

public class Day03 {

	public static void main(String[] args) throws IOException {
		var lines = FileReader.readLines("src/main/resources/d03.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> map)  {
		var i=0;
		var j=0;
		var trees = 0;
		var lineLen = map.get(0).length();
		while (i < map.size()) {
			trees += map.get(i).charAt(j) == '#' ? 1 : 0;
			j = (j+3)%lineLen;
			i++;
		}
		System.out.println(trees);
	}

	public static void part2(List<String> map) {
		System.out.println(traverseRule(map, 1, 1)*
				traverseRule(map, 3, 1)*
				traverseRule(map, 5, 1)*
				traverseRule(map, 7, 1)*
				traverseRule(map, 1, 2));
	}
	
	private static long traverseRule(List<String> map, int right, int down) {
		var i=0;
		var j=0;
		var trees = 0;
		var lineLen = map.get(0).length();
		while (i < map.size()) {
			trees += map.get(i).charAt(j) == '#' ? 1 : 0;
			j = (j+right)%lineLen;
			i+=down;
		}
		return trees;
	}

}
