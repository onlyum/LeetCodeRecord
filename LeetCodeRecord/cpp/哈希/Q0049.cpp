// 题目: 字母异位词分组

#include <iostream>
#include <vector>
#include <unordered_map>
#include <string>
#include <algorithm>

using namespace std;

class Solution{
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs){
        unordered_map<string, vector<string>> map;
        for(const string& str : strs){
            string sorted_str = str;
            sort(sorted_str.begin(), sorted_str.end());
            map[sorted_str].push_back(str);
        }
        vector<vector<string>> result;
        for(const auto& pair : map){
            result.push_back(pair.second);
        }
        return result;
    }
};

int main(){
    Solution sol;
    vector<string> strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
    vector<vector<string>> result = sol.groupAnagrams(strs);
    for(const auto& group : result){
        for(const auto& str : group){
            cout << str << " ";
        }
        cout << endl;
    }
    return 0;
}