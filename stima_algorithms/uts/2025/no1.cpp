#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

struct Result {
    int minVal;   // Nilai terkecil di sub-larik ini
    int maxVal;   // Nilai terbesar di sub-larik ini
    int maxStep;  // Selisih terbesar (x[j] - x[i]) di sub-larik ini
};

Result solveMaxStep(const vector<int>& x, int i, int j) {
	if (j == i + 1) { 
		return { 
			min(x[i], x[j]), max(x[i], x[j]), x[j] - x[i]
		}; 
	}

	int mid = (i + j) / 2; 

	Result left = solveMaxStep(x, i, mid); 
	Result right = solveMaxStep(x, mid + 1, j);
	Result res; 
	res.minVal = min(left.minVal, right.minVal);
	res.maxVal = max(left.maxVal, right.maxVal); 

	int cross = res.maxVal - res.minVal; 
	res.maxStep = max({left.maxStep, right.maxStep, cross});

	return res; 
}

struct Ball { 
	int weight; 
}; 


int sum(vector<int>::const_iterator start, vector<int>::const_iterator end) { 
	int c; 
	for (auto it = start; it != end; it++){ 
		c += *it;
	}
	return c; 
} 

int solveBall(vector<int>::const_iterator start, vector<int>::const_iterator end) { 
	int n = distance(start, end); 
	if (n == 1) return *start; 

	int k = n/3; 
	auto leftEnd = start + k; 
	auto midEnd = leftEnd + k; 

	int sumA = sum(start, leftEnd); 
	int sumB = sum(leftEnd, midEnd); 

	if (sumA > sumB){
		return solveBall(start, leftEnd);
	} else if (sumB > sumA){
		return solveBall(leftEnd, midEnd); 
	} else{ 
		return solveBall(midEnd, end);
	} 
} 

int findHeavy(const vector<int>& x){
	if (x.empty()) return -1; 
	return solveBall(x.begin(), x.end()); 
} 

int main() {
vector<int> x = {1, 1, 1, 1, 5, 1, 1, 1, 1};
	int y = findHeavy(x); 
	cout << y<< endl; 
} 
