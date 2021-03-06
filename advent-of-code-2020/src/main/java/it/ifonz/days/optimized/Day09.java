package it.ifonz.days.optimized;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.List;

import it.ifonz.common.FileReader;

public class Day09 {

	public static void main(String[] args) throws IOException {
		var lines = FileReader.readLongLines("src/main/resources/d09.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<Long> numbers) {
		
		System.out.println(findWeak(numbers));
	}

	private static Long findWeak(List<Long> numbers) {
		var queue = new ArrayDeque<Long>(numbers);
		var preamble = new ArrayDeque<Long>();
		for (int i = 0; i < 25; i++) {
			preamble.add(queue.pop());
		}
		for (var n : queue) {
			var found = false;
			for (var n1 : preamble) {
				for (var n2 : preamble) {
					if (n1 + n2 == n) {
						found = true;
					}
				}
			}
			if (found) {
				preamble.pop();
				preamble.add(n);
			} else {
				return n;
			}
		}
		return -1l;
	}
	
	public static void part2(List<Long> numbers) {
		var weak = findWeak(numbers);
		var contiguous = new ArrayDeque<Long>();
		int i = 0;
		var sum = 0;
		while (sum != weak) {
			while (sum < weak) {
				var n = numbers.get(i++);
				contiguous.add(n);
				sum+=n;
			}
			if (sum > weak) {
				sum-=contiguous.pop();
			}
		}
		System.out.println(contiguous.stream().mapToLong(x->x).min().getAsLong()+contiguous.stream().mapToLong(x->x).max().getAsLong());
	}
	
}
