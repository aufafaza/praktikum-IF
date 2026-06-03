#include "InternalRecord.hpp"


InternalRecord::InternalRecord(std::string author, int key) : BaseRecord(author, key) {}; 

InternalRecord::~InternalRecord() = default; 

int InternalRecord::peekSecurity() const { 
    return calculateClearance() * 2; 
}