package it.ifonz.days.optimized;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import it.ifonz.common.FileReader;

public class Day05 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		var lines = FileReader.readLines("src/main/resources/d05.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> input) {
		var max = input.stream().mapToInt(seat -> {
			var rowLowerBound = 0d;
			var rowUpperBound = 127d;
			var columnLowerBound = 0d;
			var columnUpperBound = 7d;
			for (var c : seat.toCharArray()) {
				switch (c) {
				case 'F':
					rowUpperBound = Math.ceil((rowLowerBound + rowUpperBound) / 2);
					break;
				case 'B':
					rowLowerBound = Math.ceil((rowLowerBound + rowUpperBound) / 2);
					break;
				case 'R':
					columnLowerBound = Math.ceil((columnLowerBound + columnUpperBound) / 2);
					break;
				case 'L':
					columnUpperBound = Math.ceil((columnLowerBound + columnUpperBound) / 2);
					break;
				default:
					break;
				}
			}
			return (int) rowLowerBound * 8 + (int) columnLowerBound;
		}).max().getAsInt();
		System.out.println(max);
	}

	public static void part2(List<String> input) {
		var seats = input.stream().mapToInt(seat -> {
			var rowLowerBound = 0d;
			var rowUpperBound = 127d;
			var columnLowerBound = 0d;
			var columnUpperBound = 7d;
			for (var c : seat.toCharArray()) {
				switch (c) {
				case 'F':
					rowUpperBound = Math.ceil((rowLowerBound + rowUpperBound) / 2);
					break;
				case 'B':
					rowLowerBound = Math.ceil((rowLowerBound + rowUpperBound) / 2);
					break;
				case 'R':
					columnLowerBound = Math.ceil((columnLowerBound + columnUpperBound) / 2);
					break;
				case 'L':
					columnUpperBound = Math.ceil((columnLowerBound + columnUpperBound) / 2);
					break;
				default:
					break;
				}
			}
			return (int) rowLowerBound * 8 + (int) columnLowerBound;
		}).boxed().collect(Collectors.toList());
		for (int row = 1; row < 127; row++) {
			for (int column = 0; column < 8; column++) {
				if (!seats.contains(row * 8 + column) && seats.contains(row * 8 + column + 1)
						&& seats.contains(row * 8 + column - 1)) {
					System.out.println(row * 8 + column);
				}
			}
		}
	}

}
