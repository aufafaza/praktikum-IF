#ifndef AIRVEHICLE_HPP
#define AIRVEHICLE_HPP

#include "Vehicle.hpp"


class AirVehicle : public Vehicle { 
	private: 
		int alt; 
	public:
		AirVehicle(string vehicleId, string brand,int maxSpeed, int altitude); 
		~AirVehicle();
		void fly(string dest) const; 
		string showSpec() const;
	
		string getBrandName() const;
};
#endif
