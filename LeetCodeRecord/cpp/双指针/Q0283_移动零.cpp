#include <iostream>
#include <vector>
#include <unordered_set>
#include <string>
#include <algorithm>
using namespace std;

class Solution{
public:
    void moveZeroes(vector<int>& nums){
        int left = 0;
        int right = 0;
        while(right < nums.size()){
            if(nums[right] != 0){
                swap(nums[left], nums[right]);
                left++;
                right++;
            }else{
                right++;
            }
        }
    }
};

int main() {
    Solution solution;

    // 测试用例 1: [0, 1, 0, 3, 12] -> [1, 3, 12, 0, 0]
    vector<int> nums1 = {0, 1, 0, 3, 12};
    solution.moveZeroes(nums1);
    cout << "示例 1 移动零后的结果: ";
    for (int num : nums1) {
        cout << num << " ";
    }
    cout << endl;

    // 测试用例 2: [0] -> [0]
    vector<int> nums2 = {0};
    solution.moveZeroes(nums2);
    cout << "示例 2 移动零后的结果: ";
    for (int num : nums2) {
        cout << num << " ";
    }
    cout << endl;

    return 0;
}