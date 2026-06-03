Memilih algoritma
1. A memecahkan persoalan ukuran n dengan membagi menjadi lima peresoalan berukuran n/2. Menyelseaikan esecara rekursif lalu digabung secara linier.
2. Algoritma B memecahkan persoalan berukuran n dan membagi menjadi 2 persoalan masing masing berukuran n-1, menyelesaikan persoalan secara rekursif lalu menggabung dalam waktu konstan
3. C memecahkan persoalan berukuran n dengan membagi menjadi 9 upa peresoalan berukuran n/3. Memecahkan secara rekursif lalu menggabung dalam waktu kuadratik. 

Untuk yang pertama, 
karena pembagian dibagi menjadi 5 subpersoalan, makak ita bisa buat rekurensnya menjadi 5T(n/2). Untuk penggabungan, karena linier menjadi cn.

T(n) = 5T(n/2) + cn 
Teorema master: 
a = 5 
b = 2
d  = 1 

5 > 2^1.
Maka dari itu, gunakan O(n^(logba))
therefore, O(n^log(2)5) --> O(nlog2)


2. Ini tidak bisa menggunakan teorema master.
Kita tahu bahwa diinginkan subpersoalan berukuran 2 subpersoalan yang berukuran n-1. 
Maka, kita dapat merumuskannya sebagai 
T(n) = 2T(n-1) + c 
misalkan untuk sebuah T(n-2)
T(n) = 2(2T(n-2) + c) + c = 4T(n-2) + 2c + c 
                            = 4(2T(n-3)+c) + 3c 
                            = 8T(n-3) + 7c 
                            = 2^kT(n-k)+(2^k-1)c
Misalkan diambil sebuah nilai k = n. 
T(n) = 2^n(T)(0) + 2(2^n-1) + c 
        = 0 + 2(2^n-1) + c 
        = 2(2^n-1) + c

Karena ktia mengabaikan nilai konstanta, 
therefore, O(2^n) 

3. 
T(n) = 9T(n/3) + cn^2
a = 9
b = 3
d = 2
9 = 9 
O(n^2logn) -> O(n^2logn)

