package it.ifonz.days.optimized;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.ifonz.common.FileReader;

public class Day14 {

	// Idk how to optimize it
	public static void main(String[] args) throws IOException {
		var lines = FileReader.readLines("src/main/resources/d14.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> rows) {
		var mask = "";
		var mem = new HashMap<Integer, String>();
		for (var r : rows) {
			var val = r.split("= ")[1];
			if (r.contains("mask")) {
				mask = val;
			} else {
				var key = Integer.parseInt(r.split("\\[")[1].split("\\]")[0]);
				var n = StringUtils.leftPad(Integer.toBinaryString(Integer.parseInt(val)), 36, '0');
				var sb = new StringBuilder();
				for (var i = 0; i < n.length(); i++) {
					sb.append(mask.charAt(i) == 'X' ? n.charAt(i) : mask.charAt(i));
				}
				mem.put(key, sb.toString());
			}
		}
		var s = mem.values().stream().mapToLong(v -> Long.parseLong(v,2)).sum();
		System.out.println(s);
	}

	

	public static void part2(List<String> rows) {
		var mask = "";
		var mem = new HashMap<String, Integer>();
		for (var r : rows) {
			var val = r.split("= ")[1];
			if (r.contains("mask")) {
				mask = val;
			} else {
				var key = StringUtils.leftPad(Integer.toBinaryString(Integer.parseInt(r.split("\\[")[1].split("\\]")[0])),36,'0');
				var xIndexes = new ArrayList<Integer>();
				var lastIndex = 0;
				while (mask.indexOf('X',lastIndex) != -1) {
					xIndexes.add(mask.indexOf('X', lastIndex));
					lastIndex = mask.indexOf('X', lastIndex) +1;
				}
				var sb = new StringBuilder();
				for (var i = 0; i < key.length(); i++) {
					sb.append(mask.charAt(i) == 'X' ? 'X' : (mask.charAt(i) == '1' || key.charAt(i) == '1' ? '1' : '0'));
				}
				for (var i = 0; i < Math.pow(2, xIndexes.size()); i++) {
					var iBin = StringUtils.leftPad(Integer.toBinaryString(i), xIndexes.size(), '0');
					var floatedKey = new StringBuilder(sb.toString());
					for (var j = 0; j < iBin.length(); j++) {
						floatedKey.setCharAt(xIndexes.get(j), iBin.charAt(j));
					}
					mem.put(floatedKey.toString(), Integer.parseInt(val));
				}
			}
		}
		var s = mem.values().stream().mapToLong(v -> v).sum();
		System.out.println(s);
	}
	

}
