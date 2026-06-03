
public class Umandana {
	/**
	 * Mengembalikan kata yang telah diubah menjadi bahasa Umandana
	 * Huruf a menjadi "aiden"
	 * Huruf i menjadi "ipri"
	 * Huruf u menjadi "upru"
	 * Huruf e menjadi "epre"
	 * Huruf o menjadi "opro"
	 * Huruf mati yang tidak diikuti huruf vokal menjadi huruf tersebut + "es"
	 * Suku kata "ng" yang tidak diikuti huruf vokal menjadi "strengen"
	 * Suku kata "ng" yang diikuti huruf vokal tetap menjadi "ng"
	 * Suku kata "ny" yang diikuti huruf vokal tetap menjadi "ny"
	 * Selain ketentuan di atas, huruf/karakter tidak diubah
	 * *
	 * 
	 * @param words
	 * @return kata yang telah diubah menjadi bahasa Umandana
	 * 
	 */
	public static String toUmandana(String words) {
		StringBuilder result = new StringBuilder();

		int n = words.length();

		for (int i = 0; i < n; i++) {
			char current = words.charAt(i);
			char next = (i + 1 < n) ? words.charAt(i + 1) : '\0';
			// char low = Character.toLowerCase(next);
			boolean isNextVowel = isVowel(next);
			if (current == 'n' && next == 'g') {
				char afterNg = (i + 2 < n) ? words.charAt(i + 2) : '\0';
				if (isVowel(afterNg)) {
					result.append("ng");
				} else {
					result.append("strengen");
				}
				i++;
				continue;
			}

			if (current == 'n' && next == 'y') {
				result.append("ny");
				i++;
				continue;
			}

			if (isVowel(current)) {
				result.append(getUmandana(current));
			} else if (Character.isLetter(current)) {
				if (isNextVowel) {
					result.append(current);
				} else {
					result.append(current).append("es");
				}
			} else {
				result.append(current);
			}

		}
		return result.toString();
	}

	public static boolean isVowel(char c) {
		char low = Character.toLowerCase(c);
		return (low == 'a' || low == 'i' || low == 'u' || low == 'e' || low == 'o');
	}

	private static String getUmandana(char c) {
		switch (Character.toLowerCase(c)) {
			case 'a':
				return "aiden";
			case 'i':
				return "ipri";
			case 'u':
				return "upru";
			case 'e':
				return "epre";
			case 'o':
				return "opro";
			default:
				return String.valueOf(c);
		}
	}

	/**
	 * Mengembalikan kata Umandana ke bentuk normal
	 * *
	 * 
	 * @param words kata dalam bahasa Umandana
	 * @return kata telah diubah ke bentuk normal
	 */
	public static String deUmandana(String words) {
		String result = words;
		result = result.replace("strengen", "ng");
		result = result.replace("aiden", "a");
		result = result.replace("ipri", "i");
		result = result.replace("upru", "u");
		result = result.replace("epre", "e");
		result = result.replace("opro", "o");
		result = result.replaceAll("([bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ])es", "$1");
		return result;
	}

}
