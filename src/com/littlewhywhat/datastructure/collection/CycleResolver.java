package com.littlewhywhat.datastructure.collection;

public class CycleResolver {
	private int lengthOfCycle;

	public int resolveIndex(int index) {
		if (index > -1 && index < lengthOfCycle )
			return index;
		if (index % lengthOfCycle == 0)
			return 0;
		if (index < 0)
			return index + (index/-lengthOfCycle + 1) * lengthOfCycle ;
		return index - (index/lengthOfCycle)*lengthOfCycle;	
	}
	
	public int getLengthOfCycle() {
		return lengthOfCycle;
	}

	public CycleResolver(int lengthOfCycle) {
		super();
		this.lengthOfCycle = lengthOfCycle;
	}

	public void setLengthOfCycle(int lengthOfCycle) {
		this.lengthOfCycle = lengthOfCycle;
	}
	
}
