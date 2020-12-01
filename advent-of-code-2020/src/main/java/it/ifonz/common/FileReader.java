package it.ifonz.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {
	
	public static List<Integer> readIntegerLines(String fileName) throws IOException  {
		try (var readLines = Files.lines(Paths.get(fileName))) {
			return readLines.map(Integer::parseInt).collect(Collectors.toList());
		}
	}
	
	public static List<Long> readLongLines(String fileName) throws IOException {
		try (var readLines = Files.lines(Paths.get(fileName))) {
			return readLines.map(Long::parseLong).collect(Collectors.toList());
		}
	}
	
	public static List<String> readLines(String fileName) throws IOException {
		return Files.lines(Paths.get(fileName)).collect(Collectors.toList());
	}
	
	public static String readLine(String fileName) throws IOException {
		return Files.lines(Paths.get(fileName)).collect(Collectors.toList()).get(0);
	}
}
