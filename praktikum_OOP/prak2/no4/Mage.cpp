#include <iostream> 
#include "Mage.hpp" 

using namespace std; 

Mage::Mage(string characterId, string name, int hp, int level, int mana) : Character(characterId, name, hp, level), mana(mana) {
    cout << "[CREATE] Mage " + this->name + " with " + to_string(this->mana) + " mana ready" << endl; 
}

int Mage::getMana() const {
    return this->mana;
}

void Mage::castSpell() const { 
    cout << "[CAST] " + this->name + " casts a spell using " + to_string(this->getMana()) + " mana" << endl; 
}

Mage::~Mage(){
    cout << "[DELETE] Mage " + this->name + " destroyed" << endl; 
}