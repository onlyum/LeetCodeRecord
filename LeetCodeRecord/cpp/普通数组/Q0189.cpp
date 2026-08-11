//
// Created by Administrator on 2026/8/11.
//
//轮转数组


#include <algorithm>
#include <vector>
#include <iostream>
using namespace std;

class Solution {
public:
    void rotate(vector<int>& nums, int k) {
        if (nums.size()<=1) return;
        k = k % nums.size();
        if (k == 0) return;

        reverse(nums.begin(), nums.end());
        reverse(nums.begin(), nums.begin()+k);
        reverse(nums.begin()+k, nums.end());
    }
};

int main() {
    Solution solution;

    struct TestCase {
        vector<int> nums;
        int k;
        vector<int> expected;
    };

    vector<TestCase> testCases = {
        {{1, 2, 3, 4, 5, 6, 7}, 3, {5, 6, 7, 1, 2, 3, 4}}, // 官方示例 1
        {{-1, -100, 3, 99}, 2, {3, 99, -1, -100}},        // 官方示例 2
        {{1, 2}, 3, {2, 1}},                             // k > n 的边界用例 (3 % 2 = 1)
        {{1}, 0, {1}},                                   // 极限用例：单元素，不旋转
        {{1, 2, 3}, 0, {1, 2, 3}}                        // k = 0 不旋转
    };

    cout << "================ 测试开始 ================" << endl;
    for (size_t i = 0; i < testCases.size(); ++i) {
        vector<int> nums = testCases[i].nums;
        solution.rotate(nums, testCases[i].k);
        bool passed = (nums == testCases[i].expected);

        cout << "测试用例 [" << i + 1 << "]: "
             << (passed ? "\033[32m[PASS]\033[0m" : "\033[31m[FAIL]\033[0m") << endl;

        cout << "  输出: ";
        for (int x : nums) cout << x << " ";
        cout << "\n  期望: ";
        for (int x : testCases[i].expected) cout << x << " ";
        cout << "\n----------------------------------------" << endl;
    }

    return 0;
}