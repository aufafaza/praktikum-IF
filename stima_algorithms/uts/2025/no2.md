Diberikan sebuah larik tidak berurut berisi n>=2 bilangan bulat. Asumsikan n perpangkatan 2. Kita ingin menemukan jarak terbesar d yang didefinisikan sebagai nilai maksikum dari x(j) - x(i).

a. Jika digunakan algoritma brute force. Maka langkah-langkahnya menjadi
    - Evaluasi seluruh kemungkinan jarak menggunakan iterator i dan iterator nested j
    - Kemudian,update nilai maksimum selisihnya 

b. Jika diselesaikan dengan dnc. Tulsikan langkah-langkahnya. Tentukan T(n) dalam relasi rekurens. Llau tentukan O-notasinya. 

Oke jadi gini. 
1. Division dibagi hingga terdapat sebuah array dengan j = i + 1. 
2. Untuk setiap langkahnya, akan dievaluasi dalam array kecil tersebut nilai selisih, max, minnya. 
3. Setelah setiap pembagian. Maka akan dicari nilai cross step dengan mengurangkan nilai maksimum dalam bagian kiri dan bagian kanan. 
4. Setelah itu, cari nilai max dari ketiga itu
5. Dalam setiap langkah evaluasinya, kita perlu mengetahui nilai maksimum untuk setiap subarraynya. Agar kemudian saat di-merge, ditemukan sebuah nilai cross dengan maks - min dari rekursi bawahnya. 

---- me trying to understand recursion O calculation 
A complexity of recursion tree is 
length of tree from node to leaf node * number of leaf nodes. 

Oke jadi untuk soal ini, untuk base case (yaitu pada saat ada dua variable dan terjadi perbandingan), maka akan dilakukan sebuah operasi yang bernilai konstan, kita misalkan itu sebagai c. 

Kemudian, saat dibagi. Kita ingin membuat sebuah sebuah array berdimensi mid - j dan i - mid. Maka akan dilakukan dua kali rekursi pembagian dua dari n.

therefore, T(n/2) * 2. 

lalu, dirumuskan sebagai berikut 
T(n) = 2T(n/2) + c. 
Misalkan untuk T(n/2),
T(n) = 2(2T(n/4) + ) + c = 4T(n/4) + 2c + c 
... dst

Sehingga ditemukan
T(n) = 2^kT(n/2^k) + c (sum of i = 0 to k-1) 2^i. 
Artinya, mudah bagi kita untuk. menentukan teorema masternya. 

Diketahui teorema master rumusnya adalah 
T(n) = aT(n/b) + cn^d. 
a = 2, b = 2, d = 0



