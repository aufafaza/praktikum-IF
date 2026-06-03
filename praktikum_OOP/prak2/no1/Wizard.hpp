#include "Maiar.hpp" 

class Wizard : private Maiar { 
	private: 
		string staffName;
	public:
		Wizard(string name, int hp, int power,  string staffName);
		~Wizard() = default; 
		void describe() const; 
		void cast(); 
};	 


