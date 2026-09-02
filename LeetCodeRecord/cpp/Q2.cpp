#include <string>
#include <vector>
#include <iostream>
using namespace std;

class Solution {
public:
    //"A%sC%sE",[B,D,F]
    //"ABCDEF"

    string formatString(string str, vector<char>& arg) {
        // write code here
        string res;
        int count = 0;
        for (int i = 0; i < str.size(); i++) {
            if (str[i] == '%' && str[i + 1] == 's') {
                res.push_back(arg[count++]);
                i++;
            }else {
                res.push_back(str[i]);
            }
        }
        while (count < arg.size()) {
            res.push_back(arg[count++]);
        }
        return res;
    }
};

int main() {
    Solution solution;
    string str = "A%sC%sE";
    vector<char> arg = {'B', 'D', 'F'};
    cout << solution.formatString(str, arg)<< endl;

    return 0;
}