public interface Subscriber {
    /**
     * @return true jika subscriber ingin tetap menerima data berikutnya,
     *         false jika ingin berhenti (unsubscribe) setelah pesan ini.
     */
    boolean onData(SensorData data);
}
