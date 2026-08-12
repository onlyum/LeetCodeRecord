//
// Created by Administrator on 2026/8/12.
//

//搜索二维矩阵2

#include <iostream>
#include <vector>
#include <iomanip>

using namespace std;

class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        if (matrix.empty() || matrix[0].empty()) return false;

        int m = matrix.size();
        int n = matrix[0].size();

        // 从右上角 (0, n - 1) 开始搜索
        int row = 0;
        int col = n - 1;

        while (row < m && col >= 0) {
            int current = matrix[row][col];
            if (current == target) {
                return true;
            } else if (current > target) {
                col--; // 偏大，向左移（剔除当前列）
            } else {
                row++; // 偏小，向下移（剔除当前行）
            }
        }

        return false;
    }
};

// 辅助打印矩阵函数
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
void runTest(int testId, const vector<vector<int>>& matrix, int target, bool expected) {
    cout << "================ Test Case " << testId << " ================\n";
    cout << "目标值 target: " << target << "\n--- 矩阵内容 ---\n";
    printMatrix(matrix);

    Solution sol;
    // 拷贝一份 matrix 避免修改原数据
    vector<vector<int>> matrixCopy = matrix;
    bool result = sol.searchMatrix(matrixCopy, target);

    cout << "\n实际输出: " << (result ? "true" : "false")
         << " | 期望输出: " << (expected ? "true" : "false") << "\n";

    bool isPassed = (result == expected);
    cout << "测试结果: " << (isPassed ? "PASSED ✅" : "FAILED ❌") << "\n\n";
}

int main() {
    // 基础测试矩阵 (5x5)
    vector<vector<int>> baseMatrix = {
        { 1,  4,  7, 11, 15},
        { 2,  5,  8, 12, 19},
        { 3,  6,  9, 16, 22},
        {10, 13, 14, 17, 24},
        {18, 21, 23, 26, 30}
    };

    // 测试用例 1: 目标存在于矩阵中间 (target = 5)
    runTest(1, baseMatrix, 5, true);

    // 测试用例 2: 目标不存在于矩阵中 (target = 20)
    runTest(2, baseMatrix, 20, false);

    // 测试用例 3: 目标值小于最小值或大于最大值 (target = 0, target = 35)
    runTest(3, baseMatrix, 0, false);

    // 测试用例 4: 边界元素 (右下角角点 target = 30)
    runTest(4, baseMatrix, 30, true);

    // 测试用例 5: 1x1 矩阵单单元格测试
    vector<vector<int>> singleCellMatrix = {{5}};
    runTest(5, singleCellMatrix, 5, true);

    return 0;
}