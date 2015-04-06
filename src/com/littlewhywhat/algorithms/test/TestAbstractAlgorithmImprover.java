package com.littlewhywhat.algorithms.test;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.AbstractAlgorithm;
import com.littlewhywhat.algorithms.AbstractAlgorithmImprover;
import com.littlewhywhat.algorithms.PerformanceChecker;

public class TestAbstractAlgorithmImprover {

	private MockAbstractAlgorithmImprover improver;
	private MockAbstractAlgorithm algorithm;
	private MockPerformanceChecker checker;
	
	@Before
	public void setUp() throws Exception {
		algorithm = new MockAbstractAlgorithm();
		checker = new MockPerformanceChecker();
		improver = new MockAbstractAlgorithmImprover();
		improver.setAlgorithm(algorithm);
		improver.setChecker(checker);
		improver.setData("5");
		improver.setConfig(1);
	}

	@Test
	public void testImprove() {
		improver.improve();
		Assert.assertEquals(1, algorithm.getConfig(), 0);
		improver.setData("25");
		improver.improve();
		Assert.assertEquals(5, algorithm.getConfig(), 0);
	}
	@Test
	public void testSetMaxNumberOfImprovements() {
		improver.setConfig(1);
		improver.setData("25");
		improver.setMaxNumberOfImprovements(2);
		improver.improve();
		Assert.assertEquals(3, algorithm.getConfig(), 0);
	}

	private class MockAbstractAlgorithmImprover extends AbstractAlgorithmImprover<String,Integer, Integer, Integer, Integer> {
	
		@Override
		protected Integer getPreviousConfig() {
			int config = getConfig();
			config--;
			return config;
		}

		@Override
		protected void improveConfig() {
			setConfig(getConfig().intValue() + 1);
		}

		@Override
		protected Integer measurePerformance() {
			getAlgorithm().setData(Integer.parseInt(getData()));
			getAlgorithm().execute();
			return getAlgorithm().getOutput();
		}
	}
	
	private class MockAbstractAlgorithm extends AbstractAlgorithm<Integer, Integer, Integer> {

		@Override
		public void execute() {
			setOutput(getData()/getConfig());			
		}
		
	}
	
	private class MockPerformanceChecker implements PerformanceChecker<Integer> {

		@Override
		public PerformanceCheckResult check(Integer value) {
			if (value == 5)
				return PerformanceCheckResult.CAN_NOT_BE_IMPROVED;
			return PerformanceCheckResult.CAN_BE_IMPROVED;
		}
		
	}
}
