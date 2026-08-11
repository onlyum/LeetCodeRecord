//
// Created by Administrator on 2026/8/11.
//

#include <deque>
#include <vector>
#include <iostream>
#include <queue>

//滑动窗口最大值
using namespace std;

class Solution {
    public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        vector<int> res;
        // 大顶堆，存储 pair<数值, 索引>
        // std::priority_queue 默认比较 pair 的第一个元素（数值）
        priority_queue<pair<int, int>> pq;

        for (int i=0;i<k;i++) {
            pq.push({nums[i], i});
        }
        res.push_back(pq.top().first);
        //删除过期的
        for (int i = k; i < nums.size(); i++) {
            pq.push({nums[i], i});
            while (pq.top().second <= i-k) {
                pq.pop();
            }
            res.push_back(pq.top().first);
        }
        return res;
    }
};
int main() {
    Solution sol;
    // 测试用例 1
    std::vector<int> nums1 = {1, 3, 5, 2, 4};
    vector<int> res = sol.maxSlidingWindow(nums1, 2);
    copy(res.begin(), res.end(), ostream_iterator<int>(cout, " "));
    std::cout << "测试 1 结果: "  << " (预期: 20)\n";


    return 0;
}