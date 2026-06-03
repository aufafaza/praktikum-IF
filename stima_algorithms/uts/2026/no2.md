Diberikan sebuah larik dengan n elemen yang berisi nilai riil. Kita ingin mencari perbedan maksimum antara dua elemen dalam larik. Perbedaan maksimum adalah 17.25. 

1. Bruteforce
\foreach i \in A --> selisih(i to j) 

O(n^2). 

2. Divide and conquer. 

1. Bagi elemen dengan dikalikan dengan 1/2, hingga basis = 1 
2. Jika n == 1: 
    - ditemukan nilai minimum, maksimum, dan selisih; yaitu arr[i]  
 3. Saat merge: 
    - Berdasarkan nilai rekursi sebelumnya, kita dapat mencari: 
        - nilai minimum global
        - nilai maksimum global
        - nilai selisih
    Selanjutnya, hitung nilai maksum selisih global berdasarkan hasil rekursi sebelumnya, dan nilai selisih dalam depth rekursi yang sedang dievaluasi. 

pseudocode 

procedure solve(input array x, int i, int j, output int minNum, int maxNum, int globalDifferenceMax) 
    if (i == j) then 
        return arr[0], i, j, arr[0], arr[0], arr[0]
    else 
        mid <- (i + j) / 2
        left <-- solve(i .. mid ) 
        right <-- solve (mid + 1.. j) 
        // untuk setiap langakh rekursinya, 
        minNum <-- min(left.minNum, right.minNum) 
        maxNum <-- max(left.maxNum, right.maxNum) 
        globalDifferenceMax <-- max((left.globalDifferenceMax), right.globalDifferenceMax, maxNum - minNum) 

        return globalDifferenceMax 

kompleksitasnya: 
2T(n/2) + c 
berdasarkan master theorem, 
a = 2, b = 2, d = 0 

a > b^d 

artinya, kompleksitas adalah O(n^(log2(2))
O(n) 


