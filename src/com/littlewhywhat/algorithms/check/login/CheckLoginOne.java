package com.littlewhywhat.algorithms.check.login;

public class CheckLoginOne implements CheckLogin {
	public boolean check(String login) {
		if (login == null)
			return false;
		int length = login.length();
		if (length == 0 || length > 20)
			return false;
		char firstSymbol = login.charAt(0);
		if ((firstSymbol >= 'A' && firstSymbol <= 'Z')
				|| (firstSymbol >= 'a' && firstSymbol <= 'z')) {
			length--;
			if (length == 0)
				return true;
			else {
				int lastSymbol = login.charAt(length);
				if ((lastSymbol >= 'A' && lastSymbol <= 'Z')
						|| (lastSymbol >= 'a' && lastSymbol <= 'z')
						|| (lastSymbol >= '0' && lastSymbol <= '9')) {
					for (int i = 1; i < length; i++) {
						int symbol = login.charAt(i);
						if (!((symbol >= 'A' && symbol <= 'Z')
								|| (symbol >= 'a' && symbol <= 'z')
								|| (symbol >= '0' && symbol <= '9')
								|| (symbol == '-') || (symbol == '.')))
							return false;
					}
					return true;
				}
			}
		}
		return false;

	}
}
