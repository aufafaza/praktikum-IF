#include "Character.hpp" 

class Mage : virtual public Character{ 
    protected: 
        int mana; 
    public: 
        Mage(string characterId, string name, int hp, int level, int mana);
        void castSpell() const;
        int getMana() const; 
        ~Mage(); 
};