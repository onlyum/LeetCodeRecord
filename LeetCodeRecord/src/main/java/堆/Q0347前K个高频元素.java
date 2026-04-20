package 堆;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Q0347前K个高频元素 {
    public static void main(String[] args){
        int[] nums = {1,2,1,2,1,2,3,1,3,2};
        int[] res = topKFrequent(nums, 2);
        System.out.println(Arrays.toString(res));
    }
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();//数字：频次
        for(int num:nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)->map.get(b)-map.get(a));
        for(int key: map.keySet()){
            pq.offer(key);
        }
        int[] res = new int[k];
        for(int i=0;i<k;i++){
            res[i] = pq.poll();
        }
        return res;
    }
}
