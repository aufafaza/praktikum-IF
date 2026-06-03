#pragma once
#include "Player.hpp"
#include "Staff.hpp"

class PlayerCoach : public Player, public Staff {
private:
    int yearsAsPlayer;
    bool isCurrentlyPlaying;

public:
    PlayerCoach(const std::string& name, int age, const std::string& contractEnd,
                const std::string& position, int stamina, double rating,
                const std::string& license, int yearsAsPlayer, bool isCurrentlyPlaying);

    std::string getProfile() const override;
    void work() const override;
    std::string getSpecialty() const override;


    double calculateRating() const override;
    double calculateWage() const override;

    ~PlayerCoach() override;
};
