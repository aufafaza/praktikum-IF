# Soal 3 — Reflection: Read/Write Properties, Anotasi & Pembuatan Instance Dinamis

## Konteks

Buat utilitas generik (mirip mini-serializer) yang mengubah objek Java
menjadi/dari `Map<String,String>` menggunakan **anotasi** dan **reflection**.

## Anotasi (sudah lengkap)

- `@Property(String value)` — nama key eksternal untuk sebuah field.
- `@ReadOnly` — field tidak boleh diubah lewat `updateField`.
- `@DefaultValue(String value)` — nilai default dipakai `createInstance` jika
  key tidak ada di input.

## Kelas target: `Employee` (sudah lengkap)

```java
public class Employee {
    @Property("emp_id")
    @ReadOnly
    private int id;

    @Property("full_name")
    private String name;

    @Property("dept")
    @DefaultValue("GENERAL")
    private String department;

    @Property("salary")
    private double salary;
}
```

## Tugas (di kelas `ReflectUtil`)

1. `static Object createInstance(Class<?> clazz, Map<String,String> data)`
   - Buat instance baru lewat *no-arg constructor*
     (`clazz.getDeclaredConstructor().newInstance()`).
   - Untuk setiap field yang beranotasi `@Property("key")`:
     - ambil `data.get("key")`.
     - jika tidak ada dan field punya `@DefaultValue`, gunakan nilai default.
     - jika tidak ada dan tanpa default, biarkan nilai default Java (0 / null).
     - konversi `String` ke tipe field (`int`, `double`, `String`, ...) lalu
       `set` (gunakan `setAccessible(true)`).
   - Kembalikan instance yang sudah terisi.

2. `static Map<String,String> readProperties(Object obj)`
   - Untuk setiap field beranotasi `@Property`, baca nilainya secara
     reflektif dan masukkan ke map sebagai `String`, key = nama property.

3. `static boolean updateField(Object obj, String propertyName, String newValue)`
   - Cari field dengan `@Property` yang namanya cocok `propertyName`.
   - Jika field beranotasi `@ReadOnly` → **jangan ubah**, kembalikan `false`.
   - Selain itu, konversi `newValue` ke tipe field, set nilainya, kembalikan
     `true`.

4. `static void printSchema(Class<?> clazz)`
   - Untuk setiap declared field, cetak satu baris:
     ```
     <propertyName> : <typeName> [readonly=<true/false>] [default=<value atau "-">]
     ```

## Format input

```
CREATE Employee emp_id=1,full_name=John,dept=IT,salary=5000.0
SCHEMA Employee
READ
UPDATE emp_id 999
UPDATE full_name Jane
UPDATE dept HR
END
```

> Catatan: nilai pada `CREATE` tidak boleh mengandung spasi maupun koma.

## Contoh

Input:
```
CREATE Employee emp_id=1,full_name=John,salary=5000.0
SCHEMA Employee
READ
UPDATE emp_id 999
UPDATE dept HR
READ
END
```

Output (kira-kira):
```
CREATED Employee{id=1, name=John, department=GENERAL, salary=5000.0}
emp_id : int [readonly=true] [default=-]
full_name : String [readonly=false] [default=-]
dept : String [readonly=false] [default=GENERAL]
salary : double [readonly=false] [default=-]
{emp_id=1, full_name=John, dept=GENERAL, salary=5000.0}
READONLY: emp_id
UPDATED dept -> HR
{emp_id=1, full_name=John, dept=HR, salary=5000.0}
```

(Catatan: `dept` tidak diisi pada `CREATE`, sehingga jatuh ke `@DefaultValue`
= `GENERAL`.)

## Pertanyaan tambahan (diskusi)

- Mengapa `setAccessible(true)` diperlukan untuk field `private`?
- Apa perbedaan `Class.forName(...)` dengan `obj.getClass()` — kapan masing-
  masing dipakai di program ini?
- Bagaimana `@ReadOnly` mencegah `updateField` mengubah `id`, padahal
  `createInstance` tetap bisa mengisinya?
