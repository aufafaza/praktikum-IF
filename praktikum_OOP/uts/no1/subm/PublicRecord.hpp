#ifndef PUBLIC_RECORD_HPP
#define PUBLIC_RECORD_HPP

#include "BaseRecord.hpp"

class PublicRecord : public BaseRecord{ 
public:  
    PublicRecord(std::string author, int key); 
    ~PublicRecord() override; 
    int calculateClearance() const override;
};


#endif
