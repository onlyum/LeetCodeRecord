package Q0239;

import java.util.ArrayDeque;
import java.util.Deque;
/*
单调队列
* */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //双端队列维护单调队列，存储下标
        Deque<Integer> dq = new ArrayDeque<>();
        int n = nums.length;
        int[] res = new int[n-k+1];

        for(int i = 0;i<n;i++){
            //比新进入元素小的全部删除
            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]){
                dq.pollLast();
            }
            //下标入队尾
            dq.offerLast(i);
            //判断队首是否出边界
            if(dq.peekFirst() <= i - k){
                dq.pollFirst();
            }
            //窗口形成
            if(i>=k-1){
                //存队首下标对应数值
                res[i-(k-1)] = nums[dq.peekFirst()];
            }
        }
        return res;
    }
}
