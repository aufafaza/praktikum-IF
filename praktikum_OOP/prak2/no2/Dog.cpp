#include "Dog.hpp"

using namespace std; 
Dog::Dog(string name, int age, bool trained) : Pet(name, age), trained(trained){
    cout << "[DOG] " + this->name + " entered as " + (trained ? "trained" : "untrained") + " dog" << endl;
}
Dog::~Dog() {
    cout << "[DOG] " + this->name + " barks goodbye" << endl; 
}

void Dog::makeSound() const{ 
    cout << "[SOUND] " + this->name + ": Woof!" << endl; 

} 
void Dog::play(){
    (trained ? this->happiness += 25 : this->happiness+=10);
    cout << "[PLAY] " + this->name + " " + (trained ? "fetches the ball!" : "runs around!") + " Happiness: " + to_string(this->happiness) << endl; 
}
