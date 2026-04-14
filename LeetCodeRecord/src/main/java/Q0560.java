/*
560. 和为 K 的子数组
 */

//import java.util.HashMap;
//import java.util.Map;
//
//class Q0560 {
//    public static void main(String[] args){
//        Q0560 sol = new Q0560();
//        int[] input = new int[]{1,1,1};
//        System.out.println(sol.subarraySum(input, 2));
//    }
//    public int subarraySum(int[] nums, int k) {
//        //HaspMap存前缀和value:下标
//        Map<Integer, Integer> map = new HashMap<>();
//        //注意：初始化状态，因为[0,i]需要pre[i]-pre[-1]但是没有-1，就默认[-1]位置为0，并且默认出现过一次
//        map.put(0,1);
//        //前缀和
//        int pre = 0;
//        int n = nums.length;
//        int res = 0;
//        //遍历到i处时，就可以直到j<i的部分是否有pre[i]-pre[j]=k存在
//        for(int i = 0;i<n;i++){
//            pre += nums[i];
//            if(map.containsKey(pre-k)){
//                res += map.get(pre-k);//i之前所有前缀和为pre-k的都算进去
//            }
//            map.put(pre, map.getOrDefault(pre, 0) + 1);
//        }
//        return res;
//    }
//}

//20260414
import java.util.HashMap;
import java.util.Map;

class Q0560 {
    public static void main(String[] args){
        int[] input = new int[]{1,1,1};
        System.out.println(subarraySum(input, 2));
    }
    public static int subarraySum(int[] nums, int k) {
        //前缀和：出现次数。pre[i] - pre[<i] = k就是答案，加上出现次数即可
        int len = nums.length;
        int pre = 0;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        //需要有pre[-1]，不然就相当于自动把包含pre[0]的结果都删掉了
//注意：初始化状态，因为[0,i]需要pre[i]-pre[-1]但是没有-1，就默认[-1]位置为0，并且默认出现过一次
        map.put(pre,1);

        for (int num : nums) {
            pre += num;
            if (map.containsKey(pre - k)) {
                res += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }

        return res;
    }
}