package it.ifonz.days.optimized;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import it.ifonz.common.FileReader;

public class Day01 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		var numbers = FileReader.readIntegerLines("src/main/resources/d01.txt");
		Collections.sort(numbers);
		part1(numbers);
		part2(numbers);
	}

	public static void part1(List<Integer> numbers) throws URISyntaxException, IOException {
		for (int i = 0; i < numbers.size() / 2; i++) {
			for (int j = numbers.size() - 1; j >= numbers.size() / 2; j--) {
				if (numbers.get(i) + numbers.get(j) == 2020) {
					System.out.println(numbers.get(i) * numbers.get(j));
					return;
				} else if (numbers.get(i) + numbers.get(j) < 2020) {
					break;
				}
			}
		}
	}

	public static void part2(List<Integer> numbers) {
		for (int i = 0; i < numbers.size() - 2; i++) {
			var a = numbers.get(i);
			var start = i + 1;
			var end = numbers.size() - 1;
			while (start < end) {
				var b = numbers.get(start);
				var c = numbers.get(end);
				if (a + b + c == 2020) {
					System.out.println(a * b * c);
					return;
				} else if (a + b + c > 2020) {
					end--;
				} else {
					start++;
				}
			}
		}
	}

}
