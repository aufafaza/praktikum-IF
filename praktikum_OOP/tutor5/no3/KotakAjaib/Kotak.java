import java.util.ArrayList;

public class Kotak<T extends Barang> {
    private ArrayList<T> items;
    private int kapasitas;

    public Kotak(int kapasitas) {
        this.items = new ArrayList<>();
        this.kapasitas = kapasitas;
    }

    public boolean tambah(T item) {
        if (!penuh()) {
            items.add(item);
            return true;
        }
        return false;
    }

    public T ambil() {
        if (kosong()) {
            return null;
        }
        int lastIndex = items.size() - 1;
        T item = items.get(lastIndex);
        items.remove(lastIndex);
        return item;
    }

    public T lihat(int index) {
        if (index < 0 || index >= items.size())
            return null;
        return items.get(index);
    }

    public int jumlah() {
        return items.size();
    }

    public int kapasitas() {
        return this.kapasitas;
    }

    public boolean penuh() {
        return jumlah() == kapasitas();
    }

    public boolean kosong() {
        return jumlah() == 0;
    }
}
