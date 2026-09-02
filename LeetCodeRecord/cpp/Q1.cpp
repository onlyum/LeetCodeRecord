#include <iostream>
#include <vector>
#include <string>
using namespace std;
//最长公共子数组长度
// class Solution {
// public:
//     int longestCommonSubarry(vector<int>& A, vector<int>& B) {
//         // write code here
//         if (A.size() == 0 || B.size() == 0) return 0;
//         if (A.size() >= B.size()) {
//             swap(A, B);
//         }
//         int m = A.size(), n = B.size();
//
//         int res = 0;
//         int k = 0;
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
//                 if (A[j] == B[i]) {
//                     // 引入临时变量往后探测，不破坏原有的 i 和 j
//                     int temp_j = j;
//                     int temp_i = i;
//
//                     while (temp_j < m && temp_i < n && A[temp_j] == B[temp_i]) {
//                         temp_j++;
//                         temp_i++;
//                     }
//                     res = max(res, temp_i - i); // 计算匹配了多长
//                 }
//             }
//         }
//         return res;
//     }
// };

class Solution {
public:
    int longestCommonSubarry(vector<int>& A, vector<int>& B) {
        int m = A.size(), n = B.size();

        // 定义二维 DP 数组，大小为 (m+1) * (n+1)，初始值全为 0
        vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));

        int res = 0; // 用来随时记录找到的最大长度

        // i 表示 A 的第 i 个元素，j 表示 B 的第 j 个元素
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                // 如果当前元素相等
                if (A[i - 1] == B[j - 1]) {
                    // 当前格子的值 = 左上角格子的值 + 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                    // 每次更新后，都和历史最大值比对一下
                    res = max(res, dp[i][j]);
                }
                // 如果不相等，因为 C++ 中 vector 默认初始化就是 0，
                // 所以可以省略 dp[i][j] = 0; 这步
            }
        }
        return res;
    }
};

//[1,2],[1,2]
int main() {
    Solution solution;
    vector<int> input1={1,2,3}, input2={1,2};
    cout << solution.longestCommonSubarry(input1, input2) << endl;

    return 0;
}