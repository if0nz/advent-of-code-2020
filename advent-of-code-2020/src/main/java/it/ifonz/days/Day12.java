package it.ifonz.days;

import java.io.IOException;
import java.util.List;

import it.ifonz.bean.Coord;
import it.ifonz.common.FileReader;

public class Day12 {

	public static void main(String[] args) throws IOException {
		var lines = FileReader.readLines("src/main/resources/d12.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> rows) {
		var faces = new char[] { 'E', 'S', 'W', 'N' };
		var face = 0;
		Coord position = new Coord(0, 0);
		for (var r : rows) {
			var shift = Integer.parseInt(r.substring(1));
			var direction = r.charAt(0);
			face = applyRule(position, shift, direction, face, faces);
		}
		System.out.println(Math.abs(position.x) + Math.abs(position.y));
	}

	private static int applyRule(Coord position, int shift, char direction, int face, char[] faces) {
		switch (direction) {
		case 'N':
			position.y += shift;
			break;
		case 'S':
			position.y -= shift;
			break;
		case 'E':
			position.x += shift;
			break;
		case 'W':
			position.x -= shift;
			break;
		case 'R':
			face = (face + (shift / 90)) % 4;
			break;
		case 'L':
			face = Math.floorMod(face - shift / 90, 4);
			break;
		case 'F':
			applyRule(position, shift, faces[face], face, faces);
		}
		return face;
	}

	public static void part2(List<String> rows) {

		Coord shipPosition = new Coord(0, 0);
		Coord waypointPosition = new Coord(10, 1);
		for (var r : rows) {
			var shift = Integer.parseInt(r.substring(1));
			var direction = r.charAt(0);
			switch (direction) {
			case 'N':
				waypointPosition.y += shift;
				break;
			case 'S':
				waypointPosition.y -= shift;
				break;
			case 'E':
				waypointPosition.x += shift;
				break;
			case 'W':
				waypointPosition.x -= shift;
				break;
			case 'R': {
				for (var i = 0; i < shift / 90; i++) {
					var tmpx = waypointPosition.x;
					var tmpy = waypointPosition.y;
					waypointPosition.x = tmpy;
					waypointPosition.y = -tmpx;
				}
				break;
			}
			case 'L':
				for (var i = 0; i < shift / 90; i++) {
					var tmpx = waypointPosition.x;
					var tmpy = waypointPosition.y;
					waypointPosition.x = -tmpy;
					waypointPosition.y = tmpx;
				}
				break;
			case 'F':
				shipPosition.x += waypointPosition.x * shift;
				shipPosition.y += waypointPosition.y * shift;
			}
		}
		System.out.println(Math.abs(shipPosition.x) + Math.abs(shipPosition.y));
	}

}
