#include <iostream>

class Solution {
public:
    std::string longestPalindrome(std::string s) {
        if(s.empty())   return "";

        int start = 0;
        int maxLen = 0;

        for(int i = 0; i < s.length(); i++) {
            int len_odd = expandAroundCenter(s, i, i);
            int len_even = expandAroundCenter(s, i, i+1);

            int len = std::max(len_odd, len_even);
            if(len>maxLen){
                maxLen = len;
                start = i - (len-1)/2;
            }
        }
        return s.substr(start, maxLen);
    }
private:
    int expandAroundCenter(const std::string&s , int left, int right) {
        while(left>=0&&right<s.length()&&s[left]==s[right]){
            left--;
            right++;
        }
        return right-left-1;
    }

};

int main() {
    Solution solution;
    std::string s = "babad";
    std::string result = solution.longestPalindrome(s);

    std::cout << "原字符串: " << s << std::endl;
    std::cout << "最长回文子串: " << result << std::endl; // 输出 "bab" 或 "aba"

    return 0;
}