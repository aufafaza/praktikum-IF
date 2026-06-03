public class Arsip {
	private Pesan[] listPesan;
	private int cap;

	public Arsip() {
		listPesan = new Pesan[100];
		cap = 0;
	}

	public void tambah(Pesan pesan) {
		if (cap < 100) {
			listPesan[cap] = pesan;
			cap++;
		}
	}

	public Pesan get(int index) {
		if (index >= 1 && index <= cap) {
			return listPesan[index - 1];
		}
		return null;
	}

	public int cari(String keyword) {
		int cnt = 0;
		String low = keyword.toLowerCase();

		for (int i = 0; i < cap; i++) {
			if (listPesan[i].getTeks().toLowerCase().contains(low)) {
				cnt++;
			}
		}
		return cnt;
	}

	public int jumlah() {
		return cap;
	}
}
