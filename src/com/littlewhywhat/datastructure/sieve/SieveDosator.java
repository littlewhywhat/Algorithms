package com.littlewhywhat.datastructure.sieve;

public class SieveDosator<T> {
	private T[] dose;
	private ArraySieve<T> arraySieve;

	public void setArraySieve(ArraySieve<T> arraySieve) {
		this.arraySieve = arraySieve;
	}

	public void setDose(T[] dose) {
		this.dose = dose;
		arraySieve.setInterval(dose.length);
	}

	public void nextDose() {
		int count = 0;
		while (arraySieve.hasItems() && count < dose.length)
			dose[count] = arraySieve.getItem();
	}

	public boolean hasDose() {
		return arraySieve.hasItems();
	}

	public void setArray(T[] array) {
		arraySieve.setArray(array);
	}

}
