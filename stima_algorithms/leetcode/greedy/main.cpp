#include <iostream> 
#include <vector> 
#include <algorithm> 
using namespace std; 


class Solution {
public:
    int maxArea(vector<int>& height) {
        int i = 0;
        int j = static_cast<int>(height.size());
	j--; 
	int max = 0; 
        while (i < j){ 
            int temp; 
            if (height[i] < height[j]){ 
                temp = (j - i) * height[i];
                i++; 
            }else {
                temp = (j - i) * height[j];
                j--; 
            }
            if (temp > max) { 
                max = temp; 
            }
        }
        return max; 
    }

    int maxProfit(vector<int>& prices) {
        int profit = 0;
        int n = static_cast<int>(prices.size()) - 1; 
        int temp = prices[0];
        for (int i = 1; i < n; i++){ 
		
		cout << prices[i] << " vs " << temp << endl;
		if (prices[i] > temp){
			profit += prices[i] - temp; 
		    }
		temp = prices[i];

        }
        return profit; 
    }

	int maxSatisfaction(vector<int>& satisfaction){
		std::sort(satisfaction.begin(), satisfaction.end(), greater<int>()); 
		int ans = 0, s = 0; 
		for (int x : satisfaction) { 
			s += x; 
			if (s <= 0) break; 
			ans +=s;
		} 
		return ans;

	}
};

int main(){ 
	vector<int> height{1, 8, 6, 2, 5, 4, 8, 3, 7}; 
	vector<int> stocks{1, 2, 3, 4 ,5};	
	vector<int> chef{-1, -8, 0, 5, -9};
		Solution s; 
	int result = s.maxSatisfaction(chef); 
	cout << result << endl; 
} 
