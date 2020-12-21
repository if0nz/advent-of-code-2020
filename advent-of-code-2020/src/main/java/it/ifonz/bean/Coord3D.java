package it.ifonz.bean;

import java.util.Objects;

public class Coord3D extends Coord {

	public int z;
	
	public Coord3D(int x, int y) {
		super(x, y);
		z = 0;
	}

	public Coord3D(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(z);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord3D other = (Coord3D) obj;
		return z == other.z;
	}

	@Override
	public String toString() {
		return "Coord3D ["+super.x+" "+super.y+" "+z+"]";
	}

	
}
