#include "PublicRecord.hpp"

PublicRecord::PublicRecord(std::string author, int key) : BaseRecord(author, key) {}; 

PublicRecord::~PublicRecord() = default; 

int PublicRecord::calculateClearance() const { 
    return 0; 
}