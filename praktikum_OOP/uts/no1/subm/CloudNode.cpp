#include "CloudNode.hpp"
#include "Formatter.hpp"
#include <string>
#include <algorithm>
using namespace std;

CloudNode::CloudNode(string name, int lim) 
    : server_name(name), limit_gb(lim), used_gb(0) {
    Formatter::printCtor(server_name);
}

CloudNode::CloudNode(const CloudNode& other) 
    : server_name(other.server_name + "_backup"), 
      limit_gb(other.limit_gb), 
      used_gb(0) {
    Formatter::printCCtor(this->server_name);
}

CloudNode::~CloudNode() {
    Formatter::printDtor(server_name);
}

CloudNode& CloudNode::operator=(const CloudNode& other) {
    int potential_used = other.getUsedGB() + 2;
    if (potential_used > this->limit_gb) {
        this->used_gb = this->limit_gb;
    } else {
        this->used_gb = potential_used;
    }

    Formatter::printAssign(this->server_name);
    return *this;
}

CloudNode CloudNode::operator+(int n) {
	CloudNode temp = *this;
	temp.limit_gb += n; 
	return temp; 
}

CloudNode CloudNode::operator-(int n) {
	CloudNode temp = *this; 
	temp.used_gb = max(0, temp.used_gb - n); 
	return temp; 
}

void systemWipe(CloudNode& node) {
    node.used_gb = 0;
    node.limit_gb = 0;
}
