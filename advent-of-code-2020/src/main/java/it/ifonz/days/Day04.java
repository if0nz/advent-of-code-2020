package it.ifonz.days;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import it.ifonz.common.FileReader;

public class Day04 {

	public static void main(String[] args) throws URISyntaxException, IOException {
		var lines = FileReader.readLines("src/main/resources/d04.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> input) {
		var passports = new ArrayList<String>();
		var sb = new StringBuilder();
		for (var s : input) {
			if (!s.isBlank()) {
				sb.append(s + " ");
			} else {
				passports.add(sb.toString());
				sb = new StringBuilder();
			}
		}
		System.out.println(passports.stream().filter(p -> {
			var tokens = p.split(" ");
			return Arrays.stream(tokens).filter(f -> !f.substring(0, 3).equals("cid")).count() == 7;
		}).count());
	}

	public static void part2(List<String> input) {
		var passports = new ArrayList<String>();
		var sb = new StringBuilder();
		for (var s : input) {
			if (!s.isBlank()) {
				sb.append(s + " ");
			} else {
				passports.add(sb.toString());
				sb = new StringBuilder();
			}
		}
		passports.add(sb.toString());

		var c = passports.stream().filter(passport -> {
			var fields = passport.split(" ");
			var valid = 0;
			for (var f : fields) {
				var kv = f.split(":");
				valid += switch (kv[0]) {
				case "byr":
					var byr = Integer.parseInt(kv[1]);
					yield (byr >= 1920 && byr <= 2002) ? 1 : 0 ;
				case "iyr":
					var yir = Integer.parseInt(kv[1]);
					yield (yir >= 2010 && yir <= 2020) ? 1 : 0 ;
				case "eyr":
					var eyr = Integer.parseInt(kv[1]);
					yield (eyr >= 2020 && eyr <= 2030) ? 1 : 0 ;
				case "hgt": {
					if (kv[1].length() < 4) yield 0;
					var unit = kv[1].substring(kv[1].length()-2);
					var hgt = Integer.parseInt(kv[1].substring(0,kv[1].length()-2));
					if ("cm".equals(unit)) {
						yield (hgt >= 150 && hgt <= 193) ? 1 : 0;
					} else if ("in".equals(unit)) {
						yield (hgt >= 59 && hgt <= 76) ? 1 : 0;
					}
					yield 0;
				}
				case "hcl":
					if (kv[1].charAt(0) == '#' && kv[1].length() == 7) {
						yield NumberUtils.isCreatable("0x"+kv[1].substring(1)) ? 1 : 0;
					}
					yield 0;
				case "ecl": {
					var accepted = new String[] {"amb","blu","brn","gry","grn","hzl","oth"};
					yield (Arrays.stream(accepted).anyMatch(a -> a.equals(kv[1]))) ? 1 : 0;
				}
				case "pid": {
					if (kv[1].length() == 9) {
						yield NumberUtils.isParsable(kv[1]) ? 1 : 0;
					}
					yield 0;
				}
				default:
					yield 0;
				};
			}
			return valid == 7;
		}).count();
		System.out.println(c);
	}

}
