package com.littlewhywhat.algorithms.badparket;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.badparket.Data.Index;

public class BadParketOneTwoComputer extends
		AbstractAlgorithm<int[], LinkedList<Index>, Integer> {
	private List<Index> minNeighbours;
	private List<Index> tempNeighbours;	
	
	@Override
	public void execute() {
		int sum = 0;
		minNeighbours = new ArrayList<Data.Index>();
		tempNeighbours = new ArrayList<Data.Index>();		
		while (!getData().isEmpty()) {
			getMinNeighbours();
			if (!this.minNeighbours.isEmpty()) {
				removeFirstOfMinNeighbours();
				sum += getConfig()[0];
			}
			else
				sum += getConfig()[1];
		}
		setOutput(sum);
	}

	private void removeFirstOfMinNeighbours() {
		getData().remove(this.minNeighbours.get(0));
	}

	private List<Index> getMinNeighbours() {
		int minIndex = 0;
		getBadNeighbours(this.minNeighbours, getData().getFirst());
		for (int i = 1; i < getData().size(); i++) {
			Index index = getData().get(i);
			getBadNeighbours(this.tempNeighbours, index);
			if (this.tempNeighbours.size() < minNeighbours.size()) {
				minNeighbours.clear();
				minNeighbours.addAll(this.tempNeighbours);
				minIndex = i;
			}
		}
		getData().remove(minIndex);
		return minNeighbours;
	}

	private List<Index> getBadNeighbours(List<Index> neighbours, Index index) {
		neighbours.clear();
		if (index.hasUpNeighbour())
			addIfIsBad(neighbours, index.getUpNeighbour());
		if (index.hasDownNeighbour())
			addIfIsBad(neighbours, index.getDownNeighbour());
		if (index.hasLeftNeighbour())
			addIfIsBad(neighbours, index.getLeftNeighbour());
		if (index.hasRightNeighbour())
			addIfIsBad(neighbours, index.getRightNeighbour());
		return neighbours;
	}

	private void addIfIsBad(List<Index> neighbours, Index neighbour) {
		if (neighbour.isBad() && getData().contains(neighbour))
			neighbours.add(neighbour);
	}

}
