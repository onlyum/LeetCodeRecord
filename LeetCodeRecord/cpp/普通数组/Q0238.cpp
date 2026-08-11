//
// Created by Administrator on 2026/8/11.
//
//除了自身以外数组的乘积

#include <vector>
#include <iostream>
using namespace std;

class Solution {
public:
    vector<int> productExceptSelf(vector<int>& nums) {
        int N = nums.size();
        vector<int> L(N,1);
        vector<int> R(N,1);
        vector<int> result(N, 1);

        for (int i=1;i<N;i++) {
            L[i] = nums[i-1] * L[i-1];
        }
        for (int i=N-2;i>=0;i--) {
            R[i] = nums[i+1] * R[i+1];
        }
        for (int i=0;i<N;i++) {
            result[i] = L[i] * R[i];
        }

        return result;
    }
};


int main() {
    Solution solution;

    struct TestCase {
        vector<int> nums;
        vector<int> expected;
    };

    vector<TestCase> testCases = {
        {{1, 2, 3, 4}, {24, 12, 8, 6}},            // 官方常规示例
        {{-1, 1, 0, -3, 3}, {0, 0, 9, 0, 0}},     // 包含 1 个 0 的情况
        {{0, 0}, {0, 0}},                          // 包含多个 0 的情况
        {{5, 2}, {2, 5}}                           // 极限用例：长度为 2
    };

    cout << "================ 测试开始 ================" << endl;
    for (size_t i = 0; i < testCases.size(); ++i) {
        vector<int> result = solution.productExceptSelf(testCases[i].nums);
        bool passed = (result == testCases[i].expected);

        cout << "测试用例 [" << i + 1 << "]: "
             << (passed ? "\033[32m[PASS]\033[0m" : "\033[31m[FAIL]\033[0m") << endl;

        cout << "  输出: ";
        for (int x : result) cout << x << " ";
        cout << "\n  期望: ";
        for (int x : testCases[i].expected) cout << x << " ";
        cout << "\n----------------------------------------" << endl;
    }

    return 0;
}