#ifndef SAFE_BOX_EXCEPTION_HPP
#define SAFE_BOX_EXCEPTION_HPP

#include <exception>
#include <string>
#include <sstream>
using namespace std;

class SafeBoxException : public exception {
public:
    virtual const char* what() const noexcept = 0;
};

class BoxFullException : public SafeBoxException {
private:
    int capacity_;
    string msg_;
public:
    BoxFullException(int capacity) : capacity_(capacity) {
        ostringstream oss;
        oss << "Box penuh: kapasitas maks " << capacity_;
        msg_ = oss.str();
    }
    const char* what() const noexcept override {
        return msg_.c_str();
    }
};

class BoxEmptyException : public SafeBoxException {
public:
    const char* what() const noexcept override {
        return "Box kosong";
    }
};

template<typename T>
class InvalidItemException : public SafeBoxException {
private:
	T item_;
    string msg_;
public:
    InvalidItemException(const T& item) {
        ostringstream oss;
        oss << "Item tidak valid: " << item;
        msg_ = oss.str();
    }
    const char* what() const noexcept override {
        return msg_.c_str();
    }
};

#endif
