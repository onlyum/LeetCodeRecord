package 堆;

import java.util.*;

public class Q0215数组中的第K个最大元素 {
    public static void main(String[] args){
        int[] input = {3,2,3,1,2,4,5,5,6};
        int k = 4;
        System.out.println(findKthLargest(input, k));
    }
    public static int findKthLargest(int[] nums, int k) {
        List<Integer> ls = Arrays.stream(nums).boxed().toList();
        PriorityQueue<Integer> pq = new PriorityQueue<>(ls.size(), Collections.reverseOrder());
        pq.addAll(ls);
        while(--k>0 && !pq.isEmpty()){
            pq.poll();
        }
        return pq.peek();
    }
}
