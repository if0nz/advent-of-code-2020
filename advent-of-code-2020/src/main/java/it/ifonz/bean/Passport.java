package it.ifonz.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;

public class Passport {

	private static String[] accepted = new String[] { "amb", "blu", "brn", "gry", "grn", "hzl", "oth" };

	int byr;
	int iyr;
	int eyr;
	int hgt;
	String hgtUnit;
	String hcl;
	String ecl;
	String pid;
	int cid;

	Passport() {
		hcl = "";
		pid = "";
	}

	public static Passport parsePassport(String passport) {
		var p = new Passport();
		var fields = passport.split(" ");
		for (var f : fields) {
			var kv = f.split(":");
			switch (kv[0]) {
			case "byr":
				p.byr = Integer.parseInt(kv[1]);
				break;
			case "iyr":
				p.iyr = Integer.parseInt(kv[1]);
				break;
			case "eyr":
				p.eyr = Integer.parseInt(kv[1]);
				break;
			case "hgt": {
				var unit = kv[1].substring(kv[1].length() - 2);
				p.hgtUnit = !NumberUtils.isCreatable(unit) ? unit : "";
				p.hgt = Integer.parseInt(!p.hgtUnit.isBlank() ? kv[1].substring(0, kv[1].length() - 2) : kv[1]);
				break;
			}
			case "hcl":
				p.hcl = kv[1];
				break;
			case "ecl": {
				p.ecl = kv[1];
				break;
			}
			case "pid": {
				p.pid = kv[1];
				break;
			}
			}
		}
		return p;
	}

	public static List<Passport> parsePassports(List<String> lines) {
		var passports = new ArrayList<String>();
		var sb = new StringBuilder();
		for (var s : lines) {
			if (!s.isBlank()) {
				sb.append(s + " ");
			} else {
				passports.add(sb.toString());
				sb = new StringBuilder();
			}
		}
		passports.add(sb.toString());

		return passports.stream().map(passport -> parsePassport(passport)).collect(Collectors.toList());
	}

	public boolean isValid() {

		return (byr >= 1920 && byr <= 2002) && (iyr >= 2010 && iyr <= 2020) && (eyr >= 2020 && eyr <= 2030)
				&& (hgt >= 150 && hgt <= 193 && "cm".equals(hgtUnit)
						|| (hgt >= 59 && hgt <= 76 && "in".equals(hgtUnit)))
				&& (hcl.length() == 7 && hcl.charAt(0) == '#' && NumberUtils.isCreatable("0x" + hcl.substring(1)))
				&& (Arrays.stream(accepted).anyMatch(a -> a.equals(ecl))) && pid.length() == 9
				&& NumberUtils.isParsable(pid);
	}

}
