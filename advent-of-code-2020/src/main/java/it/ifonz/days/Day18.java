package it.ifonz.days;

import java.io.IOException;
import java.util.List;

import it.ifonz.common.FileReader;

public class Day18 {

	public static void main(String[] args) throws IOException {
		var lines = FileReader.readLines("src/main/resources/d18.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> lines) {
		var s = lines.stream().mapToLong(expression -> {
			expression = "+ " + expression.replaceAll("\\(", "\\( ").replaceAll("\\)", " \\)"); // trollface
			var tokens = expression.split(" ");
			// init
			var i = 0;
			var r = 0l;
			while (i < tokens.length) {
				if (tokens[i].equals("+")) {
					i++;
					if (tokens[i].equals("(")) {
						var mem = parenthesis(i + 1, expression, tokens);
						i = mem.i;
						r += mem.r;
					} else {
						r += Integer.parseInt(tokens[i]);
					}
				} else if (tokens[i].equals("*")) {
					i++;
					if (tokens[i].equals("(")) {
						var mem = parenthesis(i + 1, expression, tokens);
						i = mem.i;
						r *= mem.r;
					} else {
						r *= Integer.parseInt(tokens[i]);
					}
				} else {
					var mem = parenthesis(i + 1, expression, tokens);
					i = mem.i;
					r += mem.r;
				}
				i++;
			}
			return r;
		}).sum();
		System.out.println(s);
	}

	public static void part2(List<String> lines) {
		var s = lines.stream().mapToLong(expression -> {
			expression = expression.replaceAll("\\(", "\\( ").replaceAll("\\)", " \\)"); // trollface
			var tokens = expression.split(" ");
			// init
			var i = 0;
			var r = 0l;
			while (i < tokens.length) {
				if (tokens[i].equals("+")) {
					i++;
					if (tokens[i].equals("(")) {
						var mem = parenthesis2(i + 1, expression, tokens);
						i = mem.i;
						r += mem.r;
					} else {
						r += Integer.parseInt(tokens[i]);
					}
				} else if (tokens[i].equals("*")) {
					i++;
					if (tokens[i].equals("(")) {
						var mem = parenthesis2(i + 1, expression, tokens);
						i = mem.i;
						var temp = mem.r;
						while (i+1 < tokens.length && tokens[i+1].equals("+")) {
							mem = add(i+2, expression, tokens);
							i = mem.i;
							temp += mem.r;
						}
						r *= temp;
					} else if (i+1 < tokens.length && tokens[i + 1].equals("+")) {
						var mem = add(i, expression, tokens);
						r *= mem.r;
						i = mem.i;
					}

					else {
						r *= Integer.parseInt(tokens[i]);
					}
				} else if (tokens[i].equals("(")){
					var mem = parenthesis2(i + 1, expression, tokens);
					i = mem.i;
					r += mem.r;
				} else {
					r = Long.parseLong(tokens[i]);
				}
				i++;
			}
			return r;
		}).sum();
		System.out.println(s);
	}

	private static Memory parenthesis(int i, String expression, String[] tokens) {
		var r = 0l;
		while (i < tokens.length && !tokens[i].equals(")")) {
			if (tokens[i].equals("+")) {
				i++;
				if (tokens[i].equals("(")) {
					var mem = parenthesis(i + 1, expression, tokens);
					i = mem.i;
					r += mem.r;
				} else {
					r += Integer.parseInt(tokens[i]);
				}
			} else if (tokens[i].equals("*")) {
				i++;
				if (tokens[i].equals("(")) {
					var mem = parenthesis(i + 1, expression, tokens);
					i = mem.i;
					r *= mem.r;
				} else {
					r *= Integer.parseInt(tokens[i]);
				}
			} else if (tokens[i].equals("(")) {
				var mem = parenthesis(i + 1, expression, tokens);
				i = mem.i;
				r += mem.r;
			} else {
				r = Long.parseLong(tokens[i]);
			}
			i++;
		}
		var mem = new Memory();
		mem.r = r;
		mem.i = i;
		return mem;
	}

	private static Memory parenthesis2(int i, String expression, String[] tokens) {
		var r = 0l; // init result
		while (i < tokens.length && !tokens[i].equals(")")) { // stops eval at the end of the expression or when it mets a )
			if (tokens[i].equals("+")) { // if it's an add
				i++;
				if (tokens[i].equals("(")) { // next char is a (
					var mem = parenthesis2(i + 1, expression, tokens); // eval parenthesis sub-exp
					i = mem.i;
					r += mem.r;
				} else {
					r += Integer.parseInt(tokens[i]); // else just adds the the val
				}
			} else if (tokens[i].equals("*")) { // else if it's a multiplication
				i++;
				if (tokens[i].equals("(")) {
					var mem = parenthesis2(i + 1, expression, tokens);
					i = mem.i;
					var temp = mem.r;
					while (i+1 < tokens.length && tokens[i+1].equals("+")) {
						mem = add(i+2, expression, tokens);
						i = mem.i;
						temp += mem.r;
					}
					r *= temp;
				}  else if (i+1 < tokens.length && tokens[i + 1].equals("+")) { // if next operation is an add
					var mem = add(i, expression, tokens); // eval the add
					r *= mem.r;
					i = mem.i;
				}else {
					r *= Integer.parseInt(tokens[i]);
				}
			} else if (tokens[i].equals("(")) {
				var mem = parenthesis2(i + 1, expression, tokens);
				i = mem.i;
				r += mem.r;
			} else {
				r = Long.parseLong(tokens[i]); // actually inits the sub-exp with the very first available number
			}
			i++;
		}
		var mem = new Memory();
		mem.r = r;
		mem.i = i;
		return mem;
	}
	
	private static Memory add(int i, String expression, String[] tokens) {
		var r = 0l;
		while (i < tokens.length && !tokens[i].equals(")") && !tokens[i].equals("*")) { // it stops when it mets a multiplication or parenthesis' end
			if (tokens[i].equals("+")) {
				i++;
				if (tokens[i].equals("(")) {
					var mem = parenthesis2(i + 1, expression, tokens);
					i = mem.i;
					r += mem.r;
				} else {
					r += Integer.parseInt(tokens[i]);
				}
			} 
			else if (tokens[i].equals("(")) {
				var mem = parenthesis2(i + 1, expression, tokens);
				i = mem.i;
				r += mem.r;
			}
			else {
				r = Long.parseLong(tokens[i]);
			}
			i++;
		}
		var mem = new Memory();
		mem.r = r;
		mem.i = i < tokens.length && (tokens[i].equals("*") || tokens[i].equals(")"))? i-1:i;
		return mem;
	}

	// it stores parenthesis and add (p2) results and pointer's location after sub-expression's eval
	private static class Memory {
		public long r;
		public int i;
	}
}
