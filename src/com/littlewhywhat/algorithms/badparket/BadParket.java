package com.littlewhywhat.algorithms.badparket;

import java.util.LinkedList;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.badparket.Data.Index;

public class BadParket extends AbstractAlgorithm<int[], Data, Integer> {

	LinkedList<Index> badCellsForCurrentFigure;

	@Override
	public void execute() {
		int result = 0;
		badCellsForCurrentFigure = new LinkedList<Index>();
		LinkedList<Index> figure = new LinkedList<Data.Index>();
		BadParketOneTwoComputer computer = new BadParketOneTwoComputer();
		computer.setConfig(getConfig());
		computer.setData(figure);
		while (getData().hasUnProcessed()) {
			Index index = getData().pop();
			if (index.isBad()) {
				badCellsForCurrentFigure.addLast(index);
				figure.clear();
				while (!badCellsForCurrentFigure.isEmpty()) {
					Index badCell = badCellsForCurrentFigure.pop();
					addNewNeighbours(badCell);
					figure.add(badCell);
					//System.out.println(badCell);
				}
				//System.out.println(figure);
				//System.out.println();
				if (!figure.isEmpty()) {
					computer.execute();
					result += computer.getOutput();
				}
				//System.out.println();
			}
		}
		setOutput(result);
		
	}

	private void addNewNeighbours(Index index) {
		Index neighbour;
		if (index.hasLeftNeighbour()) {
			neighbour = index.getLeftNeighbour();
			processNeighbour(neighbour);
		}
		if (index.hasRightNeighbour()) {
			neighbour = index.getRightNeighbour();
			processNeighbour(neighbour);
		}
		if (index.hasDownNeighbour()) {
			neighbour = index.getDownNeighbour();
			processNeighbour(neighbour);
		}
		if (index.hasUpNeighbour()) {
			neighbour = index.getUpNeighbour();
			processNeighbour(neighbour);
		}
			
		//System.out.println(count);
		//figure.put(count, index);
	}

	private void processNeighbour(Index neighbour) {
		if (neighbour.isProcessed())
			return;
		if (neighbour.isBad())
			badCellsForCurrentFigure.addLast(neighbour);
		neighbour.setIsProcessed();
	}

}
