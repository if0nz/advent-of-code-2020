package it.ifonz.days.optimized;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import it.ifonz.common.FileReader;

public class Day07 {

	public static void main(String[] args) throws IOException {
		var lines = FileReader.readLines("src/main/resources/d07.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> input) {
		var bagContainers = new HashMap<String, List<String>>(); // for each color, I store the bags which contain them (thx LeppyR64)
		for (var rule : input) {
			var tokens = rule.split(" ");
			var container = tokens[0]+" "+tokens[1];
			for (int i = 5; i < tokens.length; i+=4) {
				var contained = tokens[i]+" "+tokens[i+1];
				var bag = bagContainers.get(contained);
				if (bag == null) {
					bag = new ArrayList<>();
					bagContainers.put(contained, bag);
				}
				bag.add(container);
			}
		}
		var queue = new ArrayDeque<>(bagContainers.get("shiny gold"));
		var found = new HashSet<String>();
		while (!queue.isEmpty()) {
			var bag = queue.pop();
			found.add(bag);
			var bags = bagContainers.get(bag);
			if (bags != null)
				queue.addAll(bags);
		}
		System.out.println(found.size());
	}
	
	public static void part2(List<String> input) {
		var bags = new HashMap<String, List<Bag>>();
		for (var rule : input) {
			var tokens = rule.split(" ");
			var container = tokens[0]+" "+tokens[1];
			var contained = new ArrayList<Bag>();
			if (tokens.length > 7) {
				for (int i = 5; i < tokens.length; i+=4) {
					contained.add(new Bag(tokens[i]+" "+tokens[i+1], Integer.parseInt(tokens[i-1])));
				}
			}
			bags.put(container, contained);
		}
		System.out.println(containedBags(bags, "shiny gold"));
	}
	
	public static long containedBags(HashMap<String, List<Bag>> bags, String color) {
		var root = bags.get(color);
		return root == null ? 0 : root.stream().mapToLong(bag -> bag.contained*(1+containedBags(bags, bag.color))).sum();
	}
	
	static class Bag {
		String color;
		int contained;
		public Bag(String color, int contained) {
			super();
			this.color = color;
			this.contained = contained;
		}
		
	}
	
}
