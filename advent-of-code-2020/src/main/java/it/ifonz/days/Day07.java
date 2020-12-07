package it.ifonz.days;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import it.ifonz.common.FileReader;

public class Day07 {

	public static void main(String[] args) throws IOException {
		var lines = FileReader.readLines("src/main/resources/d07.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> input) {
		var bags = new HashMap<String, List<String>>();
		for (var rule : input) {
			var tokens = rule.split(" ");
			var container = tokens[0]+" "+tokens[1];
			var contained = new ArrayList<String>();
			if (!"no".equals(tokens[4])) {
				for (int i = 5; i < tokens.length; i+=4) {
					contained.add(tokens[i]+" "+tokens[i+1]);
				}
			}
			bags.put(container, contained);
		}
		System.out.println(findContainers(bags, new HashSet<String>(Arrays.asList("shiny gold"))).size());
	}
	
	private static Set<String> findContainers(HashMap<String, List<String>> bags, Set<String> colors) {
		var containers = bags.keySet().stream().filter(bag -> colors.stream().anyMatch(color -> bags.get(bag).contains(color))).collect(Collectors.toSet());
		if (!containers.isEmpty())
			containers.addAll(findContainers(bags, containers));
		return containers;
	}

	public static void part2(List<String> input) {
		var bags = new HashMap<String, List<Bag>>();
		for (var rule : input) {
			var tokens = rule.split(" ");
			var container = tokens[0]+" "+tokens[1];
			var contained = new ArrayList<Bag>();
			if (!"no".equals(tokens[4])) {
				for (int i = 5; i < tokens.length; i+=4) {
					contained.add(new Bag(tokens[i]+" "+tokens[i+1], Integer.parseInt(tokens[i-1])));
				}
			}
			bags.put(container, contained);
		}
		System.out.println(containedBags(bags, "shiny gold"));
	}
	
	public static long containedBags(HashMap<String, List<Bag>> bags, String color) {
		var root = bags.entrySet().stream().filter(b -> b.getKey().equals(color)).findAny().get();
		var c = root.getValue().isEmpty() ? 0 : root.getValue().stream().mapToLong(bag -> bag.contained+bag.contained * containedBags(bags, bag.color)).sum();
		return c;
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
