#include "WeaponInventory.hpp"

void WeaponInventory::add(const string &id, const string &name, const string &type, int damage, int rarity){
Weapon w = Weapon(id, name, type, damage, rarity);
weapons.push_back(w); 
}

bool WeaponInventory::remove(const string &id){
for (auto it = weapons.begin(); it != weapons.end(); it++){
    if (it->id == id){
        weapons.erase(it);
        return true;
    }
}
return false; 
}

// ubah jadi std::find 
const Weapon *WeaponInventory::find(const string &id) const{
	auto it = std::find_if(weapons.begin(), weapons.end(), [&id](const Weapon& w){
			return w.id == id;
			});

	if (it != weapons.end()){
		return &(*it);
	}
	return nullptr;
}

void WeaponInventory::update(const string &id, const string &name, const string &type, int damage, int rarity){
	const Weapon* w = find(id); 

	if (w != nullptr){ 
		Weapon* mut = const_cast<Weapon*>(w); 
		mut->name = name; 
		mut->type = type; 
		mut->damage = damage; 
		mut->rarity = rarity; 
	} 
}
void WeaponInventory::sort(){
    std::sort(weapons.begin(), weapons.end(), [](const Weapon& a, const Weapon b){
        if (a.rarity != b.rarity){
            return a.rarity > b.rarity; 
        }
        if (a.damage != b.damage){
            return a.damage > b.damage; 
        }
        return a.id < b.id; 
    });
}

long long WeaponInventory::totalDamage(const string &type) const{
    long long sum = std::accumulate(weapons.begin(), weapons.end(), 0LL, [type](long long tempSum, const Weapon& a){
        if (a.type == type){
            return tempSum + a.damage;
        }else {
            return tempSum; 
        }
    });
    return sum; 
}

int WeaponInventory::countByRarity(int minRarity) const{
    int sum = std::accumulate(weapons.begin(), weapons.end(), 0LL, [minRarity](int tempSum, const Weapon& a){
        if (a.rarity >= minRarity){
            return tempSum + 1;
        }else {
            return tempSum; 
        }
    });
    return sum;
}

    void WeaponInventory::printByType(const string &type) const{
            bool found = false ;
for (const auto& weapon : weapons){ 
    if (weapon.type.find(type) != string::npos){
        cout << weapon.id << "|" << weapon.name << "|" << weapon.type << "|" << to_string(weapon.damage) << "|" << to_string(weapon.rarity) << endl;
        found = true;
    }
} 
if (!found) cout << "EMPTY" << endl; 
    }

void WeaponInventory::print() const{
    if (weapons.empty()) {
    cout << "EMPTY" << endl;
    return;
}
for (const auto& weapon : weapons) {
        cout << weapon.id << "|" << weapon.name << "|" << weapon.type << "|" << to_string(weapon.damage) << "|" << to_string(weapon.rarity) << endl;
}
}
int WeaponInventory::upgradeAll(const string &type, int bonusDamage){
    int count = 0; 
    for (auto it = weapons.begin(); it != weapons.end(); it++){
        if (it->type == type){
        it->damage += bonusDamage; 
        count++;
        }
    }
    return count;
}

