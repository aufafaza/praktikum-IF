#include "Elf.hpp" 

class Ranger : public Elf{ 
	private: 
		int arrows; 
	public: 
		Ranger(string name, int hp, int grace, int arrows); 
		~Ranger() = default; 
		void describe() const; 
		void shoot(); 

};
