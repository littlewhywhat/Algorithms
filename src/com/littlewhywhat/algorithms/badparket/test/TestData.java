package com.littlewhywhat.algorithms.badparket.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.badparket.Data;
import com.littlewhywhat.algorithms.badparket.Data.Index;
import com.littlewhywhat.algorithms.badparket.ParketReader;

public class TestData {

	private Data data;
	
	@Before
	public void setUp() throws Exception {
		ParketReader reader = new ParketReader();
		reader.setInputFilePath(TestBadParket.INPUTFILE);
		reader.read();
		this.data = reader.getData();
	}

	@Test
	public void testGet() {
		Assert.assertEquals(false, this.data.get(0, 0));
		Assert.assertEquals(true, this.data.get(0, 1));
		Assert.assertEquals(true, this.data.get(0, 2));
		Assert.assertEquals(false, this.data.get(1, 0));
		Assert.assertEquals(true, this.data.get(1, 1));
		Assert.assertEquals(false, this.data.get(1, 2));
	}

	@Test
	public void testNext() {
		data.remove(0, 1);
		data.pushFirst();
		Index index = data.pushFirst();
		Assert.assertEquals(new Index(0,2), index);
	}

}
