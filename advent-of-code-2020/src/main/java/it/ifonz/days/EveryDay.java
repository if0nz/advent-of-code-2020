package it.ifonz.days;

import java.time.Instant;

public class EveryDay {

	public static void main(String[] args) throws Exception {
		var begin = Instant.now().toEpochMilli();
		System.out.println("D1");
		Day01.main(null);
		System.out.println("D2");
		Day02.main(null);
		System.out.println("D3");
		Day03.main(null);
		System.out.println("D4");
		Day04.main(null);
		System.out.println("D5");
		Day05.main(null);
		System.out.println("D6");
		Day06.main(null);
		System.out.println("D7");
		Day07.main(null);
		System.out.println("D8");
		Day08.main(null);
		System.out.println("D9");
		Day09.main(null);
		System.out.println("D10");
		Day10.main(null);
		System.out.println("D11");
		Day11.main(null);
		System.out.println("D12");
		Day12.main(null);
		System.out.println("D13");
		Day13.main(null);
		System.out.println("D14");
		Day14.main(null);
		System.out.println("total: "+(Instant.now().toEpochMilli()-begin));
	}

}
