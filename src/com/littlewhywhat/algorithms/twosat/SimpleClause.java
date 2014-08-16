package com.littlewhywhat.algorithms.twosat;

public class SimpleClause implements Clause {

	private final boolean isInversedOne;
	private final boolean isInversedTwo;
	private final int variableIndexOne;
	private final int variableIndexTwo;

	private boolean[] variables;

	public static SimpleClause getNewSimpleClause(boolean isInversedOne,
			int variableIndexOne, boolean isInversedTwo, int variableIndexTwo) {
		return new SimpleClause(isInversedOne, variableIndexOne, isInversedTwo,
				variableIndexTwo);
	}

	private SimpleClause(boolean isInversedOne, int variableIndexOne,
			boolean isInversedTwo, int variableIndexTwo) {
		this.isInversedOne = isInversedOne;
		this.isInversedTwo = isInversedTwo;
		this.variableIndexOne = variableIndexOne;
		this.variableIndexTwo = variableIndexTwo;
	}

	@Override
	public void setVariables(boolean[] variables) {
		this.variables = variables;
	}

	@Override
	public boolean check() {
		// boolean one = checkOne(getVariableOne());
		// boolean two = checkTwo(getVariableTwo());
		// System.out.println(one + " or " + two);
		return checkOne(getVariableOne()) || checkTwo(getVariableTwo());
	}

	private boolean getVariableTwo() {
		return variables[variableIndexTwo];
	}

	private boolean getVariableOne() {
		return variables[variableIndexOne];
	}

	private boolean checkOne(boolean one) {
		return checkSingleItem(isInversedOne(), one);
	}

	private boolean checkSingleItem(boolean isInversed, boolean item) {
		return isInversed ^ item;
	}

	private boolean checkTwo(boolean two) {
		return checkSingleItem(isInversedTwo(), two);
	}

	public boolean isInversedOne() {
		return isInversedOne;
	}

	public boolean isInversedTwo() {
		return isInversedTwo;
	}

	public void inverseFirstVariable() {
		variables[variableIndexOne] = !getVariableOne();
	}

	public void inverseSecondVariable() {
		variables[variableIndexTwo] = !getVariableTwo();
	}

	@Override
	public String toString() {
		return "[" + (isInversedOne() ? "!" : "") + variableIndexOne + ", "
				+ (isInversedTwo() ? "!" : "") + variableIndexTwo + "]";
	}

	public int getVariableIndexOne() {
		return variableIndexOne;
	}

	public int getVariableIndexTwo() {
		return variableIndexTwo;
	}

	public boolean isInversedOneAndTwo() {
		return isInversedOne() && isInversedTwo();
	}

	public boolean isInversedOnlyOne() {
		return isInversedOne() && !isInversedTwo();
	}

	public boolean isInversedOnlyTwo() {
		return !isInversedOne() && isInversedTwo();
	}

	public boolean isInversedOneOrTwo() {
		return isInversedOne() || isInversedTwo();
	}

	public boolean isNotInversedOneAndTwo() {
		return !isInversedOne() && !isInversedTwo();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isInversedOne ? 1231 : 1237);
		result = prime * result + (isInversedTwo ? 1231 : 1237);
		result = prime * result + variableIndexOne;
		result = prime * result + variableIndexTwo;
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
		if (!(obj instanceof SimpleClause)) {
			return false;
		}
		SimpleClause other = (SimpleClause) obj;
		if (isInversedOne != other.isInversedOne) {
			return false;
		}
		if (isInversedTwo != other.isInversedTwo) {
			return false;
		}
		if (variableIndexOne != other.variableIndexOne) {
			return false;
		}
		if (variableIndexTwo != other.variableIndexTwo) {
			return false;
		}
		return true;
	}

}
