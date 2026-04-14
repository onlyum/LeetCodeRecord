import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
239. 滑动窗口最大值
 */
public class Q0239 {
    public static void main(String[] args) {
        int[] nums = {3,1,1,3};
        int k = 3;
        int[] res = maxSlidingWindow(nums, k);
        for (int re : res) {
            System.out.println(re);
        }
    }
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        //优先队列存[数值，下标]，数值排序，最大值下标出则出队列
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2){
                return a1[0]!=a2[0]?a2[0]-a1[0]:a2[1]-a1[1];
            }
        });

        int[] res = new int[len-k+1];
        for(int i=0;i<k;i++){
            pq.offer(new int[]{nums[i], i});
        }
        res[0] = pq.peek()[0];

        for(int i=k;i<len;i++){
            pq.offer(new int[]{nums[i], i});
            //一定是while循环，确保窗口外全部排出
            while(pq.peek()[1] <= i-k){
                pq.poll();
            }
            res[i-k+1] = pq.peek()[0];
        }

        return res;

    }
}
