package com.littlewhywhat.algorithms.twosat;

public class SimpleClause implements Clause {

	private final boolean isInversedOne;
	private final boolean isInversedTwo;
	private final int variableOne;
	private final int variableTwo;
	
	private boolean[] variables;
	
	public SimpleClause(boolean isInversedOne, int variableOne , boolean isInversedTwo, int variableTwo) {
		this.isInversedOne = isInversedOne;
		this.isInversedTwo = isInversedTwo;
		this.variableOne = variableOne;
		this.variableTwo = variableTwo;
	}
	
	@Override
	public void setVariables(boolean[] variables) {
		this.variables = variables;
	}
	
	@Override
	public boolean check() {
		//boolean one = checkOne(getVariableOne());
		//boolean two = checkTwo(getVariableTwo());
		//System.out.println(one + " or " + two);
		return checkOne(getVariableOne()) || checkTwo(getVariableTwo());
	}

	private boolean getVariableTwo() {
		return variables[variableTwo];
	}

	private boolean getVariableOne() {
		return variables[variableOne];
	}

	private boolean checkOne(boolean one) {
		return checkSingleItem(getIsInversedOne(), one);
	}

	private boolean checkSingleItem(boolean isInversed, boolean item) {
		return isInversed ^ item;
	}

	private boolean checkTwo(boolean two) {
		return checkSingleItem(getIsInversedTwo(), two);
	}

	private boolean getIsInversedOne() {
		return isInversedOne;
	}

	private boolean getIsInversedTwo() {
		return isInversedTwo;
	}

	public void inverseFirstVariable() {
		variables[variableOne] = !getVariableOne();
	}
	
	public void inverseSecondVariable() {
		variables[variableTwo] = !getVariableTwo();
	}

	@Override
	public String toString() {
		return "[" + (getIsInversedOne()? "!" : "") + variableOne + ", " + (getIsInversedTwo()? "!" : "")
				+ variableTwo + "]";
	}
	
}
