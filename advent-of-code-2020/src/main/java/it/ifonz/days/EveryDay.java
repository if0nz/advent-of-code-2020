package it.ifonz.days;

import java.time.Instant;

public class EveryDay {

	public static void main(String[] args) throws Exception {
		var begin = Instant.now().toEpochMilli();
		System.out.println("D1");
		Day01.main(args);
		System.out.println("total: "+(Instant.now().toEpochMilli()-begin));
	}

}
