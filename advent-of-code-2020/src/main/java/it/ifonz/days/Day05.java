package it.ifonz.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import it.ifonz.common.FileReader;

public class Day05 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		var lines = FileReader.readLines("src/main/resources/d05.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> input) {
		var max = input.stream().mapToDouble(seat -> {
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
					default: break;
				}
			}
			return rowLowerBound * 8 + 5;
		}).max().getAsDouble();
		System.out.println(max);
	}

	public static void part2(List<String> input) {
		var seats = input.stream().map(seat -> {
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
					default: break;
				}
			}
			return new Seat((int)rowLowerBound, (int)columnLowerBound, (int)rowLowerBound * 8 + (int)columnLowerBound);
		}).collect(Collectors.toList());
		for (int row = 1; row < 127; row++) {
			for (int column = 0; column < 8; column++) {
				if (!seats.contains(new Seat(0,0,row*8+column)) &&
						seats.contains(new Seat(0,0,row*8+column+1)) &&
						seats.contains(new Seat(0,0,row*8+column-1))) {
					System.out.println(row*8+column);
				}
			}
		}
	}
	
	static class Seat {
		
		public int row, column, id;

		public Seat(int row, int column, int id) {
			super();
			this.row = row;
			this.column = column;
			this.id = id;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Seat other = (Seat) obj;
			return id == other.id;
		}
		
		
	}

}
