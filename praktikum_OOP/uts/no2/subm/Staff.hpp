#pragma once
#include "ClubMember.hpp"
#include <string>

class Staff : virtual public ClubMember {
protected:
    std::string coachingLicense;
    std::string department;

public:
    Staff(const std::string& name, int age, const std::string& contractEnd,
          const std::string& license, const std::string& dept);

    virtual std::string getSpecialty() const = 0;

    double calculateWage() const override;
    virtual ~Staff();
};
