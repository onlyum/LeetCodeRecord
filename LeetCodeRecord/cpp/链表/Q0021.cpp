//合并两个有序链表

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(nullptr) {}
};

using namespace std;

class Solution {
public:
    ListNode* mergeTwoLists(ListNode* list1, ListNode* list2) {
        ListNode* dummy = new ListNode(-1);
        ListNode* cur = dummy;

        // 比较两链表节点，谁小就让 cur->next 指向谁
        while (list1 && list2) {
            if (list1->val <= list2->val) {
                cur->next = list1;
                list1 = list1->next;
            } else {
                cur->next = list2;
                list2 = list2->next;
            }
            cur = cur->next;
        }

        // 任一链表空了，直接把另一个链表剩余部分接上
        cur->next = list1 ? list1 : list2;

        ListNode* res = dummy->next;
        delete dummy; // 释放虚拟头节点，防止内存泄漏
        return res;
    }
};