//
// Created by Administrator on 2026/8/12.
//
//螺旋矩阵


#include <vector>


#include <iostream>
#include <vector>
#include <iomanip>

using namespace std;

class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        vector<int> res;
        if (matrix.empty() || matrix[0].empty()) return res;

        int top = 0;
        int bottom = matrix.size() - 1;
        int left = 0;
        int right = matrix[0].size() - 1;

        while (top <= bottom && left <= right) {
            // 1. 从左到右遍历上边界
            for (int j = left; j <= right; ++j) {
                res.push_back(matrix[top][j]);
            }
            top++; // 上边界下移

            // 2. 从上到下遍历右边界
            for (int i = top; i <= bottom; ++i) {
                res.push_back(matrix[i][right]);
            }
            right--; // 右边界左移

            // 3. 从右到左遍历下边界（需确认当前仍有有效行）
            if (top <= bottom) {
                for (int j = right; j >= left; --j) {
                    res.push_back(matrix[bottom][j]);
                }
                bottom--; // 下边界上移
            }

            // 4. 从下到上遍历左边界（需确认当前仍有有效列）
            if (left <= right) {
                for (int i = bottom; i >= top; --i) {
                    res.push_back(matrix[i][left]);
                }
                left++; // 左边界右移
            }
        }

        return res;
    }
};

// 辅助工具：打印矩阵
void printMatrix(const vector<vector<int>>& matrix) {
    for (const auto& row : matrix) {
        cout << "[ ";
        for (int val : row) {
            cout << setw(3) << val << " ";
        }
        cout << "]\n";
    }
}

// 辅助工具：打印一维数组
void printVector(const vector<int>& vec) {
    cout << "[ ";
    for (int val : vec) {
        cout << val << " ";
    }
    cout << "]";
}

// 测试驱动函数
void runTest(int testId, vector<vector<int>> matrix, const vector<int>& expected) {
    cout << "================ Test Case " << testId << " ================\n";
    cout << "--- 输入矩阵 ---" << endl;
    printMatrix(matrix);

    Solution sol;
    vector<int> result = sol.spiralOrder(matrix);

    cout << "\n实际输出: ";
    printVector(result);
    cout << "\n期望输出: ";
    printVector(expected);

    bool isPassed = (result == expected);
    cout << "\n\n测试结果: " << (isPassed ? "PASSED ✅" : "FAILED ❌") << "\n\n";
}

int main() {
    // 测试用例 1: 标准 3x3 奇数方阵
    vector<vector<int>> case1 = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    vector<int> exp1 = {1, 2, 3, 6, 9, 8, 7, 4, 5};

    // 测试用例 2: 3x4 矩形矩阵
    vector<vector<int>> case2 = {
        { 1,  2,  3,  4},
        { 5,  6,  7,  8},
        { 9, 10, 11, 12}
    };
    vector<int> exp2 = {1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7};

    // 测试用例 3: 只有 1 行的扁平矩阵 (1x4)
    vector<vector<int>> case3 = {
        {1, 2, 3, 4}
    };
    vector<int> exp3 = {1, 2, 3, 4};

    // 测试用例 4: 只有 1 列的高瘦矩阵 (4x1)
    vector<vector<int>> case4 = {
        {1},
        {2},
        {3},
        {4}
    };
    vector<int> exp4 = {1, 2, 3, 4};

    // 测试用例 5: 1x1 单单元格矩阵
    vector<vector<int>> case5 = {
        {99}
    };
    vector<int> exp5 = {99};

    // 执行测试用例
    runTest(1, case1, exp1);
    runTest(2, case2, exp2);
    runTest(3, case3, exp3);
    runTest(4, case4, exp4);
    runTest(5, case5, exp5);

    return 0;
}
