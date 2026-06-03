public class PangramMessage extends Pesan {

	public PangramMessage(String teks) {
		super(teks);
	}

	@Override
	public String process() {
		String[] words = t.split(" ");
		StringBuilder result = new StringBuilder();

		for (int i = words.length - 1; i >= 0; i--) {
			result.append(words[i]);
			if (i > 0) {
				result.append(" ");
			}
		}
		return result.toString();
	}

	public boolean isPangram() {
		boolean[] found = new boolean[26];
		String low = t.toLowerCase();
		int numFound = 0;

		for (int i = 0; i < low.length(); i++) {
			char c = low.charAt(i);
			if (c >= 'a' && c <= 'z') {
				int index = c - 'a';
				if (!found[index]) {
					found[index] = true;
					numFound++;
				}
			}
		}

		return numFound == 26;
	}
}
