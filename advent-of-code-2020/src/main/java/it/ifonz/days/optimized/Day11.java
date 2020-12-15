package it.ifonz.days.optimized;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.ifonz.common.FileReader;

public class Day11 {

	public static void main(String[] args) throws IOException {
		var lines = FileReader.readLines("src/main/resources/d11.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> rows) {
		var ferry = new ArrayList<String>();
		var tempferry = new ArrayList<String>(rows);
		do {
			ferry = new ArrayList<>(tempferry);
			tempferry = expandP1(ferry);
		} while (!ferry.equals(tempferry));
		System.out.println(ferry.stream().mapToLong(s -> s.chars().filter(c -> c == '#').count()).sum());
	}

	private static ArrayList<String> expandP1(List<String> rows) {
		var ferry = new ArrayList<String>();
		for (int i = 0; i < rows.size(); i++) {
			var newRow = new StringBuilder();
			var row = rows.get(i);
			for (var j = 0; j < row.length(); j++) {
				if (row.charAt(j) == 'L') {
					var occupied = j > 0 && row.charAt(j - 1) == '#' || j + 1 < row.length() && row.charAt(j + 1) == '#'
							|| i + 1 < rows.size() && rows.get(i + 1).charAt(j) == '#'
							|| i > 0 && rows.get(i - 1).charAt(j) == '#'
							|| i > 0 && j + 1 < row.length() && rows.get(i - 1).charAt(j + 1) == '#'
							|| i > 0 && j > 0 && rows.get(i - 1).charAt(j - 1) == '#'
							|| i + 1 < rows.size() && j + 1 < row.length() && rows.get(i + 1).charAt(j + 1) == '#'
							|| i + 1 < rows.size() && j > 0 && rows.get(i + 1).charAt(j - 1) == '#';
					newRow.append(occupied ? 'L' : '#');
				} else if (row.charAt(j) == '.') {
					newRow.append('.');
				} else if (row.charAt(j) == '#') {
					var occupied = 0;
					// check left
					occupied += j > 0 && row.charAt(j - 1) == '#' ? 1 : 0;
					occupied += j + 1 < row.length() && row.charAt(j + 1) == '#' ? 1 : 0;
					occupied += i + 1 < rows.size() && rows.get(i + 1).charAt(j) == '#' ? 1 : 0;
					occupied += i > 0 && rows.get(i - 1).charAt(j) == '#' ? 1 : 0;
					occupied += i > 0 && j + 1 < row.length() && rows.get(i - 1).charAt(j + 1) == '#' ? 1 : 0;
					occupied += i > 0 && j > 0 && rows.get(i - 1).charAt(j - 1) == '#' ? 1 : 0;
					occupied += i + 1 < rows.size() && j + 1 < row.length()
							&& rows.get(i + 1).charAt(j + 1) == '#' ? 1 : 0;
					occupied += i + 1 < rows.size() && j > 0 && rows.get(i + 1).charAt(j - 1) == '#' ? 1 : 0;
					newRow.append(occupied >= 4 ? 'L' : '#');
				}
			}
			ferry.add(newRow.toString());
		}
		return ferry;
	}

	private static ArrayList<String> expandP2(List<String> numbers) {
		var ferry = new ArrayList<String>();
		for (int i = 0; i < numbers.size(); i++) {
			var newRow = new StringBuilder();
			var row = numbers.get(i);
			for (var j = 0; j < row.length(); j++) {
				if (row.charAt(j) == 'L') {
					var occupied = false;
					for (var k = 1; j - k >= 0 && !(occupied = (row.charAt(j - k) == '#')); k++) if ((row.charAt(j - k) == 'L')) break;
					for (var k = 1; !occupied && j + k < row.length() && !(occupied = (row.charAt(j + k) == '#')); k++) if ((row.charAt(j + k) == 'L')) break;
					for (var k = 1; !occupied && i + k < numbers.size() && !(occupied = (numbers.get(i + k).charAt(j) == '#')); k++) if ((numbers.get(i+k).charAt(j) == 'L')) break;
					for (var k = 1; !occupied && i - k >= 0 && !(occupied = (numbers.get(i - k).charAt(j) == '#')); k++) if ((numbers.get(i-k).charAt(j) == 'L')) break;
					for (var k = 1; !occupied && i - k >= 0 && j+k < row.length() &&!(occupied = numbers.get(i - k).charAt(j + k) == '#'); k++) if ((numbers.get(i - k).charAt(j + k) == 'L')) break;
					for (var k = 1; !occupied && i - k >= 0 && j-k >= 0 &&!(occupied = numbers.get(i - k).charAt(j - k) == '#'); k++) if ((numbers.get(i - k).charAt(j - k) == 'L')) break;
					for (var k = 1; !occupied && i + k < numbers.size() && j+k < row.length() &&!(occupied = numbers.get(i +k).charAt(j + k) == '#'); k++) if ((numbers.get(i + k).charAt(j + k) == 'L')) break;
					for (var k = 1; !occupied && i + k < numbers.size()  && j-k >= 0 &&!(occupied = numbers.get(i + k).charAt(j - k) == '#'); k++) if ((numbers.get(i + k).charAt(j - k) == 'L')) break;
					newRow.append(occupied ? 'L' : '#');
				} else if (row.charAt(j) == '.') {
					newRow.append('.');
				} else if (row.charAt(j) == '#') {
					var occupied = false;
					var cnt = 0;
					// check left
					for (var k = 1; j - k >= 0 && !(occupied = (row.charAt(j - k) == '#')); k++) if ((row.charAt(j - k) == 'L')) break;
					cnt += occupied ? 1 : 0;
					occupied = false;
					for (var k = 1; j + k < row.length() && !(occupied = (row.charAt(j + k) == '#')); k++) if ((row.charAt(j + k) == 'L')) break;
					cnt += occupied ? 1 : 0;
					occupied = false;
					for (var k = 1; i + k < numbers.size() && !(occupied = (numbers.get(i + k).charAt(j) == '#')); k++) if ((numbers.get(i+k).charAt(j) == 'L')) break;
					cnt += occupied ? 1 : 0;
					occupied = false;
					for (var k = 1; i - k >= 0 && !(occupied = (numbers.get(i - k).charAt(j) == '#')); k++) if ((numbers.get(i-k).charAt(j) == 'L')) break;
					cnt += occupied ? 1 : 0;
					occupied = false;
					for (var k = 1; i - k >= 0 && j+k < row.length() &&!(occupied = numbers.get(i - k).charAt(j + k) == '#'); k++) if ((numbers.get(i - k).charAt(j + k) == 'L')) break;
					cnt += occupied ? 1 : 0;
					occupied = false;
					for (var k = 1; i - k >= 0 && j-k >= 0 &&!(occupied = numbers.get(i - k).charAt(j - k) == '#'); k++) if ((numbers.get(i - k).charAt(j - k) == 'L')) break;
					cnt += occupied ? 1 : 0;
					occupied = false;
					for (var k = 1; i + k < numbers.size() && j+k < row.length() &&!(occupied = numbers.get(i +k).charAt(j + k) == '#'); k++) if ((numbers.get(i + k).charAt(j + k) == 'L')) break;
					cnt += occupied ? 1 : 0;
					occupied = false;
					for (var k = 1; i + k < numbers.size()  && j-k >= 0 &&!(occupied = numbers.get(i + k).charAt(j - k) == '#'); k++) if ((numbers.get(i + k).charAt(j - k) == 'L')) break;
					cnt += occupied ? 1 : 0;
					newRow.append(cnt >= 5 ? 'L' : '#');
				}
			}
			ferry.add(newRow.toString());
		}
		return ferry;
	}

	public static void part2(List<String> numbers) {
		var ferry = new ArrayList<String>();
		var tempferry = new ArrayList<String>(numbers);
		do {
			ferry = new ArrayList<>(tempferry);
			tempferry = expandP2(ferry);
		} while (!ferry.equals(tempferry));
		System.out.println(ferry.stream().mapToLong(s -> s.chars().filter(c -> c == '#').count()).sum());
	}

}
