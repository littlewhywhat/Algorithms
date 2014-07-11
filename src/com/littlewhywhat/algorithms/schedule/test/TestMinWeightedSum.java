package com.littlewhywhat.algorithms.schedule.test;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.schedule.MinWeightedSum;
import com.littlewhywhat.algorithms.schedule.Schedule;
import com.littlewhywhat.algorithms.schedule.SimpleJob;
import com.littlewhywhat.algorithms.schedule.SimpleJobReader;
import com.littlewhywhat.algorithms.schedule.SimpleSchedule;

public class TestMinWeightedSum {

	private final String FOLDER = "src/com/littlewhywhat/algorithms/schedule/test/input/";
	private final String INPUT = FOLDER + "jobs.txt";
	private final String INPUT_SMALL = FOLDER + "jobsSmall.txt";

	private Schedule<SimpleJob> schedule;
	private SimpleJobReader reader;
	private Comparator<SimpleJob> comparatorDiff = new Comparator<SimpleJob>() {
		@Override
		public int compare(SimpleJob one, SimpleJob two) {
			Integer diffOne = one.getWeight() - one.getLength();
			Integer diffTwo = two.getWeight() - two.getLength();
			if (diffOne.equals(diffTwo))
				if (one.getWeight() > two.getWeight())
					return 1;
				else
					return -1;
			else
				return diffOne.compareTo(diffTwo);
		}
	};
	private Comparator<SimpleJob> comparatorRatio = new Comparator<SimpleJob>() {
		@Override
		public int compare(SimpleJob one, SimpleJob two) {
			Double ratioOne = (double) one.getWeight() / (double)one.getLength();
			Double ratioTwo = (double) two.getWeight() / (double)two.getLength();
			return ratioOne.compareTo(ratioTwo);
		}
	};
	private MinWeightedSum<SimpleJob> algo;
	
	
	@Before
	public void setUp() throws Exception {
		algo = new MinWeightedSum<SimpleJob>();
	}

	@Test
	public void testDiff() {
		schedule = new SimpleSchedule(comparatorDiff);
		reader = new SimpleJobReader(schedule);
		reader.setInputFilePath(INPUT);
		reader.read();
		algo.setData(reader.getData());
		algo.execute();
		System.out.println(algo.getOutput());
	}
	
	@Test
	public void testRatio() {
		schedule = new SimpleSchedule(comparatorRatio);
		reader = new SimpleJobReader(schedule);
		reader.setInputFilePath(INPUT);
		reader.read();
		algo.setData(reader.getData());
		algo.execute();
		System.out.println(algo.getOutput());
	}

}
