# Computer and task 
Setiap prosesor dapat menjalankan banyak task secara sekuensial asalkan waktunya tidak beririsan (Ti + Di <= Ti+1). Given 7 task: 
(Ti, Di) = [(2, 3), (0, 4), (8, 4), (5, 6), (9, 3), (8, 1), (12, 2)]. Explain the greedy 

Decomposition. 
1. Kita dapat memilih suatu task secara bebas berdasarkan durasi waktu penyelesaian yang paling kecil. Asumsikan sebuah prosesor dibuat baru jika tidak memungkinkan untu k sebuah task dimasukkan dalam prosesor tersebut. 
2. Kemudian kita dapat mengambil task dengan fungsi kelayakan yaitu Ti + Di <= Ti+1. Agar fungsi objektif tercapai, yaitu nilai minimum dari jumlah prosesor. Kita dapat mengambil sebuah task dengan waktu mulai Ti+1 yang memiliki D minimum sesuai dengan fungsi kelayakan. 
3. Merujuk (1), buat prosesor baru jika sudah tidak dapat memasukkan task kembali. 

a. Berapa jumlah minimal prosesor yang dibutuhkan 

1. Ambil (D minimum) [8,1]. 
2. Bentuk urutan task untuk prosesor 1, berdasarkan T maksimum. 

Untuk p1, 
urutan pengambilan: 
    - 8,1 (8 -- 9)
    - 12, 2 (12 -- 14)
    - 2, 3 (2 -- 5) 
    - 9, 3 (9--12)
untuk p2, 
    - 0, 4 (0 -- 4) 
    - 8, 4 (8 -- 12)
    - 5, 6 (5 -- 11) 

\therefore, dibutuhkan 2 prosesor. 

b. Jika task memiliki D > 4 dapat dipecah menjadi duatask (paralel dengan beda prosesor). Apakah hasilny akan lebih optimal? 
Asumsi: IF D ganjil maka akan dibagi menjadi bilangan genap dan ganjil yang kemudian akan dipilih nilai terkecil terlebih dahulu. kemudian, asumsikan bahwa diprioritaskan prosesor yang sudah ada, dibanding yang belum. 

Untuk p1, 
    - 8, 1 
    - 12, 2
    - 2, 3
    - 9, 3
untuk p2, 
    -

Konklusi, ya, akan lebih optimal jika prosesor sebelumnya belum penuh akan task dan terdapat sebuah waktu mulai kosong yang dapat diisi oleh task yang dipartisi. Akan tidak optimal jika harus selalu membuat prosesor baru. 

Okeh jawwaban ini salah, karena dilewatkan sebuah langkah penting yaitu sorting di awal. Evaluasinya, harus mengetahui dulu sebenernya apa yang harus diurutin. 

Harusnya, untuk majoritas problem greedy, kita sepatutnya mengurutkan berdasarkan suatu parameter. Dalam konteks ini, lebih baik digunakan pengurutan berdasarkan waktu mulai. 

Artinya, kita dapat mengurutkan 
Ti, Di = 0,4 2,3 5,6 8,1 8,4 12,2 

Gunakan fungsi kelayakan berupa tidak tumpang tindih antara waktu mulai dan waktu mulai dijumlahkan dengan durasi. 

P1 
0,4 5,6 12,2 
P2
2,3 5,6 8,1 
P3
8,4 

Untuk jawaban b, tinggal evaluasi aja dari yang P1 di bagian 5,6. Intinya jadi bakal lebih optimal. 

