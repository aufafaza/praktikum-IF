#include <iostream> 
#include <vector> 
#include <algorithm> 
using namespace std; 


class Solution {
public:
	// just cout the minamx 
	int maximum(vector<int>& arr, int left, int right){
		if (left == right){ 
			return arr[left]; 
		}else if (right == left + 1){
			return std::max(arr[left], arr[right]);
		}

		int mid = (left + right) / 2; 
		int max1 = maximum(arr, left, mid); 
		int max2 = maximum(arr, mid + 1, right); 

		return max(max1, max2); 
	} 

};

int main(){
	vector<int> arrMax{1, 2, 3, 4, 5, 100};
	Solution s; 
	int result = s.maximum(arrMax, 0, static_cast<int>(arrMax.size()) - 1); 
	cout << result << endl; 
} 
