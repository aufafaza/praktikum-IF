#pragma once
#include "Creature.hpp" 

class Elf : public Creature{ 
	protected: 
		int grace; 
	public:
		Elf(string name, int hp, int grace); 
		~Elf() = default;
		virtual void describe() const override; 
		int getGrace() const; 
};

