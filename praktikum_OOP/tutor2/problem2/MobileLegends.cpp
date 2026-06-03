#include "Layla.hpp"
#include "Tigreal.hpp"

int main() {
    Layla* layla1 = new Layla(10, 1000);
    layla1->useSkill();
    Layla* layla1Clone = new Layla(*layla1);
    layla1->heal(5);

    Layla* layla2 = new Layla(50, 200);
    layla2->moveTo(15, 30);
    *layla2 = *layla1;  
    
    Tigreal tigreal1(200, 10);
    tigreal1.moveTo(5, 15);
    tigreal1.taunt(10);
    Tigreal tigreal1Clone(tigreal1);

    Tigreal tigreal2(400, 20);
    tigreal2.sacredHammer();
    Tigreal tigreal2Clone(tigreal2);

    tigreal1.useSkill();
    tigreal2.useSkill();
    
    Tigreal tigreal3(300, 15);
    tigreal3.moveTo(10, 20);
    Tigreal* tigreal3Clone = new Tigreal(tigreal3);
    tigreal3Clone->taunt(5);
    

    delete tigreal3Clone;
    
    delete layla2;
    delete layla1Clone;
    delete layla1;


    return 0;
}