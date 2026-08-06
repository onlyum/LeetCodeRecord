// 题目: 最长连续序列

#include <iostream>
#include <vector>
#include <unordered_set>
#include <string>
#include <algorithm>
using namespace std;

class Solution{
public:
    int longestConsecutive(vector<int>& nums){
        unordered_set<int> numSet(nums.begin(), nums.end());
        int max_length = 0;
        for(int num:numSet){
            if(!numSet.count(num-1)){
                int start = num;
                int length = 1;
                while(numSet.count(start+1)){
                    start++;
                    length++;
                }
                max_length = max(max_length, length);
            }
        }
        return max_length;
    }
};

int main() {
    Solution solution;
    
    // 测试用例 1：[100, 4, 200, 1, 3, 2] -> 最长序列为 [1, 2, 3, 4]，长度为 4
    vector<int> nums1 = {100, 4, 200, 1, 3, 2};
    cout << "示例 1 最长连续序列长度: " << solution.longestConsecutive(nums1) << endl; // 输出: 4

    // 测试用例 2：[0, 3, 7, 2, 5, 8, 4, 6, 0, 1] -> 最长序列为 [0, 1, 2, 3, 4, 5, 6, 7, 8]，长度为 9
    vector<int> nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
    cout << "示例 2 最长连续序列长度: " << solution.longestConsecutive(nums2) << endl; // 输出: 9

    return 0;
}