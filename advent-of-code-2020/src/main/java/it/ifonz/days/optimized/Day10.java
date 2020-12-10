package it.ifonz.days.optimized;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import it.ifonz.common.FileReader;

public class Day10 {

	public static void main(String[] args) throws IOException {
		var lines = FileReader.readIntegerLines("src/main/resources/d10.txt");
		part1(new ArrayList<Integer>(lines));
		part2(new ArrayList<Integer>(lines));
	}

	public static void part1(List<Integer> numbers) {
		Collections.sort(numbers);
		numbers.add(numbers.stream().mapToInt(x->x).max().getAsInt()+3);
		var outlet = 0;
		var diff1 = new ArrayList<>();
		var diff3 = new ArrayList<>();
		for (var n : numbers) {
			if (n-outlet == 1) {
				outlet = n;
				diff1.add(n);
			}
			else if (n-outlet == 3) {
				outlet = n;
				diff3.add(n);
			}
		}
		System.out.println(diff1.size()*diff3.size());
	}
	
	public static void part2(List<Integer> numbers) {
		numbers.add(0);
		var device = numbers.stream().mapToInt(x->x).max().getAsInt()+3;
		numbers.add(device);
		Collections.sort(numbers);
		var cache = new HashMap<Integer, Long>();
		System.out.println(pathsBetween(numbers, 0, device, cache, 0));
	}
	
	private static long pathsBetween(List<Integer> numbers, int source, int destination, HashMap<Integer, Long> cache, int index) {
		if (source == destination)
			return 1;
		if (cache.get(source) != null) return cache.get(source);
		var paths = 0l;
		for (int i = 1; i <= 3; i++) {
			var j = 0;
			while (++j <= i && numbers.get(index+j) != source+i) {}
			if (index +j < numbers.size() && numbers.get(index+j) == source+i)
				paths += pathsBetween(numbers, source+i, destination, cache, index+(j-1));
		}
		cache.put(source, paths);
		return paths;
	}
	
	
}
