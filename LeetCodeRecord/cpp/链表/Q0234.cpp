//
// Created by Administrator on 2026/9/3.
//

//回文链表

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(nullptr) {}
};

using namespace std;

class Solution {
public:
    bool isPalindrome(ListNode* head) {
        vector<int> vals;
        while (head!=nullptr) {
            vals.push_back(head->val);
            head = head->next;
        }
        int left = 0, right = vals.size()-1;
        while (left<right) {
            if (vals[left]!=vals[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
};
