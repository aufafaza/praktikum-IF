#include <iostream>
#include <vector> 

class Solution { 
    private: 
    public: 
        int n;
        int m;
        std::vector<int> w; 
        std::vector<int> x; 
        Solution(int n, int m) : n(n), m(m){ 
            w.resize(n);
            x.resize(n, 0); 
        } 
        bool promising(int k, int curr, int remainder){ 
            return (curr + remainder >= m) && (curr == m || (k < n && curr + w.at(k) <= m));
        }
        void sumofsubsets(int k, int wt, int remainder){ 
            if (promising(k, wt, remainder)){ 
                if (wt == m){ 
                    std::cout << "current sum: " << wt << std::endl;
                    printSubset(); 
                }else if (k < n){
                   x.at(k) = 1; 
                    sumofsubsets(k+1, wt + w.at(k), remainder - w.at(k)); 

                    x.at(k) = 0; 
                    sumofsubsets(k+1, wt, remainder - w.at(k)); 
                }
            } 
        } 

        void printSubset(){ 

            for (int i = 0; i < n; i++){ 
                if (x.at(i) == 1){ 
                    std::cout << w.at(i) << std::endl;
                } 
            } 
        } 
        
};

int main() {
    Solution s(10, 100);
    int total_weights = 0; 
    for (int i = 0; i < s.n; i++){ 
        std::cout << "input w at: " << i + 1 << std::endl;
        int input;
        std::cin >> input; 
        s.w.at(i) = input;  
        total_weights += input; 
    } 

    s.sumofsubsets(0, 0, total_weights); 
    std::cout << std::endl << "done" << std::endl; 
} 
