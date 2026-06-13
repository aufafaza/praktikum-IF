# Soal 2 — Inheritance, Composition & Stream API

## Konteks

Sistem peminjaman buku perpustakaan sederhana.

## Struktur kelas

- `Author` — *composition*: nama & negara penulis (sudah lengkap).
- `Book` — *composition*: punya sebuah `Author`, plus `title`, `genre`,
  `year`, dan status `borrowed` (sudah lengkap).
- `Member` — kelas abstrak (*inheritance*): `id`, `name`, `borrowedCount`,
  dan method abstrak `getMaxBorrowLimit()` (sudah lengkap).
- `RegularMember extends Member` — limit peminjaman 3 (sudah lengkap).
- `PremiumMember extends Member` — limit peminjaman 10 (sudah lengkap).
- `Library` — **TODO**: menyimpan
  - `Map<String, Book> catalog` (judul → buku),
  - `Map<String, Queue<String>> waitlists` (judul → antrean `id` member
    yang menunggu),
  - `Map<String, Member> members`.

## Tugas (di kelas `Library`)

1. `borrowBook(String title, String memberId)`
   - Jika buku tidak ada → cetak `DENIED <memberId> <title> NOT_FOUND`.
   - Jika buku tersedia dan member belum melewati `getMaxBorrowLimit()` →
     tandai buku dipinjam, `incrementBorrowed()`, cetak
     `BORROWED <title> by <memberId>`.
   - Jika buku tersedia tapi member sudah mencapai limit → cetak
     `DENIED <memberId> <title> LIMIT_REACHED`.
   - Jika buku sedang dipinjam orang lain → masukkan `memberId` ke
     `waitlists.get(title)` (sebuah `Queue`), cetak
     `WAITLISTED <memberId> for <title>`.

2. `returnBook(String title)`
   - Tandai buku tidak dipinjam, cetak `RETURNED <title>`.
   - Jika `waitlists.get(title)` tidak kosong, `poll()` member berikutnya
     dan langsung panggil `borrowBook(title, nextMemberId)` untuk dia.

3. **[Stream]** `Map<String, Long> countBooksByGenre()`
   - Kelompokkan seluruh buku di `catalog` berdasarkan genre, hitung
     jumlahnya (`Collectors.groupingBy` + `Collectors.counting()`).

4. **[Stream]** `List<String> topGenres(int n)`
   - Urutkan genre berdasarkan jumlah buku terbanyak (descending); jika
     seri, urutkan alfabetis. Kembalikan `n` nama genre teratas.

5. **[Stream]** `double averageYearByGenre(String genre)`
   - Rata-rata `year` semua buku dengan genre tersebut (0 jika tidak ada).

6. **[Stream]** `List<String> membersOverLimit()`
   - Daftar `id` member yang `borrowedCount > getMaxBorrowLimit()`
     (memanfaatkan polimorfisme — setiap subclass punya limit berbeda).

7. **[Stream]** `Map<String, Integer> waitlistSizes()`
   - Petakan `title -> jumlah anggota di waitlist`-nya, **hanya** untuk
     title yang waitlist-nya tidak kosong.

8. **[Stream]** `List<String> titlesWithWaitlist()`
   - Daftar `title` yang waitlist-nya tidak kosong, diurutkan alfabetis.

9. **[Stream]** `String longestWaitlistTitle()`
   - `title` dengan waitlist terpanjang. Jika ada lebih dari satu title
     dengan panjang sama, pilih yang alfabetis pertama. Jika semua waitlist
     kosong, kembalikan `"NONE"`.

10. **[Stream]** `List<String> waitlistSnapshot(String title)`
    - Kembalikan **salinan** urutan `memberId` pada waitlist `title`
      tersebut (gunakan `queue.stream()...collect(...)` — jangan `poll()`,
      karena queue aslinya tidak boleh berubah).

## Format input

```
ADD_BOOK title|author|country|genre|year
ADD_MEMBER id|name|type        (type = REGULAR atau PREMIUM)
BORROW title|memberId
RETURN title
GENRE_STATS
TOP_GENRES n
AVG_YEAR genre
WAITLIST_SIZES
WAITLIST_TITLES
LONGEST_WAITLIST
WAITLIST title
END
```

> Catatan: `title`, `name`, dll dianggap tidak mengandung spasi (gunakan `_`
> jika perlu, misalnya `Harry_Potter`).

## Contoh

Input:
```
ADD_BOOK Dune|Frank_Herbert|USA|SciFi|1965
ADD_BOOK Foundation|Isaac_Asimov|USA|SciFi|1951
ADD_BOOK Hobbit|JRR_Tolkien|UK|Fantasy|1937
ADD_MEMBER m1|Alice|REGULAR
ADD_MEMBER m2|Bob|REGULAR
ADD_MEMBER m3|Carol|PREMIUM
BORROW Dune|m1
BORROW Dune|m2
BORROW Dune|m3
GENRE_STATS
TOP_GENRES 1
AVG_YEAR SciFi
WAITLIST_SIZES
WAITLIST_TITLES
LONGEST_WAITLIST
WAITLIST Dune
RETURN Dune
WAITLIST Dune
END
```

Output (kira-kira):
```
BORROWED Dune by m1
WAITLISTED m2 for Dune
WAITLISTED m3 for Dune
{SciFi=2, Fantasy=1}
[SciFi]
1958.0
{Dune=2}
[Dune]
Dune
[m2, m3]
RETURNED Dune
BORROWED Dune by m2
[m3]
```

## Pertanyaan tambahan (diskusi)

- Mengapa `Book` "memiliki" `Author` adalah contoh *composition*, sedangkan
  `RegularMember extends Member` adalah *inheritance*?
- Apa keuntungan `getMaxBorrowLimit()` bersifat abstrak/polymorphic
  dibandingkan menyimpan limit sebagai field biasa di `Library`?
- Mengapa `waitlistSnapshot` harus menggunakan `queue.stream()...collect(...)`
  daripada `poll()` berulang? Apa yang akan rusak jika memakai `poll()`?
- Pada `longestWaitlistTitle`, bagaimana cara memastikan title yang dipilih
  saat seri adalah yang alfabetis pertama menggunakan `Comparator`?
