# Define your variables
CXX = g++
TARGET = app
# Only list the source (.cpp) files here
SOURCES = Vehicle.cpp AirVehicle.cpp WaterVehicle.cpp LandVehicle.cpp Car.cpp Drone.cpp Submarine.cpp main.cpp

# The rule to build the app
$(TARGET): $(SOURCES)
	$(CXX) $(SOURCES) -o $(TARGET)

# Clean rule to remove the executable
clean:
	rm -f $(TARGET)
