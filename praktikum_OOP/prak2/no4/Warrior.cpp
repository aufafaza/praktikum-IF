#include <iostream> 
#include "Warrior.hpp" 


using namespace std; 

Warrior::Warrior(string characterId, string name, int hp, int level, int strength) : Character(characterId, name, hp, level), strength(strength){
    cout << "[CREATE] Warrior " + this->name + " with " + to_string(this->strength) + " str ready" << endl; 
}    

int Warrior::getStrength() const {
    return this->strength;
}

void Warrior::attack() const { 
    cout << "[ATTACK] " + this->name + " attacks with " + to_string(this->getStrength()) + " strength" << endl; 

}

Warrior::~Warrior(){
    cout << "[DELETE] Warrior " + this->name + " destroyed" << endl; 
} 
