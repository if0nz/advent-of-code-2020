package it.ifonz.days.optimized;

import java.io.IOException;
import java.util.stream.IntStream;

import it.ifonz.common.FileReader;

public class Day15 {

	// TODO optimize
	public static void main(String[] args) throws IOException {
		var lines = FileReader.readLine("src/main/resources/d15.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(String row) {
		var limit = 2020;
		var spokenNumbers = new int[limit];
		var split = row.split(",");
		var lastNumberSpoken = 0;
		for (var i = 0; i < split.length; i++) {
			var string = row.split(",")[i];
			spokenNumbers[i] = Integer.parseInt(string);
			lastNumberSpoken = Integer.parseInt(string);
		}
		for (int i = split.length; i < limit; i++) {
			var spokenNow = -1;
			final var lns = lastNumberSpoken;
			var ii = i;
			var when = IntStream.range(0, ii-1).parallel().filter(j ->spokenNumbers[j] == lns).max().orElse(-1);
			if (when == -1) {
				spokenNow = 0;
				spokenNumbers[i] = spokenNow;
				lastNumberSpoken = spokenNow;
			} else {
				
				lastNumberSpoken = i-1-when;
				spokenNumbers[i] = lastNumberSpoken;
			}
		}
		System.out.println(spokenNumbers[limit-1]);
	}

	

	public static void part2(String row) {
		var limit = 30000000;
		var spokenNumbers = new int[limit];
		var split = row.split(",");
		var lastNumberSpoken = 0;
		for (var i = 0; i < split.length; i++) {
			var string = row.split(",")[i];
			spokenNumbers[Integer.parseInt(string)] = i+1;
			lastNumberSpoken = Integer.parseInt(string);
		}
		spokenNumbers[lastNumberSpoken] = 0;
 		for (int i = split.length+1; i <= limit; i++) {
			if(spokenNumbers[lastNumberSpoken] == 0) {
				spokenNumbers[lastNumberSpoken] = i-1;
				lastNumberSpoken = 0;
			} else {
				lastNumberSpoken = i-1-spokenNumbers[lastNumberSpoken];
				spokenNumbers[lastNumberSpoken] = i-1;
			}
		}
 		System.out.println(lastNumberSpoken);
	}
	

}
