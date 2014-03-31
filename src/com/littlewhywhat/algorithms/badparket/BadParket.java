package com.littlewhywhat.algorithms.badparket;

import java.util.LinkedList;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.badparket.Data.Index;

public class BadParket extends AbstractAlgorithm<int[], Data, Integer> {

	LinkedList<Index> neighbours;

	@Override
	public void execute() {
		int result = 0;
		neighbours = new LinkedList<Index>();
		LinkedList<Index> figure = new LinkedList<Index>();
		BadParketOneTwoComputer computer = new BadParketOneTwoComputer();
		computer.setConfig(getConfig());
		computer.setData(figure);
		while (getData().hasUnProcessed()) {
			neighbours.addLast(getData().pop());
			figure.clear(); 
			while (!neighbours.isEmpty()) {
				Index startIndex = neighbours.pop();
				if (startIndex.isBad()) {
					addNewNeighbours(startIndex);
					figure.add(startIndex);
				}
				startIndex.setIsProcessed();
			}
			if (!figure.isEmpty()) {
				computer.execute();
				result += computer.getOutput();
				System.out.println(computer.getOutput());
			}
		}
		setOutput(result);

	}

	private void addNewNeighbours(Index index) {
		if (index.hasLeftNeighbour())
			addNeighbourIfNotProcessed(index.getLeftNeighbour());
		if (index.hasRightNeighbour())
			addNeighbourIfNotProcessed(index.getRightNeighbour());
		if (index.hasUpNeighbour())
			addNeighbourIfNotProcessed(index.getUpNeighbour());
		if (index.hasDownNeighbour())
			addNeighbourIfNotProcessed(index.getDownNeighbour());

	}

	private void addNeighbourIfNotProcessed(Index neighbour) {
		if (!neighbour.isProcessed())
			neighbours.addLast(neighbour);
		neighbour.setIsProcessed();
	}

}
