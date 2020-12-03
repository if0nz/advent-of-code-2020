package it.ifonz.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import it.ifonz.common.FileReader;

public class Day02 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		var lines = FileReader.readLines("src/main/resources/d02.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> passwords) throws URISyntaxException, IOException {
		var r = passwords.stream().filter(p -> {
			var tokens = p.split(" ");
			var limits = tokens[0].split("-");
			var min = Integer.parseInt(limits[0]);
			var max = Integer.parseInt(limits[1]);
			var c = tokens[1].charAt(0);
			var pwd = tokens[2];
			var count = pwd.chars().filter(ch -> ch == c).count();
			return count >= min && count <= max;
		}).count();
		System.out.println(r);
	}

	public static void part2(List<String> passwords) {
		var r = passwords.stream().filter(p -> {
			var tokens = p.split(" ");
			var limits = tokens[0].split("-");
			var first = Integer.parseInt(limits[0])-1;
			var second = Integer.parseInt(limits[1])-1;
			var c = tokens[1].charAt(0);
			var pwd = tokens[2];
			var count = (pwd.charAt(first) == c ? 1 : 0) + (pwd.charAt(second) == c ? 1 : 0);
			return count == 1;
		}).count();
		System.out.println(r);
	}

}
