 Terdapat sebuah timbangan. Digunakan untuk menentukan satu bola dari 9 buah bola, ya intinya bola palsu problem. 
a. Tentukan langkah penimbangan sebanyak dua kali untuk mendapatkan satu bola yang lebih berat dibandingkan bola lain. Tentukan termasuk DnC mana. 

Kita dapat mengugnakan decrease by constant factor. Dalam hal ini, kita hanya perlu membagi menjadi base case bola ada 3.

Tapi sebelum itu, kita dapat membagi menjadi 3 kelompok bola yang masing-masing memiliki 3 bola. Tinggal dua kelompok A dan B ditimbang, kalo sama berarti C ada yang berat. Terus sampe base case, kan? Nah dari situ, tinggal dilihat A dan B. Pasti dapat. yeay. 


Analsisi kompleksitas. 

T(n) = T(n/3) + 1 
O(n) = O(n)



