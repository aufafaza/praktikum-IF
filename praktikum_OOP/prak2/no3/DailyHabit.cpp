#include "DailyHabit.hpp" 

using namespace std; 

DailyHabit::DailyHabit(const string& name ) : Activity(name), streak(0) {} 

string DailyHabit::getStatus() const{
    return "[HABIT] " + this->name + " - Streak: " + to_string(this->streak); 
} 

int DailyHabit::complete() { 
    this->streak += 1; 
    return 10 * streak; 
}

DailyHabit::~DailyHabit(){
    cout << "Menghapus DailyHabit " + this->name << endl;
}