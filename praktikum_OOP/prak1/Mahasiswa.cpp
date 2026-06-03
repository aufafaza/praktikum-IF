#include <iostream> 
// float nilai int total_sks
using namespace std; 

class Mahasiswa{
    public: 
        //methods
        Mahasiswa();
        Mahasiswa(string nama); 
        void info(); 
        void tambahNilai(float nilai, int tot); 
        static int counter;
        std::string name; 
        std::string nim;
        float ipk; 
        int total_sks;  
};

int Mahasiswa::counter = 0;
Mahasiswa::Mahasiswa(){ 
    this->name = "NPC";
    this->nim = "135" + to_string(counter); 
    this->ipk = 0; 
    this->total_sks = 0;
    counter++; 
}

Mahasiswa::Mahasiswa(string nama){ 
    this->name = nama;
    this->nim = "135" + to_string(counter); 
    this->ipk = 0; 
    this->total_sks = 0;
    counter++; 
}

void Mahasiswa::info(){ 
	cout << "INFORMASI MAHASISWA" << endl; 
	cout << "Nama: " << this->name << endl; 
    cout << "NIM: " << this->nim << endl;
    printf("IPK: %.2f\n", this->ipk); 
    printf("SKS: %d\n", this->total_sks);
}

void Mahasiswa::tambahNilai(float nilai, int tot){
    float newIPK; 
    newIPK = ipk * total_sks + nilai * tot;
    newIPK /= total_sks + tot;
    this->ipk = newIPK;
    this->total_sks += tot;
}


// int main(){ 
//     Mahasiswa A("Aboob"); 
//     A.ipk = 0;
//     A.total_sks = 0; 
//     cout << A.name << endl;
//     A.info(); 
//     A.tambahNilai(4, 4);
//     cout << "-----------------------" << endl;
//     A.info(); 

//     A.tambahNilai(3.5, 4);
//     cout << "-----------------------" << endl;
//     A.info(); 

//     Mahasiswa B;
//     B.info();
//     B.tambahNilai(1, 4);
//     B.info();
//     B.tambahNilai(2, 4);
//     B.info();

//     Mahasiswa C("askfdljsadlkfj");
//     C.info();
// }

