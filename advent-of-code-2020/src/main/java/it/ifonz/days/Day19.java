package it.ifonz.days;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import it.ifonz.common.FileReader;

public class Day19 {

	public static void main(String[] args) throws IOException {
		var lines = FileReader.readLines("src/main/resources/d19.txt");
		var begin = Instant.now().toEpochMilli();
		part1(lines);
		part2(lines);
		System.out.println(Instant.now().toEpochMilli() - begin);

	}

	public static void part1(List<String> lines) {
		var rules = new ArrayList<String>();
		for (var i = 0; i < lines.size(); i++)
			rules.add("");
		List<String> messages = new ArrayList<>();
		var i = -1;
		var t = "";
		while (!(t = lines.get(++i)).isEmpty()) {
			var split = t.split(": ");
			rules.set(Integer.parseInt(split[0]), split[1]);
		}
		messages = lines.subList(i + 1, lines.size());
		var cnt = messages.stream().filter(message -> {
			var tmp = check(rules, 0, message, 0);
			return tmp.check && tmp.index == message.length();
		}).count();
		System.out.println(cnt);
	}

	private static Memory check(List<String> rules, int subRule, String message, int index) {
		var rule = rules.get(subRule);
		var mem = new Memory();
		if (rule.charAt(0) == '"') {
			mem.check = index < message.length() && rule.charAt(1) == message.charAt(index);
			mem.index = index + 1;
			return mem;
		}
		var tokens = rule.split(" ");
		var orIndex = IntStream.range(0, tokens.length).filter(i -> tokens[i].equals("|")).findFirst().orElse(-1);
		var i = 0;
		var checkRule = true;
		var localIndex = index;
		if (orIndex > -1) {
			for (; checkRule && i < orIndex; i++) {
				var temp = check(rules, Integer.parseInt(tokens[i]), message, localIndex);
				checkRule = checkRule && temp.check;
				localIndex = temp.index;
			}
			if (checkRule) {
				mem.check = true;
				mem.index = localIndex;
				return mem;
			}
			i = orIndex + 1;
		}
		localIndex = index;
		checkRule = true;
		for (; checkRule && i < tokens.length; i++) {
			var temp = check(rules, Integer.parseInt(tokens[i]), message, localIndex);
			checkRule = checkRule && temp.check;
			localIndex = temp.index;
		}
		if (checkRule) {
			mem.check = true;
			mem.index = localIndex;
			return mem;
		}
		mem.check = false;
		return mem;
	}

	public static void part2(List<String> lines) {
		var rules = new ArrayList<String>();
		for (var i = 0; i < lines.size() * 2; i++)
			rules.add("");
		List<String> messages = new ArrayList<>();
		var i = -1;
		var t = "";
		while (!(t = lines.get(++i)).isEmpty()) {
			var split = t.split(": ");
			rules.set(Integer.parseInt(split[0]), split[1]);
		}
		// I actually don't read these rules from the array
		rules.set(8, "42 | 42 8");
		rules.set(11, "42 31 | 42 11 31");
		messages = lines.subList(i + 1, lines.size());
		var cnt = messages.stream().filter(message -> {
			var tmp = checkStack(rules, 0, message, 0, 0);
			return tmp.check && tmp.index == message.length();
		}).count();
		System.out.println(cnt);
	}

	private static Memory checkStack(List<String> rules, int subRule, String message, int index, int stacked8) {
		if (subRule == 8) {
			return check8(rules, message, index);
		}
		if (subRule == 11) {
			return check11(rules, message, index, stacked8);
		}
		var rule = rules.get(subRule);
		var mem = new Memory();
		if (rule.charAt(0) == '"') {
			mem.check = index < message.length() && rule.charAt(1) == message.charAt(index);
			mem.index = index + 1;
			return mem;
		}
		var tokens = rule.split(" ");
		var orIndex = IntStream.range(0, tokens.length).filter(i -> tokens[i].equals("|")).findFirst().orElse(-1);
		var i = 0;
		var checkRule = true;
		var localIndex = index;
		if (orIndex > -1) {
			var temp = new Memory();
			for (; checkRule && i < orIndex; i++) {
				temp = checkStack(rules, Integer.parseInt(tokens[i]), message, localIndex, temp.cnt8);
				checkRule = checkRule && temp.check;
				localIndex = temp.index;
			}
			if (checkRule) {
				mem.check = true;
				mem.index = localIndex;
				return mem;
			}
			i = orIndex + 1;
		}
		localIndex = index;
		checkRule = true;
		var temp = new Memory();
		for (; checkRule && i < tokens.length; i++) {
			temp = checkStack(rules, Integer.parseInt(tokens[i]), message, localIndex, temp.cnt8);
			checkRule = checkRule && temp.check;
			localIndex = temp.index;
		}
		if (checkRule) {
			mem.check = true;
			mem.index = localIndex;
			return mem;
		}
		mem.check = false;
		return mem;
	}

	// 42+
	private static Memory check8(List<String> rules, String message, int index) {
		var cnt = -1;
		Memory tmp = new Memory();
		tmp.index = index;
		var localIndex = 0;
		do {
			cnt++;
			localIndex = tmp.index;
			tmp = check(rules, 42, message, localIndex);
		} while (tmp.check);
		if (cnt > 0) {
			tmp.check = true;
			tmp.index = localIndex;
			tmp.cnt8 = cnt;
			return tmp;
		}
		tmp = new Memory();
		tmp.check = false;
		tmp.index = index;
		return tmp;
	}

	// 42^n 31^n, consumes the "42" produced by check8
	private static Memory check11(List<String> rules, String message, int index, int stacked8) {
		Memory tmp = new Memory();
		tmp.index = index;
		var localIndex = tmp.index;
		tmp = check8(rules, message, localIndex);
		var cnt42 = tmp.cnt8 + stacked8;
		var cnt31 = -1;
		localIndex = tmp.index;
		tmp = new Memory();
		tmp.index = localIndex;
		do {
			cnt31++;
			localIndex = tmp.index;
			tmp = check(rules, 31, message, localIndex);
		} while (tmp.check && cnt31 < cnt42);
		if (cnt31 > 0 && cnt42 > 1 && cnt31 < cnt42) {
			tmp.check = true;
			tmp.index = localIndex;
			return tmp;
		}
		tmp = new Memory();
		tmp.check = false;
		tmp.index = index;
		return tmp;
	}

	private static class Memory {

		public int cnt8 = 0;
		public int index;
		public boolean check;

	}
}
