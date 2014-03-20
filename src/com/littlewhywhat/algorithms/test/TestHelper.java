package com.littlewhywhat.algorithms.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;

public class TestHelper {
	public static void checkAnswerOutput(String[] answers, String filePath) {
		File file = new File(filePath);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (String answer : answers) 
				Assert.assertEquals(answer, br.readLine());
			Assert.assertEquals(null, br.readLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
