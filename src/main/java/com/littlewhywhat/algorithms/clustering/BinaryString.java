package com.littlewhywhat.algorithms.clustering;

import java.util.Arrays;

public class BinaryString {
	private boolean[] symbols;
	public BinaryString(boolean[] symbols) {
		this.symbols = symbols;
	}

	public int length() {
		return symbols.length;
	}

	public void flip(int index) {
		this.symbols[index] = !this.symbols[index];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(symbols);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BinaryString))
			return false;
		BinaryString other = (BinaryString) obj;
		if (!symbolsEquals(other))
			return false;
		return true;
	}

	private boolean symbolsEquals(BinaryString other) {
		if (this.symbols.length != other.symbols.length)
			return false;
		for (int i = 0; i < other.symbols.length; i++)
			if (symbols[i] != other.symbols[i])
				return false;
		return true;
	}
	
	@Override
	public String toString() {
		String str = "";
		for (boolean logic : symbols) {
			int digit = logic? 1 : 0;
			str = str + digit;
		}
		return str;
	}
	
	public BinaryString copy() {
		return new BinaryString(this.symbols.clone());
	}
}
