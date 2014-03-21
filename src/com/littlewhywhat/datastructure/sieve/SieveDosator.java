package com.littlewhywhat.datastructure.sieve;

public class SieveDosator<T> {
	private T[] dose;
	private ArraySieve<T> arraySieve;

	public boolean hasDose() {
		return arraySieve.hasItems();
	}

	public void nextDose() {
		int count = 0;
		while (arraySieve.hasItems() && count < dose.length) {
			dose[count] = arraySieve.getItem();
			count++;
		}
	}

	public void reset() {
		arraySieve.reset();
	}

	public void setArray(T[] array) {
		arraySieve.setArray(array);
	}

	public void setArraySieve(ArraySieve<T> arraySieve) {
		this.arraySieve = arraySieve;
	}
	
	public void setDose(T[] dose) {
		this.dose = dose;
		arraySieve.setInterval(arraySieve.getArray().length/dose.length);
	}

}
