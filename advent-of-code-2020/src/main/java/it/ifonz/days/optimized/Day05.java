package it.ifonz.days.optimized;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.ifonz.common.FileReader;

public class Day05 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		var lines = FileReader.readLines("src/main/resources/d05.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> input) {
		var max = input.stream().mapToInt(seat -> {
			seat = seat.replace('F', '0').replace('L', '0').replace('B', '1').replace('R', '1');
			return Integer.parseInt(seat, 2);
		}).max().getAsInt();
		System.out.println(max);
	}

	public static void part2(List<String> input) {
		var seats = input.stream().mapToInt(seat -> {
			seat = seat.replace('F', '0').replace('L', '0').replace('B', '1').replace('R', '1');
			return Integer.parseInt(seat, 2);
		}).boxed().collect(Collectors.toList());
		System.out.println(IntStream.range(5, 2032).filter(i -> !seats.contains(i) && seats.contains(i-1) && seats.contains(i+1)).findFirst().getAsInt());
	}

}
