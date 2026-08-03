#include <iostream>
#include <vector>
#include <unordered_set>
#include <string>
#include <algorithm>
using namespace std;

class Solution{
public:
    int maxArea(vector<int>& height){
        int left = 0,right = height.size()-1,maxArea = 0;
        while(left<right){
            int cur_height = min(height[left],height[right]);
            int cur_width = right - left;
            int cur_area = cur_height * cur_width;
            maxArea = max(maxArea,cur_area);
            if(height[left]<height[right]){
                left++;
            }else{
                right--;
            }
        }
        return maxArea;
    }
};

int main() {
    Solution solution;
    vector<int> height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
    int result = solution.maxArea(height);
    cout << result << endl;
    return 0;
}