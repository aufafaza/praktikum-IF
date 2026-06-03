interface ISummoned {
    public boolean flip();

    // Mengubah kondisi kartu dari tertutup menjadi terbuka kemudian mengembalikan
    // true. Bila kartu sudah dalam keadaan terbuka, tidak terjadi apa-apa dan
    // mengembalikan false
    public void rotate();

    // Mengubah posisi kartu dari menyerang ke bertahan atau sebaliknya.
    public int getPositionValue();

    // Mengembalikan nilai attack jika posisi kartu menyerang. Mengembalikan nilai
    // defense jika posisi kartu bertahan.
    public void render();
    // Melakukan print ke terminal, sudah diimplementasi pada kelas SummonedMonster
    // yang telah diberikan.
}
