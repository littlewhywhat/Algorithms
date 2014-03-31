package com.littlewhywhat.algorithms.badparket.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.littlewhywhat.algorithms.badparket.Data;
import com.littlewhywhat.algorithms.badparket.Data.Index;
import com.littlewhywhat.algorithms.badparket.ParketReader;

public class TestBadParketReader {

	private ParketReader reader; 
	
	@Before
	public void setUp() throws Exception {
		reader = new ParketReader();
		reader.setInputFilePath(TestBadParket.INPUTFILE);
	}

	@Test
	public void testRead() {
		reader.read();
		Data data = reader.getData();
		Assert.assertEquals(false, data .get(0, 0));
		Assert.assertEquals(true, data.get(0, 1));
		Assert.assertEquals(true, data.get(0, 2));
		Assert.assertEquals(false, data.get(1, 0));
		Assert.assertEquals(true, data.get(1, 1));
		Assert.assertEquals(false, data.get(1, 2));
		
		Assert.assertEquals(true, data.hasNext());
		
		Assert.assertEquals(new Index(0,0), data.pushFirst());
		Assert.assertEquals(new Index(0,1), data.pushFirst());
		Assert.assertEquals(new Index(0,2), data.pushFirst());
		
		Assert.assertEquals(new Index(1,0), data.pushFirst());
		Assert.assertEquals(new Index(1,1), data.pushFirst());
		Assert.assertEquals(new Index(1,2), data.pushFirst());
		
		Assert.assertEquals(false, data.hasNext());
	}

}
