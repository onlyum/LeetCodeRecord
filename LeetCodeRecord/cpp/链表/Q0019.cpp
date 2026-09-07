//删除链表的倒数第 N 个结点

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(nullptr) {}
};

using namespace std;

class Solution {
public:
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        ListNode* cur = head;
        int len = 0;
        while (cur&&cur->next) {
            len++;
            cur = cur->next;
        }
    }
};