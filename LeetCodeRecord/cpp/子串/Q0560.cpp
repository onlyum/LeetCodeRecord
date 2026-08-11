//
// Created by Administrator on 2026/8/11.
//

//和为k的子数组


#include <vector>
#include <iostream>
#include <unordered_map>
using namespace std;

class Solution {
    public:
    int subarraySum(vector<int>& nums, int k) {
        unordered_map<int, int> prefix;
        prefix[0] = 1;
        int res = 0, sum = 0;
        for (int num: nums) {
            sum += num;
            if (prefix.count(sum-k)) {
                res += prefix[sum-k];
            }
            prefix[sum]++;
        }
        return res;
    }
};

int main() {
    Solution solution;

    // 测试用例 1
    std::vector<int> nums1 = {1, 1, 1};
    int k1 = 2;
    std::cout << "示例 1 输出: " << solution.subarraySum(nums1, k1) << std::endl; // 预期: 2

    // 测试用例 2（包含负数）
    std::vector<int> nums2 = {1, 2, 3, -2, 1, 4};
    int k2 = 3;
    std::cout << "示例 2 输出: " << solution.subarraySum(nums2, k2) << std::endl; // 预期: 4 ([1,2], [3], [3,-2,1,-2...], [2,3,-2] 等)

    return 0;
}