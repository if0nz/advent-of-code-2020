package it.ifonz.days.optimized;

import java.time.Instant;

public class EveryDay {

	public static void main(String[] args) throws Exception {
		System.out.println("D1");
		Day01.main(null);
		System.out.println("D2");
		Day02.main(null);
		System.out.println("D3");
		Day03.main(null);
		System.out.println("D4");
		Day04.main(null);
		System.out.println("D5");
		var begin = Instant.now().toEpochMilli();
		Day05.main(null);
		System.out.println("total: "+(Instant.now().toEpochMilli()-begin));
	}

}
