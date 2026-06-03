#include "Paper.hpp"

int main() {
    Paper *a = new Paper('A');
    Paper b('B');
    Paper *c = new Paper('C');

    Paper d(*c); 

    a->fold();
    b.fold();
    d.fold();

    d.glue(); 

    delete a; 

    d.setName('X'); 

    c->fold(); 
    c->glue();
    
    delete c; 

    return 0;
}
