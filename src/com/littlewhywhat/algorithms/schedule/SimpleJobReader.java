package com.littlewhywhat.algorithms.schedule;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.littlewhywhat.algorithms.io.txt.TextFileInputReader;

public class SimpleJobReader extends
		TextFileInputReader<Void, Schedule<SimpleJob>> {

	private final Schedule<SimpleJob> schedule;

	public SimpleJobReader(Schedule<SimpleJob> schedule) {
		super();
		this.schedule = schedule;
	}

	@Override
	protected void extractInputData(Scanner scanner)
			throws InputMismatchException {
		scanner.next();
		while (scanner.hasNext()) {
			schedule.add(new SimpleJob(scanner.nextInt(), scanner.nextInt()));
		}
		setData(schedule);
	}
}
