package com.littlewhywhat.algorithms.clustering;

import java.util.Arrays;

public class HummingDistance {
	private boolean[] distance;
	public HummingDistance(boolean[] distance) {
		this.distance = distance;
	}
	
	public boolean[] getDistance() {
		return distance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(distance);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof HummingDistance)) {
			return false;
		}
		HummingDistance other = (HummingDistance) obj;
		if (!distanceEquals(other)) {
			return false;
		}
		return true;
	}

	private boolean distanceEquals(HummingDistance other) {
		if (this.distance.length != other.distance.length)
			return false;
		for (int i = 0; i < other.distance.length; i++) {
			if (distance[i] != other.distance[i])
				return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		String str = "";
		for (boolean b : distance) {
			int a = 0;
			if (b)
				a = 1;
			str = str + a;
		}
		return str;
	}
	
}
