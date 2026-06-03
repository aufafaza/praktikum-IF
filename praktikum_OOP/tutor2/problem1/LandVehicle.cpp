#include <iostream> 
#include <string> 
#include "LandVehicle.hpp"

using namespace std; 

LandVehicle::LandVehicle(string vehicleId, string brand, int maxSpeed, int numWheels) : Vehicle(vehicleId, brand, maxSpeed), numWheels(numWheels){ 
	cout << "[CREATE] LandVehicle " << brand << " with " << numWheels << " wheels ready" << endl; 
}

LandVehicle::~LandVehicle(){
	cout << "[DELETE] LandVehicle " << brand << " destroyed" << endl; 
} 

void LandVehicle::drive(string dest) const{ 
	cout << "[DRIVE] " << brand << " (" << numWheels << " wheels) driving to " << dest << endl; 
}
