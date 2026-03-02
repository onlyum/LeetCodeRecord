package Q0560;
import java.util.HashMap;

public class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap<Integer, Integer> map = new HashMap<>();    //存储(前缀和:出现次数),[j,i]求和为k,则pre[i]-pre[j-1]=k,即寻找pre - k是否在HashMap中
        map.put(0, 1);
        for(int i=0;i<nums.length;i++){
            pre += nums[i];
            if(map.containsKey(pre - k)){
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
