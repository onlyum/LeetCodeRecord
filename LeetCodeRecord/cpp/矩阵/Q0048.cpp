//
// Created by Administrator on 2026/8/12.
//

//旋转图像
#include <iostream>
#include <vector>
#include <algorithm>
#include <iomanip>

using namespace std;

class Solution {
public:
    void rotate(vector<vector<int>>& matrix) {
        int n = matrix.size();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                swap(matrix[i][j], matrix[j][i]);
            }
        }
        for (int i = 0; i < n; i++) {
            reverse(matrix[i].begin(), matrix[i].end());
        }
    }
};

// 辅助函数：打印矩阵
void printMatrix(const vector<vector<int>>& matrix) {
    for (const auto& row : matrix) {
        cout << "[ ";
        for (int val : row) {
            cout << setw(4) << val << " ";
        }
        cout << "]\n";
    }
}

// 自动化测试运行函数
void runTest(int testId, vector<vector<int>> matrix, const vector<vector<int>>& expected) {
    cout << "================ Test Case " << testId << " ================\n";
    cout << "--- 原始矩阵 ---" << endl;
    printMatrix(matrix);

    Solution sol;
    sol.rotate(matrix);

    cout << "\n--- 旋转后矩阵 ---" << endl;
    printMatrix(matrix);

    bool isPassed = (matrix == expected);
    cout << "\n测试结果: " << (isPassed ? "PASSED ✅" : "FAILED ❌") << "\n\n";
}

int main() {
    // 测试用例 1: 3x3 奇数阶方阵
    vector<vector<int>> case1 = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    vector<vector<int>> exp1 = {
        {7, 4, 1},
        {8, 5, 2},
        {9, 6, 3}
    };

    // 测试用例 2: 4x4 偶数阶方阵
    vector<vector<int>> case2 = {
        { 5,  1,  9, 11},
        { 2,  4,  8, 10},
        {13,  3,  6,  7},
        {15, 14, 12, 16}
    };
    vector<vector<int>> exp2 = {
        {15, 13,  2,  5},
        {14,  3,  4,  1},
        {12,  6,  8,  9},
        {16,  7, 10, 11}
    };

    // 测试用例 3: 1x1 边界方阵
    vector<vector<int>> case3 = {
        {1}
    };
    vector<vector<int>> exp3 = {
        {1}
    };

    // 测试用例 4: 2x2 最小偶数阶方阵
    vector<vector<int>> case4 = {
        {1, 2},
        {3, 4}
    };
    vector<vector<int>> exp4 = {
        {3, 1},
        {4, 2}
    };

    // 测试用例 5: 包含负数的 3x3 方阵
    vector<vector<int>> case5 = {
        { 1, -2,  3},
        {-4,  5, -6},
        { 7, -8,  9}
    };
    vector<vector<int>> exp5 = {
        { 7, -4,  1},
        {-8,  5, -2},
        { 9, -6,  3}
    };

    // 执行测试用例
    runTest(1, case1, exp1);
    runTest(2, case2, exp2);
    runTest(3, case3, exp3);
    runTest(4, case4, exp4);
    runTest(5, case5, exp5);

    return 0;
}