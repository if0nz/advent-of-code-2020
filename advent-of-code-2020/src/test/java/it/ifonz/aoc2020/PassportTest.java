package it.ifonz.aoc2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import it.ifonz.bean.Passport;
import it.ifonz.common.FileReader;

class PassportTest {

	@Test
	void test() throws IOException {
		var lines = FileReader.readLines("src/main/resources/d04.txt");
		var passports = Passport.parsePassports(lines);
		assertEquals(280, passports.size());
		var valid = passports.stream().filter(p -> p.isValid()).count();
		assertEquals(137, valid);
	}

}
