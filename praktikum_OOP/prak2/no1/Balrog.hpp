#include "Maiar.hpp" 

class Balrog : public Maiar { 
	private: 
		string whipName; 
	public: 
		Balrog(string name, int hp, int power, string whipName); 
		~Balrog() = default; 
		void describe() const; 
		void rage(); 
}; 
