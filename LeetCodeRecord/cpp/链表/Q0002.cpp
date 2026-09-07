//两数相加

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(nullptr) {}
};

using namespace std;

class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode *dummy = new ListNode(0), *cur = dummy;
        int sum = 0;

        while (l1 || l2 || sum) {
            int curNum = sum;
            if (l1) {
                curNum += l1->val;
                l1 = l1->next;
            }
            if (l2) {
                curNum += l2->val;
                l2 = l2->next;
            }

            sum = curNum/10;
            cur->next = new ListNode(curNum%10);
            cur = cur->next;
        }

        ListNode* res = dummy->next;
        delete dummy;
        return res;
    }
};