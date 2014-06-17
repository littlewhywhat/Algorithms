package com.littlewhywhat.algorithms.graphs.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.littlewhywhat.algorithms.graphs.Graph;
import com.littlewhywhat.algorithms.io.txt.AbstractFileInputReader;

public class GraphReader extends AbstractFileInputReader<Void, Graph> {

	private SizeCounter sizeCounter;
	private GraphFiller filler;

	public void setGraph(Graph graph) {
		this.setData(graph);
	}

	public void setSizeCounter(SizeCounter sizeCounter) {
		this.sizeCounter = sizeCounter;
	}

	public void setGraphFiller(GraphFiller filler) {
		this.filler = filler;
	}

	@Override
	protected void readFile(File file) {
		try (Scanner countScanner = new Scanner(file);
				Scanner fillerScanner = new Scanner(file)) {
			sizeCounter.count(countScanner);
			getData().setSize(sizeCounter.getSize());
			filler.fill(getData(), fillerScanner);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + this.getInputFilePath());
		} catch (InputMismatchException e) {
			System.out.println("Unable to read file: " + this.getInputFilePath());
		}
	}
}
