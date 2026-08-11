//
// Created by Administrator on 2026/8/11.
//
//最大子数组和
#include <vector>
#include <iostream>
using namespace std;

class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        if (nums.empty()) return 0;
        vector<int> dp(nums.size(),0);
        dp[0] = nums[0];
        int result = dp[0];

        for (int i = 1; i < nums.size(); ++i) {
            dp[i] = max(nums[i], dp[i-1] + nums[i]);//以nums[i]结尾的
            result = max(result, dp[i]);
        }

        return result;
    }
};

int main() {
    Solution solution;

    struct TestCase {
        vector<int> nums;
        int expected;
    };

    vector<TestCase> testCases = {
        {{-2, 1, -3, 4, -1, 2, 1, -5, 4}, 6}, // 官方示例，子数组为 [4,-1,2,1]
        {{1}, 1},                             // 极限用例：单个正数
        {{5, 4, -1, 7, 8}, 23},               // 极值用例：全正数/大部分正数
        {{-1, -2, -3, -4}, -1},               // 边界用例：全是负数（返回最大的那个负数）
        {{-2, -1}, -1}                         // 边界用例：全负数递增
    };

    cout << "================ 测试开始 ================" << endl;
    for (size_t i = 0; i < testCases.size(); ++i) {
        int result = solution.maxSubArray(testCases[i].nums);
        bool passed = (result == testCases[i].expected);

        cout << "测试用例 [" << i + 1 << "]: "
             << (passed ? "\033[32m[PASS]\033[0m" : "\033[31m[FAIL]\033[0m") << endl;
        cout << "  输出: " << result << " | 期望: " << testCases[i].expected << endl;
        cout << "----------------------------------------" << endl;
    }

    return 0;
}