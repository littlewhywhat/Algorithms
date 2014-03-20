package com.littlewhywhat.algorithms.check.login.test;
import org.junit.Assert;
import org.junit.Test;

import com.littlewhywhat.algorithms.check.login.CheckLogin;
import com.littlewhywhat.algorithms.check.login.CheckLoginOne;

public class TestCheckLogin {

	@Test
	public void test() {

		testCheckLogin(new CheckLoginOne());
	}

	public void testCheckLogin(CheckLogin CheckLogin) {
		String login = null;
		long start;
		long end;
		// check null
		login = null;
		Assert.assertEquals(false, CheckLogin.check(login));
		// check length
		login = "";

		Assert.assertEquals(false, CheckLogin.check(login));
		login = "alsdfkkkkkkkkkslfdkka";
		Assert.assertEquals(false, CheckLogin.check(login));
		login = "alsdfkkkkkkkkkslfdkk";
		Assert.assertEquals(true, CheckLogin.check(login));
		// check first symbol
		login = "1";
		Assert.assertEquals(false, CheckLogin.check(login));
		login = "A";
		Assert.assertEquals(true, CheckLogin.check(login));
		login = "a";
		Assert.assertEquals(true, CheckLogin.check(login));

		// check last symbol
		login = "A.";
		Assert.assertEquals(false, CheckLogin.check(login));
		login = "Aa.";
		Assert.assertEquals(false, CheckLogin.check(login));
		login = "Aa";
		Assert.assertEquals(true, CheckLogin.check(login));
		login = "A9";
		Assert.assertEquals(true, CheckLogin.check(login));

		// check other symbols
		login = "als1.-Kkk{kkkkslfdkk";
		Assert.assertEquals(false, CheckLogin.check(login));
		login = "als1.-Kkk[kkkkslfdkk";
		Assert.assertEquals(false, CheckLogin.check(login));
		login = "als1.-Kkk`kkkkslfdkk";
		Assert.assertEquals(false, CheckLogin.check(login));
		login = "als1.-Kkk:kkkkslfdkk";
		Assert.assertEquals(false, CheckLogin.check(login));
		login = "als1.-Kkk@kkkkslfdkk";
		Assert.assertEquals(false, CheckLogin.check(login));
		login = "als1.-Kkk/kkkkslfdkk";
		Assert.assertEquals(false, CheckLogin.check(login));
		login = "als1.-Kkk,kkkkslfdkk";
		Assert.assertEquals(false, CheckLogin.check(login));

		login = "als1.-Kkkkkkkkslfdkk";
		Assert.assertEquals(true, CheckLogin.check(login));

		start = System.currentTimeMillis();
		for (int i = 0; i < 1E6; i++) {
			login = "aa2aaaaaaaaaaaaaaaaa";
			CheckLogin.check(login);
		}

		end = System.currentTimeMillis();
		login = "aaaaaaaaaaaaaaaaaaa?";
		Assert.assertEquals(false, CheckLogin.check(login));

		System.out.println("time:" + (end - start));
	}

}
