package it.ifonz.bean;

import java.util.Objects;

public class Coord4D extends Coord3D {

	public int w;
	
	public Coord4D(int x, int y) {
		super(x, y);
		this.w = 0;
	}

	public Coord4D(int x, int y, int z, int w) {
		super(x, y, z);
		this.w = w;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(w);
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
		Coord4D other = (Coord4D) obj;
		return w == other.w;
	}

	
}
