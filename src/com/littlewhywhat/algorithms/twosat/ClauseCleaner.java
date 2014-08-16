package com.littlewhywhat.algorithms.twosat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import com.littlewhywhat.algorithms.AbstractAlgorithm;

public class ClauseCleaner extends
		AbstractAlgorithm<Integer, List<SimpleClause>, List<SimpleClause>> {

	private Set<Clause> bloomFilter;
	private Map<Integer, LinkedList<Clause>> clausesCount;

	@Override
	public void execute() {
		final List<SimpleClause> data = getData();
		setUp();
		for (ListIterator<SimpleClause> listIterator = data.listIterator(); listIterator
				.hasNext();) {
			SimpleClause clause = listIterator.next();
			if (isDuplicate(clause)
					|| (isAboutOneVariable(clause) && (clause
							.isInversedAnyone()
							|| clause.isInversedAll() || clause
								.isNotInversedAll())))
				listIterator.remove();
			else {
				int hash = getIndicesHash(clause);
				if (!clausesCount.containsKey(hash))
					clausesCount.put(hash, new LinkedList<Clause>());
				clausesCount.get(hash).add(clause);
			}

		}
		for (Iterator<LinkedList<Clause>> iterator = clausesCount.values().iterator(); iterator.hasNext();) {
			if (iterator.next().size() < 3)
				iterator.remove();
		}
		setOutput(data);
	}

	private void setUp() {
		bloomFilter = new HashSet<Clause>();
		clausesCount = new HashMap<Integer, LinkedList<Clause>>();
	}

	private boolean isAboutOneVariable(SimpleClause clause) {
		if (clause.getSmallerVariableIndex() == clause.getLargerVariableIndex())
			return true;
		return false;
	}

	private int getIndicesHash(SimpleClause clause) {
		final int prime = 31;
		int result = 1;
		result = prime * result + clause.getSmallerVariableIndex();
		result = prime * result + clause.getLargerVariableIndex();
		return result;
	}

	private boolean isDuplicate(Clause clause) {
		if (bloomFilter.contains(clause))
			return true;
		bloomFilter.add(clause);
		return false;
	}
}
