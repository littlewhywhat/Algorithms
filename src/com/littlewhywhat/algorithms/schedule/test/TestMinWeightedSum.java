package com.littlewhywhat.algorithms.schedule.test;

import java.util.Comparator;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.schedule.MinWeightedSum;
import com.littlewhywhat.algorithms.schedule.Schedule;
import com.littlewhywhat.algorithms.schedule.SimpleJob;
import com.littlewhywhat.algorithms.schedule.SimpleJobReader;
import com.littlewhywhat.algorithms.schedule.SimpleSchedule;

public class TestMinWeightedSum {

	private static final long ANSWER_DIFF_SMALL = 45;
	private static final long ANSWER_RATIO_SMALL = 44;
	private final String FOLDER = "src/com/littlewhywhat/algorithms/schedule/test/input/";
	private final String INPUT = FOLDER + "jobs.txt";
	private final String INPUT_SMALL = FOLDER + "jobsSmall.txt";

	private final long ANSWER_DIFF = 69119377652L;
	private final long ANSWER_RATIO = 67311454237L;
	
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
	public void testDiffBig() {
		test(comparatorDiff, INPUT, ANSWER_DIFF);
	}
	
	@Test
	public void testDiffSmall() {
		test(comparatorDiff, INPUT_SMALL, ANSWER_DIFF_SMALL);
	}
	
	@Test
	public void testRatioSmall() {
		test(comparatorRatio, INPUT_SMALL, ANSWER_RATIO_SMALL);
	}
	
	private void test(Comparator<SimpleJob> comparator, String inputFilePath, long answer) {
		schedule = new SimpleSchedule(comparator);
		reader = new SimpleJobReader(schedule);
		reader.setInputFilePath(inputFilePath);
		reader.read();
		algo.setData(reader.getData());
		algo.execute();
		Assert.assertEquals(answer, algo.getOutput().longValue());
	}
	
	@Test
	public void testRatioBig() {
		test(comparatorRatio, INPUT, ANSWER_RATIO);
	}

}
