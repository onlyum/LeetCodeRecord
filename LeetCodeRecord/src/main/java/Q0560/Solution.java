package Q0560;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
        //HaspMap存前缀和value:下标
        Map<Integer, Integer> map = new HashMap<>();
        //注意：初始化状态，因为[0,i]需要pre[i]-pre[-1]但是没有-1，就默认[-1]位置为0，并且默认出现过一次
        map.put(0,1);
        //前缀和
        int pre = 0;
        int n = nums.length;
        int res = 0;
        //遍历到i处时，就可以直到j<i的部分是否有pre[i]-pre[j]=k存在
        for(int i = 0;i<n;i++){
            pre += nums[i];
            if(map.containsKey(pre-k)){
                res += map.get(pre-k);//i之前所有前缀和为k-pre的都算进去
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return res;
    }
}