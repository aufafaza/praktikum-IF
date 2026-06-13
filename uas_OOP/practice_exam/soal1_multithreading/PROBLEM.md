# Soal 1 — Multithreading & Observer Pattern (ROS-like Topic)

## Konteks

Kamu akan membuat simulasi sederhana "topic" seperti pada ROS (Robot Operating
System): satu **Publisher** mengirim data sensor ke sebuah **Topic**, dan
beberapa **Subscriber** (Observer pattern) menerima notifikasi setiap kali ada
data baru.

Sinkronisasi antar thread **harus** menggunakan `wait()` / `notify()` /
`notifyAll()` di dalam blok/method `synchronized`. **Tidak boleh** menggunakan
`BlockingQueue`, `Thread.sleep` sebagai pengganti sinkronisasi, atau busy-wait
(`while(true) { if (...) ... }` tanpa `wait()`).

## Struktur kelas

- `SensorData` — pesan yang dikirim lewat topic (`type`, `value`). Sebuah
  `SensorData` dengan `type.equals("END")` dianggap **poison pill** (tanda
  stream berakhir).
- `Subscriber` — interface Observer: `void onData(SensorData data)`.
- `LoggerSubscriber` — sudah lengkap, mencetak setiap data yang masuk.
- `AlertSubscriber` — **TODO**: punya `threshold`, mencetak peringatan jika
  `value > threshold`.
- `Topic` — **TODO**: berisi buffer (`Queue<SensorData>`) dan daftar
  subscriber.
  - `publish(SensorData data)`: menambahkan data ke buffer lalu
    membangunkan thread yang menunggu.
  - `take()`: menunggu (dengan `wait()`) sampai buffer tidak kosong, lalu
    mengambil dan mengembalikan elemen pertama.
- `Dispatcher` — **TODO**: thread yang terus-menerus memanggil `topic.take()`
  lalu memanggil `onData()` ke **setiap** subscriber yang terdaftar (ini bagian
  Observer-nya: dispatcher = subject yang "menotifikasi" semua observer).
  Dispatcher harus berhenti ketika menerima poison pill.

## Tugas

1. Implementasikan `Topic.publish` dan `Topic.take` dengan benar (hindari
   *missed signal* dan *busy waiting*).
2. Implementasikan `Dispatcher.run()`.
3. Implementasikan `AlertSubscriber.onData`.
4. Pastikan program berhenti dengan bersih (gunakan `dispatcher.join()`).

## Format input

```
PUBLISH <type> <value>
PUBLISH <type> <value>
...
END
```

## Contoh

Input:
```
PUBLISH temperature 25.0
PUBLISH temperature 42.0
PUBLISH humidity 80.0
END
```

Dengan `AlertSubscriber` ber-threshold `40.0`, output yang diharapkan (urutan
LOG dan ALERT relatif terhadap urutan publish harus terjaga per subscriber):

```
[LOG] temperature = 25.0
[LOG] temperature = 42.0
[ALERT] temperature = 42.0 exceeds threshold 40.0
[LOG] humidity = 80.0
```

## Pertanyaan tambahan (diskusi)

- Mengapa `wait()` harus dipanggil di dalam `while` (bukan `if`)?
- Apa yang terjadi jika `publish` tidak memanggil `notifyAll()`?
- Bagaimana pola Observer terlihat pada `Dispatcher` dan daftar `Subscriber`?
