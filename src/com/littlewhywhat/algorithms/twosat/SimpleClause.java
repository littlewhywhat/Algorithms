package com.littlewhywhat.algorithms.twosat;

public class SimpleClause implements Clause {

	private final boolean isInversedSmaller;
	private final boolean isInversedLarger;
	private final int smallerVariableIndex;
	private final int largerVariableIndex;

	private boolean[] variables;

	public static SimpleClause getNewSimpleClause(boolean isInversedOne,
			int variableIndexOne, boolean isInversedTwo, int variableIndexTwo) {
		if (variableIndexOne < variableIndexTwo)
			return new SimpleClause(isInversedOne, variableIndexOne, isInversedTwo,
					variableIndexTwo);
		return new SimpleClause(isInversedTwo, variableIndexTwo, isInversedOne,
				variableIndexOne);
	}

	private SimpleClause(boolean isInversedOne, int variableIndexOne,
			boolean isInversedTwo, int variableIndexTwo) {
		this.isInversedSmaller = isInversedOne;
		this.isInversedLarger = isInversedTwo;
		this.smallerVariableIndex = variableIndexOne;
		this.largerVariableIndex = variableIndexTwo;
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
		return variables[largerVariableIndex];
	}

	private boolean getVariableOne() {
		return variables[smallerVariableIndex];
	}

	private boolean checkOne(boolean one) {
		return checkSingleItem(isInversedSmaller(), one);
	}

	private boolean checkSingleItem(boolean isInversed, boolean item) {
		return isInversed ^ item;
	}

	private boolean checkTwo(boolean two) {
		return checkSingleItem(isInversedLarger(), two);
	}

	public boolean isInversedSmaller() {
		return isInversedSmaller;
	}

	public boolean isInversedLarger() {
		return isInversedLarger;
	}

	public void inverseFirstVariable() {
		variables[smallerVariableIndex] = !getVariableOne();
	}

	public void inverseSecondVariable() {
		variables[largerVariableIndex] = !getVariableTwo();
	}

	@Override
	public String toString() {
		return "[" + (isInversedSmaller() ? "!" : "") + smallerVariableIndex + ", "
				+ (isInversedLarger() ? "!" : "") + largerVariableIndex + "]";
	}

	public int getSmallerVariableIndex() {
		return smallerVariableIndex;
	}

	public int getLargerVariableIndex() {
		return largerVariableIndex;
	}

	public boolean isInversedOneAndTwo() {
		return isInversedSmaller() && isInversedLarger();
	}

	public boolean isInversedOnlyOne() {
		return isInversedSmaller() && !isInversedLarger();
	}

	public boolean isInversedOnlyTwo() {
		return !isInversedSmaller() && isInversedLarger();
	}

	public boolean isInversedOneOrTwo() {
		return isInversedSmaller() || isInversedLarger();
	}

	public boolean isNotInversedOneAndTwo() {
		return !isInversedSmaller() && !isInversedLarger();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isInversedSmaller ? 1231 : 1237);
		result = prime * result + (isInversedLarger ? 1231 : 1237);
		result = prime * result + smallerVariableIndex;
		result = prime * result + largerVariableIndex;
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
		if (isInversedSmaller != other.isInversedSmaller) {
			return false;
		}
		if (isInversedLarger != other.isInversedLarger) {
			return false;
		}
		if (smallerVariableIndex != other.smallerVariableIndex) {
			return false;
		}
		if (largerVariableIndex != other.largerVariableIndex) {
			return false;
		}
		return true;
	}

}
