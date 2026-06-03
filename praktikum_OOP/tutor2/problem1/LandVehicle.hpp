#ifndef LANDVEHICLE_H
#define LANDVEHICLE_H

#include "Vehicle.hpp"
using namespace std; 

class LandVehicle : public Vehicle { 
	private:	
		int numWheels; 
	public:
		LandVehicle(string vehicleId, string brand, int maxSpeed, int numWheels); 
		~LandVehicle();
		void drive(string dest) const; 

}; 


#endif
