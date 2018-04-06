package urlshortener;

import java.util.TreeSet;

public class URLID {
	private static TreeSet<Character> SYMBOLS;

	public URLID () {
		SYMBOLS = new TreeSet<>();
		for (char c = 'A'; c <= 'Z'; c++) {
			SYMBOLS.add(c);
		}
		for (char c = 'a'; c <= 'z'; c++) {
			SYMBOLS.add(c);
		}
		for (char c = '0'; c <= '9'; c++) {
			SYMBOLS.add(c);
		}

		SYMBOLS.add('-');
		SYMBOLS.add('.');
		SYMBOLS.add('_');
		SYMBOLS.add('~');
	}

	public String first () {
		return SYMBOLS.first().toString();
	}

	public String next (String id) {
		StringBuilder result = new StringBuilder(id);
		for (int i = id.length() - 1; i >= 0; i--) {
			Character nextSymbol = SYMBOLS.higher(id.charAt(i));
			if (nextSymbol != null) {
				result.setCharAt(i, nextSymbol);
				return result.toString();
			} else {
				result.setCharAt(i, SYMBOLS.first());
			}
		}

		result.insert(0, SYMBOLS.first());
		return result.toString();
	}
}
