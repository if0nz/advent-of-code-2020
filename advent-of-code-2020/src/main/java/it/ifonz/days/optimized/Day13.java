package it.ifonz.days.optimized;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import it.ifonz.common.FileReader;

public class Day13 {

	// Idk how to optimize it
	public static void main(String[] args) throws IOException {
		var lines = FileReader.readLines("src/main/resources/d13.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> rows) {
		var time = Integer.parseInt(rows.get(0));
		var ids = rows.get(1).split(",");
		var nextDepart = new HashMap<Integer, Integer>();
		Arrays.stream(ids).filter(id -> !"x".equals(id)).forEach(id -> nextDepart.put(Integer.parseInt(id), Integer.parseInt(id)*(1+time/Integer.parseInt(id))));
		var e = nextDepart.entrySet().stream().min((i1, i2) -> i1.getValue() - i2.getValue()).get();
		System.out.println(e.getKey()*(e.getValue()-time));
	}

	

	public static void part2(List<String> rows) {
		var ids = rows.get(1).split(",");
		var equations = new HashMap<Long, Long>();
		
		for (var i = 0; i < ids.length; i++) {
			if (!"x".equals(ids[i])) {
				equations.put(Long.parseLong(ids[i]), Math.floorMod(-i, Long.parseLong(ids[i])));
			}
		}
		var n = new Long[equations.size()];
		var a = new Long[equations.size()];
		equations.values().toArray(a);
		equations.keySet().toArray(n);
		System.out.println(chineseRemainder(n, a));
	}
	
	/*
	 * I won't reinvent the wheel
	 * https://rosettacode.org/wiki/Chinese_remainder_theorem#Java
	 */
    private static long chineseRemainder(Long[] n, Long[] a) {
    	 
        var prod = Arrays.stream(n).reduce(1l, (i, j) -> i * j);
 
        long p, sm = 0;
        for (var i = 0; i < n.length; i++) {
            p = prod / n[i];
            sm += a[i] * mulInv(p, n[i]) * p;
        }
        return sm % prod;
    }
 
    private static long mulInv(long a, long b) {
    	long b0 = b;
        long x0 = 0;
        long x1 = 1;
 
        if (b == 1)
            return 1;
 
        while (a > 1) {
            var q = a / b;
            var amb = a % b;
            a = b;
            b = amb;
            var xqx = x1 - q * x0;
            x1 = x0;
            x0 = xqx;
        }
 
        if (x1 < 0)
            x1 += b0;
 
        return x1;
    }

}
