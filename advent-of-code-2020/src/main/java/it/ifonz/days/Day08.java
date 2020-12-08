package it.ifonz.days;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;

import it.ifonz.common.FileReader;

public class Day08 {

	public static void main(String[] args) throws IOException {
		var lines = FileReader.readLines("src/main/resources/d08.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> input) {
		var acc = 0;
		var executed = new HashSet<>();
		var goOn = true;
		int i = 0;
		while (goOn) {
			var instruction = input.get(i).split(" ");
			i += switch (instruction[0]) {
			case "acc": {
				acc += NumberUtils.createInteger(instruction[1]);
				yield 1;
			}
			case "jmp": {
				yield NumberUtils.createInteger(instruction[1]);
			}
			case "nop": {
				yield 1;
			}
			default:
				yield 1;
			};
			goOn = executed.add(i);
		}
		System.out.println(acc);
	}
	
	public static void part2(List<String> input) {
		var acc = 0;
		var executed = new HashSet<Integer>();
		var goOn = true;
		int i = 0;
		while (goOn && i < input.size()) {
			var instruction = input.get(i).split(" ");
			i += switch (instruction[0]) {
			case "acc": {
				acc += NumberUtils.createInteger(instruction[1]);
				yield 1;
			}
			case "jmp": {
				var newInput = new ArrayList<>(input);
				newInput.set(i, "nop "+instruction[1]);
				if (fixIt(newInput, i, acc, new HashSet<>(executed))) {
					goOn = false;
				}
				yield NumberUtils.createInteger(instruction[1]);
			}
			case "nop": {
				var newInput = new ArrayList<>(input);
				newInput.set(i, "jmp "+instruction[1]);
				if (fixIt(newInput, i, acc, new HashSet<>(executed))) {
					goOn = false;
				}

				yield 1;
			}
			default:
				yield 1;
			};
			goOn = executed.add(i);
		}
	}
	
	private static boolean fixIt(List<String> input, int i, int acc, Set<Integer> executed) {
		var goOn = true;
		while (goOn && i < input.size()) {
			var instruction = input.get(i).split(" ");
			i += switch (instruction[0]) {
			case "acc": {
				acc += NumberUtils.createInteger(instruction[1]);
				yield 1;
			}
			case "jmp": {
				yield NumberUtils.createInteger(instruction[1]);
			}
			case "nop": {
				yield 1;
			}
			default:
				yield 1;
			};
			goOn = executed.add(i);
		}
		if (goOn) {
			System.out.println(acc);
		}
		return goOn;
	}
	
}
