public class RepeatMessage extends Pesan {
	private int n;

	public RepeatMessage(String teks, int n) {
		super(teks);
		this.n = n;
	}

	@Override
	public String toString() {
		return "[REPEAT] " + super.toString();
	}

	public int getN() {
		return n;
	}

	@Override
	public String process() {
		String[] words = t.split(" ");
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < n; j++) {
				result.append(words[i]);
				if (i != words.length - 1 || j != n - 1) {
					result.append(" ");
				}
			}
		}
		return result.toString();
	}
}
