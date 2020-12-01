package it.ifonz.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import it.ifonz.common.FileReader;

public class Day01 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		var numbers = FileReader.readIntegerLines("src/main/resources/d01.txt");
		part1(numbers);
		part2(numbers);
	}
	
	public static void part1(List<Integer> numbers) throws URISyntaxException, IOException {
		for (var n1 : numbers) {
			for (var n2 : numbers) {
				if (n1 + n2 == 2020) {
					System.out.println(n1*n2);
					return;
				}
			}
		}
	}
	
	public static void part2(List<Integer> numbers) throws URISyntaxException, IOException {
		for (var n1 : numbers) {
			for (var n2 : numbers) {
				for (var n3 : numbers)
				if (n1 + n2 + n3 == 2020) {
					System.out.println(n1*n2*n3);
					return;
				}
			}
		}
	}
	
}
