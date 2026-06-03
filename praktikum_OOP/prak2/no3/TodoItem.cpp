#include "TodoItem.hpp" 

using namespace std; 

TodoItem::TodoItem(const string& name ) : Activity(name), isDone(0) {} 

string TodoItem::getStatus() const{
    if (isDone){ 
        return "[TODO] " + this->name + " - Selesai"; 
    } else {
        return "[TODO] " + this->name + " - Belum"; 
    }
} 

int TodoItem::complete() { 
    if (!this->isDone) { 
        this->isDone = true; 
        return 10; 
    }else {
        return 0; 
    }
}

TodoItem::~TodoItem(){
    cout << "Menghapus TodoItem " + this->name << endl;
}