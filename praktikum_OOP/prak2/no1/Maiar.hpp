#include "Creature.hpp" 
#pragma once


class Maiar : protected Creature{ 
	protected: 
		int power;
		Maiar(string name, int hp, int power); 
	public:
		~Maiar() = default;
		virtual void describe() const;
		int getPower() const; 
}; 
