#include "Parrot.hpp"

using namespace std; 
Parrot::Parrot(string name, int age, int vocabulary) : Pet(name, age), vocabulary(vocabulary){
    cout << "[PARROT] " + this->name + " entered knowing " + to_string(vocabulary) + " words" << endl;
}
Parrot::~Parrot() {
    cout << "[PARROT] " + this->name + " squawks goodbye" << endl; 
}

void Parrot::makeSound() const{ 
    cout << "[SOUND] " + this->name + ": Squawk! I know " + to_string(vocabulary) + " words!" << endl; 

} 
void Parrot::play(){
    this->vocabulary += 1; 
    this->happiness += 10; 
    cout << "[PLAY] " + this->name + " learns a new word! Vocabulary: " + to_string(this->vocabulary) + ", Happiness: " + to_string(this->happiness) << endl; 
}
void Parrot::feed(){
    this->happiness += 3;
    cout << "[FEED] " + this->name + " eats seeds! Happiness: " + to_string(this->happiness) << endl;  
}
