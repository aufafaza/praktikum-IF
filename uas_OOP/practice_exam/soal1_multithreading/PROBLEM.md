# Soal 1 — Multithreading & Observer Pattern (ROS-like Broker)

## Konteks

Kamu akan membuat simulasi sederhana **broker** seperti pada ROS (Robot
Operating System): broker mengelola banyak **topic** (misalnya
`"temperature"`, `"humidity"`). Setiap topic punya buffer **terbatas
(bounded)** dan daftar **Subscriber** (Observer pattern) yang menerima
notifikasi setiap kali ada data baru di topic tersebut. Satu **Dispatcher**
thread berjalan per topic.

Sinkronisasi antar thread **harus** menggunakan `wait()` / `notify()` /
`notifyAll()` di dalam blok/method `synchronized`. **Tidak boleh** menggunakan
`BlockingQueue`, `Thread.sleep` sebagai pengganti sinkronisasi, atau busy-wait
(`while(true) { if (...) ... }` tanpa `wait()`).

## Struktur kelas

- `SensorData` — pesan (`type` = nama topic, `value`). `type.equals("END")`
  adalah **poison pill** (tanda topic tersebut harus berhenti).
- `Subscriber` — interface Observer:
  `boolean onData(SensorData data)`. Return `true` = tetap berlangganan,
  `false` = **unsubscribe** dirinya sendiri setelah pesan ini.
- `LoggerSubscriber` — sudah lengkap, selalu mencetak data dan return `true`.
- `AlertSubscriber` — **TODO**: punya `threshold`, mencetak peringatan jika
  `value > threshold`, selalu return `true`.
- `CountingSubscriber` — **TODO**: punya `limit`. Setiap menerima data,
  hitung berapa kali sudah menerima; setelah mencapai `limit`, cetak pesan
  unsubscribe dan return `false`.
- `Topic` — **TODO**: buffer (`Queue<SensorData>`) dengan kapasitas tetap
  (`capacity`) + daftar subscriber.
  - `publish(SensorData data)`: jika buffer **penuh**, publisher harus
    `wait()` sampai ada slot kosong; setelah menambah data, `notifyAll()`.
  - `take()`: jika buffer **kosong**, consumer harus `wait()` sampai ada
    data baru; setelah mengambil data, `notifyAll()` (membangunkan
    publisher yang mungkin menunggu slot kosong).
  - `snapshotSubscribers()`: kembalikan **salinan** daftar subscriber, agar
    Dispatcher bisa iterasi dengan aman walau ada `unsubscribe` di tengah
    iterasi.
- `Broker` — **TODO**: `Map<String, Topic>` dari nama topic ke `Topic`-nya
  (lazy-create dengan `getOrCreateTopic`).
- `Dispatcher` — **TODO**: thread per topic, loop: `take()` dari topic-nya,
  lalu panggil `onData()` ke setiap subscriber pada `snapshotSubscribers()`.
  Jika `onData` mengembalikan `false`, panggil `topic.unsubscribe(...)`.
  Berhenti saat menerima poison pill.
- `Publisher` — **TODO**: `Thread` yang merepresentasikan satu node ROS;
  punya daftar `value` yang akan di-publish berurutan ke satu topic.
- `PublisherGroup` — **TODO**: menjalankan banyak `Publisher` secara
  konkuren lalu menunggu semuanya selesai.

## Tugas

1. Implementasikan `Topic.publish` dan `Topic.take` dengan bounded buffer
   (dua kondisi `wait`: penuh & kosong) — hindari *missed signal* dan
   *busy waiting*.
2. Implementasikan `Broker.getOrCreateTopic`.
3. Implementasikan `Dispatcher.run()`, termasuk logika unsubscribe dinamis.
4. Implementasikan `AlertSubscriber.onData` dan `CountingSubscriber.onData`.
5. Implementasikan `Publisher.run()` — publish setiap nilai pada `values`
   secara berurutan ke topic-nya lewat `broker.publish(...)`.
6. Implementasikan `PublisherGroup.runAll()` — `start()` semua `Publisher`
   (agar berjalan konkuren), lalu `join()` semuanya sehingga `runAll()`
   baru return setelah **semua** publisher selesai.

## Format input

```
SUBSCRIBE <topic> <spec>
PUBLISHER <topic> <value1,value2,...>
...
END
```

`<spec>` salah satu dari:
- `LOG` → `LoggerSubscriber`
- `ALERT:<threshold>` → `AlertSubscriber(threshold)`
- `COUNT:<limit>` → `CountingSubscriber(limit)`

Setiap baris `PUBLISHER` membuat satu thread `Publisher` baru yang akan
mempublish `value1, value2, ...` secara berurutan ke `<topic>`. Semua
`Publisher` baru benar-benar mulai berjalan (lewat `PublisherGroup.runAll()`)
**setelah** seluruh input selesai dibaca.

> Asumsi: setiap topic yang punya `PUBLISHER` sudah punya minimal satu
> `SUBSCRIBE` sebelumnya (sehingga dispatcher-nya sudah berjalan dan buffer
> tidak akan penuh selamanya).

## Contoh

Input:
```
SUBSCRIBE temperature LOG
SUBSCRIBE temperature ALERT:40.0
SUBSCRIBE humidity LOG
SUBSCRIBE humidity COUNT:1
PUBLISHER temperature 25.0,42.0
PUBLISHER humidity 80.0,90.0
END
```

Karena setiap topic punya Dispatcher-nya sendiri, urutan output **antar
topic** bisa berselang-seling (tidak deterministik). Tapi urutan output
**dalam satu topic** (sesuai urutan subscribe & publish) harus seperti ini:

Topic `temperature`:
```
[LOG] temperature = 25.0
[LOG] temperature = 42.0
[ALERT] temperature = 42.0 exceeds threshold 40.0
```

Topic `humidity`:
```
[LOG] humidity = 80.0
[COUNT] humidity = 80.0 (1/1)
[UNSUB] CountingSubscriber done after 1 messages
[LOG] humidity = 90.0
```

(`CountingSubscriber` berhenti setelah pesan pertama, sehingga tidak
menerima `humidity = 90.0`.)

## Pertanyaan tambahan (diskusi)

- Mengapa `wait()` harus dipanggil di dalam `while` (bukan `if`), baik di
  `publish` maupun `take`?
- Apa yang terjadi jika `Topic.capacity = 1` dan publisher mem-publish lebih
  cepat daripada Dispatcher memproses? Jelaskan urutan `wait`/`notifyAll`
  yang terjadi.
- Mengapa `Dispatcher` perlu mengiterasi `snapshotSubscribers()` (salinan)
  daripada list subscriber asli secara langsung?
- Bagaimana pola Observer terlihat pada `Dispatcher`, `Topic`, dan
  `Subscriber`? Bagian mana yang berperan sebagai *Subject* dan mana sebagai
  *Observer*?
- Apa yang terjadi jika `PublisherGroup.runAll()` memanggil `join()` segera
  setelah `start()` pada **setiap** publisher di dalam loop yang sama
  (`start(); join();` lalu lanjut ke publisher berikutnya), dibandingkan
  memisahkan menjadi dua loop (`start()` semua, lalu `join()` semua)?
- Jika ada dua `PUBLISHER` untuk topic yang sama (misalnya dua sensor
  `temperature`), apakah urutan data yang diterima `Dispatcher` untuk topic
  itu masih bisa dijamin? Mengapa?
