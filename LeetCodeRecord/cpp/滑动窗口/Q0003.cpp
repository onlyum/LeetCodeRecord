//
// Created by Administrator on 2026/8/6.
//无重复字符的最长字串
#include <iostream>
#include <ostream>
#include <string>
#include <vector>

using namespace std;

class Solution {
    public:
    int func(string s) {
        vector<int> last(256,-1);
        int left = 0;
        int res = 0;
        for (int right = 0; right < s.size(); ++right) {
            char c = s[right];
            if (last[c] >= left) {
                left = last[c] + 1;
            }
            last[c] = right;
            res = max(res, right - left + 1);
        }
        return res;
    }
};

int main() {
    string s = "abcabcbb";
    int res = Solution().func(s);
    cout << res << endl;
}