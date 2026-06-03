#include "Penganan.hpp"
#include <iostream>

int Penganan::uang = 0;
int Penganan::n_rumah = 0;

Penganan::Penganan() {
    this->keik = 0;
    this->panekuk = 0;
}

Penganan::Penganan(int keik, int panekuk) {
    this->keik = keik;
    this->panekuk = panekuk;
}

int Penganan::GetKeik() const {
    return this->keik;
}

int Penganan::GetPanekuk() const {
    return this->panekuk;
}

void Penganan::SetKeik(int k) {
    this->keik = k;
}

void Penganan::SetPanekuk(int p) {
    this->panekuk = p;
}

Penganan operator+(const Penganan& p1, const Penganan& p2) {
    Penganan::n_rumah++;
    return Penganan(p1.keik + p2.keik, p1.panekuk + p2.panekuk);
}

Penganan operator-(const Penganan& p1, const Penganan& p2) {
    int terjualKeik = (p1.keik < p2.keik) ? p1.keik : p2.keik;
    int terjualPanekuk = (p1.panekuk < p2.panekuk) ? p1.panekuk : p2.panekuk;
    
    Penganan::uang += (terjualKeik * 51) + (terjualPanekuk * 37);
    
    return Penganan(p1.keik - terjualKeik, p1.panekuk - terjualPanekuk);
}

Penganan operator^(const Penganan& p, const int n) {
    int sisaKeik = p.keik - n;
    int sisaPanekuk = p.panekuk - n;
    
    if (sisaKeik < 0) {
        Penganan::uang -= (0 - sisaKeik) * 51;
        sisaKeik = 0;
    }
    
    if (sisaPanekuk < 0) {
        Penganan::uang -= (0 - sisaPanekuk) * 37;
        sisaPanekuk = 0;
    }
    
    return Penganan(sisaKeik, sisaPanekuk);
}

Penganan operator^(const int n, const Penganan& p) {
    return p ^ n;
}

int Penganan::JumlahUang() {
    return uang;
}

int Penganan::HitungNRumah() {
    return n_rumah;
}

void Penganan::Print() {
    std::cout << this->keik << "keik-" << this->panekuk << "panekuk" << std::endl;
}
