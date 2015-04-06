package com.littlewhywhat.algorithms.parser;

public class IpParser {

	public static String parseIP(int PrevDivident)
	{
		final int DIVIDER = 256;
		final byte size = 4;
		byte[] results = new byte[size];
		for (int i = 0; i < size; i++) {
			int Divident = PrevDivident/ DIVIDER;
			results[i] = (byte)(PrevDivident - Divident * DIVIDER);
			PrevDivident = Divident;
		}
		return results[3] + "." + results[2] + "." + results[1] + "." + results[0];
	}


}
