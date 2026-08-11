//
// Created by Administrator on 2026/8/11.
//
//最小覆盖子串

#include <iostream>
#include <vector>
#include <cvt/wstring>
using namespace std;

class Solution {
private:
    bool check(vector<int>& s_cnt, vector<int>& t_cnt) {
        for (int i=0;i<128;i++) {
            if (s_cnt[i]<t_cnt[i]) {
                return false;
            }
        }
        return true;
    }
public:
    string minWindow(string s, string t) {
        if (s.length()<t.length()) {return "";}
        vector<int> s_cnt(128,0), t_cnt(128,0);
        for (char c:t) {
            t_cnt[c]++;
        }

        int left = 0;
        int min_len = INT_MAX;
        int start = 0;
        for (int right=0;right<s.length();right++) {
            s_cnt[s[right]]++;
            //左移
            while (check(s_cnt,t_cnt)) {
                if (right - left +1 < min_len) {
                    min_len = right - left + 1;
                    start = left;
                }
                s_cnt[s[left]]--;
                left++;
            }
        }
        return min_len==INT_MAX?"":s.substr(start,min_len);
    }
};

int main() {
    Solution solution;

    // 结构体：定义测试用例格式
    struct TestCase {
        string s;
        string t;
        string expected;
    };

    // 包含了常见考试/面试场景的边界用例
    vector<TestCase> testCases = {
        {"ADOBECODEBANC", "ABC", "BANC"},  // 官方标准示例：中间和末尾都有满足条件的子串
        {"a", "a", "a"},                  // 极限情况：长度都为 1
        {"a", "aa", ""},                  // 异常情况：s 满足不了 t 的字符频次要求
        {"aa", "a", "a"},                 // 重复字符：只需覆盖 1 个 a
        {"ab", "b", "b"},                 // 目标在末尾
        {"A", "A", "A"},                  // 大写字母
        {"aAbBcC", "abc", "aAbBc"},       // 大小写混合敏感度测试
        {"abcdef", "fed", "abcdef"}       // 必须完全覆盖整个 s 字符串
    };

    cout << "================ 测试开始 ================" << endl;
    for (int i = 0; i < testCases.size(); i++) {
        string result = solution.minWindow(testCases[i].s, testCases[i].t);
        bool passed = (result == testCases[i].expected);

        cout << "测试用例 [" << i + 1 << "]: "
             << (passed ? "\033[32m[PASS]\033[0m" : "\033[31m[FAIL]\033[0m") << endl;
        cout << "  输入 s: \"" << testCases[i].s << "\", t: \"" << testCases[i].t << "\"" << endl;
        cout << "  输出:   \"" << result << "\"" << endl;
        cout << "  期望:   \"" << testCases[i].expected << "\"" << endl;
        cout << "----------------------------------------" << endl;
    }

    return 0;
}