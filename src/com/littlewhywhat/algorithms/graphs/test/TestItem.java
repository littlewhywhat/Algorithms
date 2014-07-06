package com.littlewhywhat.algorithms.graphs.test;

import com.littlewhywhat.algorithms.graphs.Id;

class TestItem implements Id {

	private int id;

	TestItem(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TestItem)) {
			return false;
		}
		TestItem other = (TestItem) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

}
