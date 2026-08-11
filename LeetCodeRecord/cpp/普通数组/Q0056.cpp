//
// Created by Administrator on 2026/8/11.
//
//合并区间

#include <algorithm>
#include <vector>
#include <iostream>
using namespace std;

class Solution {
public:
    vector<vector<int>> merge(vector<vector<int>>& intervals) {
        if (intervals.empty()) {return {};}

        sort(intervals.begin(), intervals.end(), [](
            const vector<int>& l, const vector<int>& r) {
            return l[0] < r[0];
        });

        vector<vector<int>> result;
        result.push_back(intervals[0]);

        for (size_t i = 1; i < intervals.size(); ++i) {
            if (intervals[i][0] < result.back()[1]) {
                result.back()[1] = max(result.back()[1], intervals[i][1]);
            }else {
                result.push_back(intervals[i]);
            }
        }

        return result;
    }
};

int main() {
    Solution solution;

    struct TestCase {
        vector<vector<int>> intervals;
        vector<vector<int>> expected;
    };

    vector<TestCase> testCases = {
        {{{1,3},{2,6},{8,10},{15,18}}, {{1,6},{8,10},{15,18}}}, // 官方标准用例
        {{{1,4},{4,5}}, {{1,5}}},                               // 边界用例：端点刚好相接
        {{{1,4},{2,3}}, {{1,4}}},                               // 包含用例：一个区间完全包含另一个
        {{{1,4}}, {{1,4}}},                                     // 极限用例：只有一个区间
        {{{1,4},{0,4}}, {{0,4}}}                                // 无序用例：需要先对左端点排序
    };

    cout << "================ 测试开始 ================" << endl;
    for (size_t i = 0; i < testCases.size(); ++i) {
        vector<vector<int>> result = solution.merge(testCases[i].intervals);
        bool passed = (result == testCases[i].expected);

        cout << "测试用例 [" << i + 1 << "]: "
             << (passed ? "\033[32m[PASS]\033[0m" : "\033[31m[FAIL]\033[0m") << endl;

        cout << "  输入: ";
        for (const auto& interval : testCases[i].intervals) {
            cout << "[" << interval[0] << "," << interval[1] << "] ";
        }
        cout << "\n  输出: ";
        for (const auto& interval : result) {
            cout << "[" << interval[0] << "," << interval[1] << "] ";
        }
        cout << "\n  期望: ";
        for (const auto& interval : testCases[i].expected) {
            cout << "[" << interval[0] << "," << interval[1] << "] ";
        }
        cout << "\n----------------------------------------" << endl;
    }

    return 0;
}