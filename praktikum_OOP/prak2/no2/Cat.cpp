#include "Cat.hpp"

using namespace std; 
Cat::Cat(string name, int age, bool indoor) : Pet(name, age), indoor(indoor){
    cout << "[CAT] " + this->name + " entered as " + (indoor ? "indoor" : "outdoor") + " cat" << endl;
}
Cat::~Cat() {
    cout << "[CAT] " + this->name + " meows goodbye" << endl; 
}

void Cat::makeSound() const{ 
    cout << "[SOUND] " + this->name + ": Meow!" << endl; 

} 
void Cat::play(){
    (indoor ? this->happiness += 15 : this->happiness+=20);
    cout << "[PLAY] " + this->name + " " + (indoor ? "plays with yarn!" : "chases mice!") + " Happiness: " + to_string(this->happiness) << endl; 
}
void Cat::feed(){
    this->happiness += 8;
    cout << "[FEED] " + this->name + " eats fish! Happiness: " + to_string(this->happiness) << endl; 
}
