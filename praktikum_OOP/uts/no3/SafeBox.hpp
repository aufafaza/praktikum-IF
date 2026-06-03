#ifndef SAFE_BOX_HPP
#define SAFE_BOX_HPP

#include <iostream>
#include <functional>
#include <vector>
#include <algorithm>
#include "SafeBoxException.hpp"

using namespace std;

template<typename T>
class SafeBox {
private:
    int capacity_;
    vector<T> items_; 
    function<bool(const T&)> validator_;

public:
    SafeBox(int capacity, function<bool(const T&)> validator = nullptr)
        : capacity_(capacity), validator_(validator) {}

    void store(const T& item) {
        if (validator_ && !validator_(item)) {
            throw InvalidItemException<T>(item);
        }
        if ((int)items_.size() >= capacity_) {
            throw BoxFullException(capacity_);
        }
        items_.push_back(item);
    }

    T retrieve() {
        if (items_.empty()) throw BoxEmptyException();
        T top = items_.back();
        items_.pop_back();
        return top;
    }

    T peek() const {
        if (items_.empty()) throw BoxEmptyException();
        return items_.back();
    }

    void reverse() {
        if (items_.empty()) throw BoxEmptyException();
        std::reverse(items_.begin(), items_.end());
    }

    int size() const { return (int)items_.size(); }
    int capacity() const { return capacity_; }
    bool isEmpty() const { return items_.empty(); }

    friend ostream& operator<<(ostream& os, const SafeBox<T>& box) {
        if (box.items_.empty()) {
            os << "[]";
            return os;
        }
        os << "[";
        for (int i = 0; i < (int)box.items_.size(); i++) {
            os << box.items_[i];
            if (i < (int)box.items_.size() - 1) os << ", ";
        }
        os << "]";
        return os;
    }
};

template<typename T>
int safeMerge(SafeBox<T>& src, SafeBox<T>& dst) {
    int moved = 0;
    while (!src.isEmpty()) {
        try {
            T item = src.peek(); 
	    dst.store(item); 
	    src.retrieve(); 
	    moved++;
        } catch (BoxFullException&) {
            break;
        }
    }
    return moved;
}

#endif
