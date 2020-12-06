package it.ifonz.days;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import it.ifonz.common.FileReader;

public class Day06 {

	public static void main(String[] args) throws IOException {
		var lines = FileReader.readLines("src/main/resources/d06.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> input) {
		var answers = new ArrayList<String>();
		var sb = new StringBuilder();
		for (var s : input) {
			if (!s.isBlank()) {
				sb.append(s);
			} else {
				answers.add(sb.toString());
				sb = new StringBuilder();
			}
		}
		answers.add(sb.toString());
		var s = answers.stream().mapToInt(a -> {
			var uniques = new HashSet<>();
			a.chars().forEach(c -> uniques.add(c));
			return uniques.size();
		}).sum();
		System.out.println(s);
	}

	public static void part2(List<String> input) {
		var answers = new ArrayList<String>();
		var sb = new StringBuilder();
		for (var s : input) {
			if (!s.isBlank()) {
				sb.append(s+",");
			} else {
				var tmp = sb.toString();
				answers.add(tmp.substring(0,tmp.length()-1));
				sb = new StringBuilder();
			}
		}
		var tmp = sb.toString();
		answers.add(tmp.substring(0,tmp.length()-1));
		var s = answers.stream().mapToLong(a -> {
			var singles = a.split(",");
			if (singles.length == 1) return a.length();
			var first = singles[0];
			return first.chars().filter(c -> {
				var flag = true;
				for (int i = 1; i < singles.length; i++) {
					flag = flag && singles[i].chars().anyMatch(c1 -> c1 == c);
				}
				return flag;
			}).count();
		}).sum();
		System.out.println(s);
	}
	
}
