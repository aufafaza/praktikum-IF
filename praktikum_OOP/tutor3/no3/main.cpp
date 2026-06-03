#include "CartManager.hpp"
#include <iostream> 

using namespace std; 
int main() {
    CartManager cm;
    int q;
    
    if (!(cin >> q)) return 0;

    cout << fixed << setprecision(2);

    for (int i = 0; i < q; i++) {
        string op;
        cin >> op;

        if (op == "ADD") {
            string title; float price;
            cin >> title >> price;
            cm.addToCart(title, price);
        } else if (op == "REMOVE") {
            string title; cin >> title;
            if (cm.removeFromCart(title))
                cout << "Removed: " << title << "\n";
            else
                cout << "Not found: " << title << "\n";
        } else if (op == "CHECKOUT") {
            cm.checkout();
        } else if (op == "TOTAL") {
            cout << "Cart total: $" << cm.cartTotal() << "\n";
        } else if (op == "DISCOUNT") {
            float pct; cin >> pct;
            cout << "After discount: $" << cm.applyDiscount(pct) << "\n";
        } else if (op == "COUNT") {
            string title; cin >> title;
            cout << title << ": " << cm.countInCart(title) << " in cart\n";
        } else if (op == "PRINT_CART") {
            cm.printCart();
        } else if (op == "PRINT_PURCHASED") {
            cm.printPurchased();
        } else if (op == "PURCHASED_COUNT") {
            cout << "Total purchased: " << cm.totalPurchased() << "\n";
        }
    }

    return 0;
}
