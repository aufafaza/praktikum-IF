public abstract class Pesan {
	protected String t;

	public Pesan(String teks) {
		this.t = teks;
	}

	public abstract String process();

	public String getTeks() {
		return t;
	}

	@Override
	public String toString() {
		return t;
	}
}
