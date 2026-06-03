#include "AirVehicle.hpp"

AirVehicle::AirVehicle(string vehicleId, string brand,int maxSpeed, int alt)
    : Vehicle((vehicleId), (brand), maxSpeed), alt(alt) {
  cout << "[CREATE] AirVehicle " << brand << " at max altitude " << alt << "m ready\n";
}

AirVehicle::~AirVehicle() {
  cout << "[DELETE] AirVehicle " << brand << " destroyed\n";
}


string AirVehicle::showSpec() const {
  return getVehicleId() + " | " + brand + " | MaxSpeed: " + to_string(maxSpeed);
}

void AirVehicle::fly(string dest) const{
	cout << "[FLY] " << brand << " (max " << alt << "m) flying to " << dest << endl;	
}


string AirVehicle::getBrandName() const{
	return brand;
}
