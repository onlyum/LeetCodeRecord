//
// Created by Administrator on 2026/8/12.
//
//矩阵置零

#include <iostream>
#include <vector>
#include <iomanip>

using namespace std;
class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        if (matrix.empty() || matrix[0].empty()) return;
        int m = matrix.size();
        int n = matrix[0].size();

        bool flag_firstRow = false;
        bool flag_firstColumn = false;

        for (int j=0;j<n;j++) {
            if (matrix[0][j] == 0) {
                flag_firstRow = true;
            }
        }

        for (int i=0;i<m;i++) {
            if (matrix[i][0] == 0) {
                flag_firstColumn = true;
            }
        }

        for (int i=1;i<m;i++) {
            for (int j=1;j<n;j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i=1;i<m;i++) {
            for (int j=1;j<n;j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (flag_firstRow) {
            for (int j=0;j<n;j++) {
                matrix[0][j] = 0;
            }
        }

        if (flag_firstColumn) {
            for (int i=0;i<m;i++) {
                matrix[i][0] = 0;
            }
        }
    }
};

// 辅助打印矩阵函数
void printMatrix(const vector<vector<int>>& matrix) {
    for (const auto& row : matrix) {
        cout << "[ ";
        for (int val : row) {
            cout << setw(3) << val << " ";
        }
        cout << "]\n";
    }
}

// 自动化测试用例运行函数
void runTest(int testId, vector<vector<int>> matrix, const vector<vector<int>>& expected) {
    cout << "================ Test Case " << testId << " ================\n";
    cout << "--- 原始矩阵 ---" << endl;
    printMatrix(matrix);

    Solution sol;
    sol.setZeroes(matrix);

    cout << "\n--- 修改后矩阵 ---" << endl;
    printMatrix(matrix);

    bool isPassed = (matrix == expected);
    cout << "\n测试结果: " << (isPassed ? "PASSED ✅" : "FAILED ❌") << "\n\n";
}

int main() {
    // 测试用例 1: 标准中间有 0 的 3x3 矩阵
    vector<vector<int>> case1 = {
        {1, 1, 1},
        {1, 0, 1},
        {1, 1, 1}
    };
    vector<vector<int>> exp1 = {
        {1, 0, 1},
        {0, 0, 0},
        {1, 0, 1}
    };

    // 测试用例 2: 多行多列包含 0，且边界有 0 的 3x4 矩阵
    vector<vector<int>> case2 = {
        {0, 1, 2, 0},
        {3, 4, 5, 2},
        {1, 3, 1, 5}
    };
    vector<vector<int>> exp2 = {
        {0, 0, 0, 0},
        {0, 4, 5, 0},
        {0, 3, 1, 0}
    };

    // 测试用例 3: 首行首列包含 0 的边界用例
    vector<vector<int>> case3 = {
        {1, 0, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    vector<vector<int>> exp3 = {
        {0, 0, 0},
        {4, 0, 6},
        {7, 0, 9}
    };

    // 测试用例 4: 不包含任何 0 的矩阵
    vector<vector<int>> case4 = {
        {1, 2},
        {3, 4}
    };
    vector<vector<int>> exp4 = {
        {1, 2},
        {3, 4}
    };

    // 测试用例 5: 1x1 只有一个 0 的单单元格矩阵
    vector<vector<int>> case5 = {
        {0}
    };
    vector<vector<int>> exp5 = {
        {0}
    };

    // 执行测试
    runTest(1, case1, exp1);
    runTest(2, case2, exp2);
    runTest(3, case3, exp3);
    runTest(4, case4, exp4);
    runTest(5, case5, exp5);

    return 0;
}
