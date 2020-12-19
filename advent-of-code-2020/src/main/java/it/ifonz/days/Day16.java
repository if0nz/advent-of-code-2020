package it.ifonz.days;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import it.ifonz.common.FileReader;

public class Day16 {

	public static void main(String[] args) throws IOException {
		var lines = FileReader.readLines("src/main/resources/d16.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> lines) {
		var ranges = new ArrayList<String>();
		var i = 0;
		while (!lines.get(i).isBlank()) {
			var split = lines.get(i).split(":")[1].split(" ");
			ranges.add(split[1]);
			ranges.add(split[3]);
			i++;
		}
		i += 5;
		var s = 0;
		for (; i < lines.size(); i++) {
			var numbers = Arrays.stream(lines.get(i).split(",")).mapToInt(Integer::parseInt).boxed()
					.collect(Collectors.toList());
			s += numbers.stream().filter(n -> {
				var correct = false;
				for (var range : ranges) {
					if (n >= Integer.parseInt(range.split("-")[0]) && n <= Integer.parseInt(range.split("-")[1])) {
						correct = true;
						break;
					}
				}
				return !correct;
			}).mapToInt(x -> x).sum();
		}
		System.out.println(s);
	}

	public static void part2(List<String> lines) {
		var ranges = new ArrayList<String>();
		var i = 0;
		while (!lines.get(i).isBlank()) {
			var split = lines.get(i).split(":")[1].split(" ");
			ranges.add(split[1]);
			ranges.add(split[3]);
			i++;
		}
		i += 5;
		var myTicket = lines.get(i-3).split(",");
		lines = lines.subList(i, lines.size());
		var tickets = lines.stream().filter(line -> {
			var numbers = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).boxed()
					.collect(Collectors.toList());
			return numbers.stream().filter(n -> {
				var correct = false;
				for (var range : ranges) {
					if (n >= Integer.parseInt(range.split("-")[0]) && n <= Integer.parseInt(range.split("-")[1])) {
						correct = true;
						break;
					}
				}
				return !correct;
			}).findAny().isEmpty();
		}).collect(Collectors.toList());
		var possibleAssignments = new List[ranges.size() / 2]; // index = field name, value = every column that fits the
																// field
		for (var j = 0; j < possibleAssignments.length; j++) {
			possibleAssignments[j] = new ArrayList<Integer>();
			var min1 = Integer.parseInt(ranges.get(2 * j).split("-")[0]);
			var max1 = Integer.parseInt(ranges.get(2 * j).split("-")[1]);
			var min2 = Integer.parseInt(ranges.get(2 * j + 1).split("-")[0]);
			var max2 = Integer.parseInt(ranges.get(2 * j + 1).split("-")[1]);
			var candidates = IntStream.range(0, tickets.get(0).split(",").length)
					.filter(colIndex -> tickets.stream()
							.filter(ticket -> 
							!(Integer.parseInt(ticket.split(",")[colIndex]) >= min1
									&& Integer.parseInt(ticket.split(",")[colIndex]) <= max1

									|| Integer.parseInt(ticket.split(",")[colIndex]) >= min2
											&& Integer.parseInt(ticket.split(",")[colIndex]) <= max2))
							.findAny().isEmpty())
					.boxed().collect(Collectors.toList());
			possibleAssignments[j] = candidates;
		}
		var fieldAssignemtns = new Integer[ranges.size()/2];
		var cnt = 0;
		// sudoku naive solve lol
		while (cnt < ranges.size()/2) {
			var field = IntStream.range(0, ranges.size()/2).filter(j -> possibleAssignments[j].size() == 1).findAny().getAsInt();
			fieldAssignemtns[field] = (Integer) possibleAssignments[field].get(0);
			for (var pa : possibleAssignments) pa.remove(fieldAssignemtns[field]);
			cnt++;
		}
		var mul = 1l;
		for (var j = 0; j < 6; j++) {
			mul*=Integer.parseInt(myTicket[fieldAssignemtns[j]]);
		}
		System.out.println(mul);
	}

}
