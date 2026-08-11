//
// Created by Administrator on 2026/8/6.
//找到字符串中所有字母异位词

#include <iostream>
#include <ostream>
#include <string>
#include <vector>

using namespace std;

class Solution {
public:
    vector<int> findAnagrams(string s,string p) {
        if (s.length() < p.length()) {return {};}
        vector<int> s_cnt(256,-1);
        vector<int> p_cnt(256,-1);
        vector<int> res;
        for (int i = 0; i < p.length(); i++) {
            p_cnt[p[i]-'a']++;
            s_cnt[p[i]-'a']++;
        }

        if (p_cnt==s_cnt) {res.push_back(0);}

        for (int right = p.length(); right < s.length(); ++right) {
            s_cnt[s[right]-'a']++;
            s_cnt[s[right-p.length()]-'a']--;
            if (p_cnt==s_cnt) {res.push_back(right-p.length()+1);}
        }
        return res;
    }
};

int main() {
    Solution solution;

    // 测试用例 1
    std::string s1 = "cbaebabacd";
    std::string p1 = "abc";
    std::vector<int> res1 = solution.findAnagrams(s1, p1);
    std::cout << "示例 1 输出: ";
    std::copy(res1.begin(), res1.end(), std::ostream_iterator<int>(std::cout, " "));

    // 测试用例 2
    std::string s2 = "abab";
    std::string p2 = "ab";
    std::vector<int> res2 = solution.findAnagrams(s2, p2);
    std::cout << "示例 2 输出: ";
    std::copy(res2.begin(), res2.end(), std::ostream_iterator<int>(std::cout, " "));
    copy(res2.begin(), res2.end(), ostream_iterator<int>(cout, ""));
    return 0;
}
