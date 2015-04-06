package com.littlewhywhat.algorithms.parser;

import org.junit.Assert;
import org.junit.Test;


public class TestIpParser {

	@Test
	public void test() {
		int address = 1297618184;		
		Assert.assertEquals("77.88.21.8", IpParser.parseIP(address));
	}

}
