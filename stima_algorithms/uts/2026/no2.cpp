#include <iostream> 
#include <vector> 
#include <algorithm> 
using namespace std; 

/*
 
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
 * */
struct Result { 
    double minVal; 
    double maxVal; 
    double actualMax; 

};

Result solve(vector<double>& arr, int i, int j) { 
	if (i == j){ 
		return { 
			arr[i], arr[i], 0
		}; 
	}
	else{ 
		int mid = (i + j) / 2; 

		Result left = solve(arr, i, mid); 
		Result right = solve(arr, mid + 1, j); 
		Result res;

		res.minVal = min(left.minVal, right.minVal); 
		res.maxVal = max(left.maxVal, right.maxVal); 

		res.actualMax = max({left.actualMax, right.actualMax, res.maxVal - res.minVal});

		return res; 	
	} 
}

int main() { 
	vector<double> x{4.5, 10, -2, 3.14, -7.25}; 
	Result y = solve(x, 0, static_cast<int>(x.size()) - 1);
	cout << y.actualMax << endl;
	
} 

