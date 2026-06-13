public class CountingSubscriber implements Subscriber {
    private final int limit;
    private int count = 0;

    public CountingSubscriber(int limit) {
        this.limit = limit;
    }

    /**
     * TODO:
     * - tambah `count` setiap kali menerima data
     * - cetak "[COUNT] <type> = <value> (<count>/<limit>)"
     * - jika `count` sudah mencapai `limit`, cetak
     *   "[UNSUB] CountingSubscriber done after <limit> messages"
     *   lalu return false (berhenti menerima data berikutnya)
     * - selain itu return true
     */
    @Override
    public boolean onData(SensorData data) {
        return true;
    }
}
