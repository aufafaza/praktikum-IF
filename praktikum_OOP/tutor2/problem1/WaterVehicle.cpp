#include "WaterVehicle.hpp"

WaterVehicle::WaterVehicle(string vehicleId, string brand,int maxSpeed, int weight)
    : Vehicle((vehicleId), (brand), maxSpeed), weight(weight) {
  cout << "[CREATE] WaterVehicle " << brand << " with " << weight << " tons displacement ready\n";
}

WaterVehicle::~WaterVehicle() {
  cout << "[DELETE] WaterVehicle " << brand << " destroyed\n";
}


string WaterVehicle::showSpec() const {
  return getVehicleId() + " | " + brand + " | MaxSpeed: " + to_string(maxSpeed);
}

void WaterVehicle::sail(string dest) const{
	cout << "[SAIL] " << brand << " (" << weight << " tons) sailing to " << dest << endl;
}
string WaterVehicle::getBrandName() const{
	return brand;
}
