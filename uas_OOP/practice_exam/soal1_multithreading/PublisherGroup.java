import java.util.List;

/**
 * Menjalankan sekumpulan Publisher secara konkuren (masing-masing thread-nya
 * sendiri), lalu menunggu semuanya selesai.
 */
public class PublisherGroup {
    private final List<Publisher> publishers;

    public PublisherGroup(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    /**
     * TODO:
     * - start() setiap Publisher pada `publishers` (agar berjalan konkuren).
     * - lalu join() setiap Publisher tersebut, supaya method ini baru
     *   return setelah SEMUA publisher selesai mempublish.
     */
    public void runAll() throws InterruptedException {
        // TODO
    }
}
