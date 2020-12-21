package it.ifonz.days;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.ifonz.bean.Coord3D;
import it.ifonz.bean.Coord4D;
import it.ifonz.common.FileReader;

public class Day17 {

	public static void main(String[] args) throws IOException {
		var lines = FileReader.readLines("src/main/resources/d17.txt");
		part1(lines);
		part2(lines);
	}

	public static void part1(List<String> lines) {
		Set<Coord3D> cube = new HashSet<>();
		for (var y = 0; y < lines.size(); y++) {
			for (var x = 0; x < lines.get(y).toCharArray().length; x++) {
				var charAt = lines.get(y).charAt(x);
				if (charAt == '#') {
					cube.add(new Coord3D(x, y, 0));
				}
			}
		}
		for (var i = 0; i < 6; i++) {
			cube = expandCube(cube);
		}
		System.out.println(cube.size());
	}

	private static Set<Coord3D> expandCube(Set<Coord3D> oldCube) {
		var newCube = new HashSet<Coord3D>();
		var candidates = new HashMap<Coord3D, Integer>();
		for (var coord : oldCube) {
			var cnt = 0;
			var neighbours = findNeighbours(coord);
			for (var c : neighbours) {
				if (oldCube.contains(c)) {
					cnt++;
				} else {
					var n = candidates.getOrDefault(c, 0);
					candidates.put(c, n + 1);
				}
			}
			if (cnt == 2 || cnt == 3) {
				newCube.add(coord);
			}
		}
		candidates.entrySet().stream().filter(e -> e.getValue() == 3).forEach(e -> newCube.add(e.getKey()));
		return newCube;
	}

	private static Set<Coord4D> expandCube2(Set<Coord4D> oldCube) {
		var newCube = new HashSet<Coord4D>();
		var candidates = new HashMap<Coord4D, Integer>();
		for (var coord : oldCube) {
			var cnt = 0;
			var neighbours = findNeighbours2(coord);
			for (var c : neighbours) {
				if (oldCube.contains(c)) {
					cnt++;
				} else {
					var n = candidates.getOrDefault(c, 0);
					candidates.put(c, n + 1);
				}
			}
			if (cnt == 2 || cnt == 3) {
				newCube.add(coord);
			}
		}
		candidates.entrySet().stream().filter(e -> e.getValue() == 3).forEach(e -> newCube.add(e.getKey()));
		return newCube;
	}
	
	public static void part2(List<String> lines) {
		Set<Coord4D> cube = new HashSet<>();
		for (var y = 0; y < lines.size(); y++) {
			for (var x = 0; x < lines.get(y).toCharArray().length; x++) {
				var charAt = lines.get(y).charAt(x);
				if (charAt == '#') {
					cube.add(new Coord4D(x, y));
				}
			}
		}
		for (var i = 0; i < 6; i++) {
			cube = expandCube2(cube);
		}
		System.out.println(cube.size());
	}

	private static Set<Coord3D> findNeighbours(Coord3D c) {
		var n = new HashSet<Coord3D>();
		for (var x = -1; x <= 1; x++) {
			for (var y = -1; y <= 1; y++) {
				for (var z = -1; z <= 1; z++) {
					if (!(x == 0 && y == 0 && z == 0)) {
						n.add(new Coord3D(c.x + x, c.y + y, c.z + z));
					}
				}
			}
		}
		return n;
	}
	
	private static Set<Coord4D> findNeighbours2(Coord4D c) {
		var n = new HashSet<Coord4D>();
		for (var x = -1; x <= 1; x++) {
			for (var y = -1; y <= 1; y++) {
				for (var z = -1; z <= 1; z++) {
					for (var w = -1; w <= 1; w++)
					if (!(x == 0 && y == 0 && z == 0 && w == 0)) {
						n.add(new Coord4D(c.x + x, c.y + y, c.z + z,c.w+w));
					}
				}
			}
		}
		return n;
	}
	
}
