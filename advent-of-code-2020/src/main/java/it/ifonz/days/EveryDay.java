package it.ifonz.days;

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
		Day05.main(null);
		System.out.println("D6");
		Day06.main(null);
		System.out.println("D7");
		Day07.main(null);
		System.out.println("D8");
		var begin = Instant.now().toEpochMilli();
		Day08.main(null);
		System.out.println("total: "+(Instant.now().toEpochMilli()-begin));
	}

}
