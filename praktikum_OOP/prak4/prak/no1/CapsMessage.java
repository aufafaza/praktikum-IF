public class CapsMessage extends Pesan {

	public CapsMessage(String teks) {
		super(teks);
	}

	@Override
	public String toString() {
		return "[CAPS] " + super.toString();
	}

	@Override
	public String process() {
		return t.toUpperCase();
	}

	public int countVowels() {
		int cnt = 0;
		String low = t.toLowerCase();

		for (int i = 0; i < low.length(); i++) {
			char c = low.charAt(i);
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				cnt++;
			}
		}
		return cnt;
	}
}
