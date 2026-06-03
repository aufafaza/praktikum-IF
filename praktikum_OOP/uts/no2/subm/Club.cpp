#include "Club.hpp"
#include <iostream>

Club::Club(std::string name) : clubName(name) {}

void Club::addMember(ClubMember* member) {
    roster.push_back(member);
}

void Club::printSquadReport() const {
    std::cout << "\n======================================\n";
    std::cout << "  " << clubName << " - Squad Report\n";
    std::cout << "======================================\n";
    for (const auto& member : roster) {
        std::cout << member->getProfile() << "\n";
    }
}

void Club::runTraining() const {
    std::cout << "\n--- Match Day Actions ---\n";
    for (const auto& member : roster) {
        member->work();
    }
}

Club::~Club() {
    std::cout << "\n--- Releasing Club Roster ---\n";
    for (auto& member : roster) {
        delete member;
    }
}
