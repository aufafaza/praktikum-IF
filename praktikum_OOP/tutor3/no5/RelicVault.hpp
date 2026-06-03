#ifndef RELICVAULT_HPP
#define RELICVAULT_HPP

#include <string>
#include <vector>
#include "VaultException.hpp"
using namespace std; 
class RelicVault {
private:
    size_t maxCapacity;
    vector<string> storage;

    bool containsDigit(const std::string &name) const;
public:
    explicit RelicVault(size_t capacity);
    
    void push(const string& name);
    string pop();
    string top() const;
    
    size_t size() const;
    size_t capacity() const;
    bool empty() const;
};

#endif
