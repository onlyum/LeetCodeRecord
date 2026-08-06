// 题目: 两数之和

﻿#include <iostream>
#include <vector>
#include <unordered_map>

class Solution{
public:
    std::vector<int> twoSum(std::vector<int> &nums, int target){
        std::unordered_map<int, int> num_map;

        for(int i = 0; i < nums.size(); i++){
            int target2 = target - nums[i];
            if(num_map.find(target2) != num_map.end()){
                return {num_map[target2], i};
            }
            num_map[nums[i]] = i;
        }

        return {};
    }
};

int main(){
    std::vector<int> nums = {2, 7, 11, 15};
    int target = 9;
    Solution sol;
    std::vector<int> res = sol.twoSum(nums, target);
    std::cout << res[0] << " " << res[1] << std::endl;
}
