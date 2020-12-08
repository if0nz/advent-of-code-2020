package it.ifonz.days.optimized;

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
		int i = 0;
		while (executed.add(i)) {
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
		}
		System.out.println(acc);
	}
	
	public static void part2(List<String> input) {
		var acc = 0;
		var executed = new HashSet<Integer>();
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
				var newInput = prepare(input, i, "nop", null);
				if (fixIt(newInput, i, acc, new HashSet<>(executed))) {
					goOn = false;
				}
				yield NumberUtils.createInteger(instruction[1]);
			}
			case "nop": {
				var newInput = prepare(input, i, "jmp", instruction[1]);
				if (fixIt(newInput, i, acc, new HashSet<>(executed))) {
					goOn = false;
				}

				yield 1;
			}
			default:
				yield 1;
			};
			executed.add(i);
		}
	}

	private static ArrayList<String> prepare(List<String> input, int i, String newInstruction, String value) {
		var newInput = new ArrayList<>(input);
		newInput.set(i, newInstruction+" "+value);
		return newInput;
	}
	
	private static boolean fixIt(List<String> input, int i, int acc, Set<Integer> executed) {
		var size = input.size();
		while (i < size) {
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
			if(!executed.add(i))
				return false;
		}
		System.out.println(acc);
		return true;
	}
	
}
