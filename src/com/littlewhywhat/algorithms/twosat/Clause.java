package com.littlewhywhat.algorithms.twosat;

public interface Clause {
	boolean check();
	void setVariables(boolean[] variables);
}
