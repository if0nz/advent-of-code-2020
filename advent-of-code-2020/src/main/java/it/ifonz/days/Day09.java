package it.ifonz.days;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import it.ifonz.common.FileReader;

public class Day09 {

	public static void main(String[] args) throws IOException {
		var lines = FileReader.readLongLines("src/main/resources/d09.txt");
//		part1(lines);
		part2(lines);
	}

	public static void part1(List<Long> numbers) {
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
				System.out.println(n);
				break;
			}
		}
	}
	
	public static void part2(List<Long> numbers) {
		var queue = new ArrayDeque<Long>(numbers);
		var preamble = new ArrayDeque<Long>();
		var weak = 0l;
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
				weak = n;
				break;
			}
		}
		var contiguous = new ArrayList<Long>();
		int i = 0;
		while (contiguous.stream().mapToLong(x->x).sum() != weak) {
			contiguous = new ArrayList<Long>();
			int j = i;
			while (contiguous.stream().mapToLong(x->x).sum() < weak) {
				contiguous.add(numbers.get(j++));
			}
			i++;
		}
		System.out.println(contiguous.stream().mapToLong(x->x).min().getAsLong()+contiguous.stream().mapToLong(x->x).max().getAsLong());
	}
	
}
