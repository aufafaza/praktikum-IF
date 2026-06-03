public class LaciAngka<T extends Number> extends Laci<T> {
    public LaciAngka(String label) {
        super(label);
    }

    public double total() {
        double sum = 0;
        for (int i = 1; i <= this.ukuran(); i++) {
            sum += this.ambil(i).doubleValue();
        }
        return sum;
    }

    public double rataRata() {
        if (this.ukuran() != 0) {
            return total() / this.ukuran();
        }
        return 0.0;
    }

}
