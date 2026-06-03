#include "RelicVault.hpp"
#include <cctype>
using namespace std; 
RelicVault::RelicVault(size_t capacity) : maxCapacity(capacity) {}

bool RelicVault::containsDigit(const std::string &name) const {
    for (size_t i = 0; i < name.size(); i++) {
        if (std::isdigit(static_cast<unsigned char>(name[i])) != 0) {
            return true;
        }
    }
    return false;
}
void RelicVault::push(const string& name) {
    if (storage.size() >= maxCapacity) {
        throw FullVaultException(maxCapacity);
    }

    if (name.length() < 3) {
        throw InvalidRelicException(name, "nama terlalu pendek");
    }

    for (char c : name) {
        if (containsDigit(name)) {
            throw InvalidRelicException(name, "mengandung angka");
        }
    }

    storage.push_back(name);
}

string RelicVault::pop() {
    if (empty()) throw EmptyVaultException();
    string item = storage.back();
    storage.pop_back();
    return item;
}

string RelicVault::top() const {
    if (empty()) throw EmptyVaultException();
    return storage.back();
}

size_t RelicVault::size() const { return storage.size(); }
size_t RelicVault::capacity() const { return maxCapacity; }
bool RelicVault::empty() const { return storage.empty(); }
