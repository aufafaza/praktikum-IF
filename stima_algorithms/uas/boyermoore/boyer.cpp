#include <iostream> 
#include <unordered_map> 
#include <vector> 
using namespace std; 
struct Solution { 
    std::string pattern; 
    std::string text; 
    Solution(std::string pattern, std::string text) : pattern(pattern), text(text){}; 
    int solve(){ 
        int count = 0; 
        std::unordered_map<char, int> last = buildLast(pattern);
        for (auto const& pair : last) { 
            cout << "char '" << pair.first << "' : " << pair.second << endl; 
        }
        int n = text.length(); 
        int m = pattern.length(); 
        int i = m - 1; 
        if (i > n - 1){ 
            return -1; 
        }
        int j = m - 1; 
        do { 
            count++;

        cout << "total comparisons: " << count << endl;
            if (pattern.at(j) == text.at(i)){
                if (j == 0){ 
                    return i; 
                }
                else{ 
                    i--;
                    j--;
                }
            }else { 
                char mism = text.at(i);
                int lo = -1; 

                if (last.count(mism)){
                    lo = last.at(mism);
                }
                int shift; 

                if (lo == -1){ 
                    shift = j + 1;
                }else if (lo < j){
                    // normal case 
                    shift = j - lo; 
                }else {
                    shift = 1; 
                }
                /*
                 * i is the text pointer, hencew e shift i by its current state, PLUS the length of pattern, PLUS the shift.
                 * we reset j 
                 * */
                cout  << "i" << "= " << i << " + (" << m-1 << " - " << j << ") + " << shift << endl; 
                i = i + (m - 1 - j ) + shift; 
                j = m - 1; 
            }
        } while (i <= n -1); 


        cout << "total comparisons: " << count << endl;
        return -1; 
    } 

    unordered_map<char, int> buildLast(std::string pattern){ 
        std::unordered_map<char, int> last; 
        for (int i = 0; i < pattern.length(); i++){ 
            last[pattern.at(i)] = i;
        } 
        return last; 
    } 


}; 

int main(){ 
    std::string pattern("TATGTG"); 
    std::string text("GCAATGCGCGTATGTGACC");
    Solution sol(pattern, text);
    int result = sol.solve();
    cout << result << endl; 
    if (result != -1){ 
        for (int i = 0; i < pattern.length(); i++){ 
            cout << text.at(result + i);
        }
    };
} 
