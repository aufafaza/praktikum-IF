#include <vector> 
#include <iostream> 
#include <string> 

using namespace std; 

void constructlps(string &pattern, vector<int> &lps){ 
    int len = 0; 
    lps[0] = 0; 

    int i = 1; 

    while (i < pattern.length()){ 
        if (pattern.at(i) == pattern.at(len)){ 
            len++; 
            lps[i] = len; 
            i++; 
        }else { 
            if (len != 0) { 
                len = lps[len-1];
            }else{
                lps[i] = 0; 
                i++;
            }
        }
    } 
} 

vector<int> search(string &pattern, string& txt){ 
    int n = txt.length(); 
    int m = pattern.length(); 

    vector<int> lps(m); 
    vector<int> res; 
    constructlps(pattern, lps); 
    
    int i = 0; 
    int j = 0; 
    while (i < n) { 
        if (txt[i] == pattern[j]){ 
            i++; 
            j++; 
            if ( j == m){ 
                res.push_back(i - j); 
                j = lps[j - 1];
            }
        }
        else{
            if (j != 0){ 
                j = lps[j-1];
            }else{
                i++;
            }
        }
    }
    return res; 
}

int main() {
    string txt = "aabaacaadaabaaba";
    string pat = "aaba";

    vector<int> res = search(pat, txt);
    for (int i = 0; i < res.size(); i++)
        cout << res[i] << " ";

    return 0;
}
