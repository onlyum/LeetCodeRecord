package Q0239;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){ //k滑动窗口用 优先队列，内部放[数值，下标]
            public int compare(int[] pair1, int[] pair2){
                return pair1[0]!=pair2[0]? pair2[0]-pair1[0] : pair2[1] - pair1[1];
            }
        });
        for(int i=0; i<k; i++){
            pq.offer(new int[]{nums[i], i});
        }
        int[] res = new int[n-k+1];
        res[0] = pq.peek()[0];
        for(int i=k; i<n; i++){
            pq.offer(new int[]{nums[i], i});
            while(pq.peek()[1] <= i-k){
                pq.poll();
            }
            res[i-k+1] = pq.peek()[0];
        }
        return res;
    }
}
