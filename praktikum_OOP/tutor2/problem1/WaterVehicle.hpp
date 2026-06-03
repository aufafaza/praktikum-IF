#ifndef WATERVEHICLE_HPP
#define WATERVEHICLE_HPP
#include <iostream> 
#include "Vehicle.hpp"

using namespace std; 

class WaterVehicle : public Vehicle{
	private:
		int weight;
	public: 
		WaterVehicle(string vehicleId, string brand,int maxSpeed, int weight);
		~WaterVehicle();
		void sail(string dest) const; 
		string showSpec() const; 
		string getBrandName() const;
}; 

#endif
