#include "Authenticator.hpp"
#include <cstddef>

using namespace std; 

Authenticator::Authenticator(){
	this->userCount = 0; 
	this->currentUser = nullptr;
	this->secretResource = "Ini adalah data rahasia!";
	for (int i = 0; i < MAX_USERS; i++) users[i] = nullptr; 
}

void Authenticator::registerUser(const string &username, const string &password, bool isAdmin){
	if (userCount >= MAX_USERS){
		cout << "Gagal: Kapasitas user penuh!" << endl; 
		return; 
	} 
	
	for (int i = 0; i < userCount; i++){ 
			if (users[i]->getUsername() == username){
				cout << "Gagal: Username sudah digunakan!" << endl; 
				return; 
			} 
	}
	if (isAdmin) { 
		users[userCount] = new AdminUser(username, password);
	}else if (!isAdmin){
		users[userCount] = new User(username, password); 
	} 

	cout << "Sukses: User " + username + " berhasil terdaftar!" << endl;
	userCount++; 
}

void Authenticator::login(const string &username, const string &password){
	bool uCheck = false; 
	bool pCheck = false; 
	int index = -1;
	for (int i = 0; i < userCount; i++){ 
		if (users[i]->getUsername() == username){
			uCheck = true; 
			if (users[i]->checkPassword(password)){
					pCheck = true;
					index = i; 
			}
		}
	} 

	if (!uCheck || !pCheck) { 
		cout << "Gagal: Username atau password salah!" << endl; 
		return; 
	}else{
		cout << "Sukses: Login berhasil! Selamat datang, " + username + "." << endl; 
		currentUser = users[index];  
	} 
	
}

void Authenticator::logout(){ 
	if (currentUser == nullptr){ 
		cout << "Gagal: Tidak ada user yang sedang login!" << endl; 
		return; 
	} 
	cout << "Sukses: User " + currentUser->getUsername() + " telah logout." << endl; 
	currentUser = nullptr; 
} 

void Authenticator::accessResource() const{
	if (currentUser == nullptr){ 
		cout << "Akses ditolak! Silakan login terlebih dahulu." << endl; 
		return; 
	} 

	cout << "Resource: " + this->secretResource << endl; 
	return; 
}

void Authenticator::setResource(const string &newResource){
	if (currentUser == nullptr){ 
		cout << "Gagal: Tidak bisa mengubah resource! Silakan login terlebih dahulu." << endl; 
		return;
	} 
	if (!currentUser->isAdmin()){
		cout << "Gagal: Tidak bisa mengubah resource! Hanya admin yang dapat melakukan ini." << endl; 
		return;
	} 
	this->secretResource = newResource; 
	cout << "Sukses: Resource telah diperbarui oleh " + currentUser->getUsername() + "." << endl; 
	return; 
}

Authenticator::~Authenticator(){
	for (int i = 0; i < userCount; i++){
		delete users[i]; 
		users[i] = nullptr; 
	} 
} 
