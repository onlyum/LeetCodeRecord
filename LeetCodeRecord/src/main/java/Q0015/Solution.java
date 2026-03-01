package Q0015;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if(i>0 && nums[i] == nums[i-1]) continue;
            int l = i+1, r = nums.length-1, target = -nums[i];
            while(l<r){
                int sum = nums[l] + nums[r];
                if(sum == target){
                    res.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    l++;
                    r--;
                    // 跳过重复元素
                    while(l<r && nums[l] == nums[l-1]) l++;
                    while(l<r && nums[r] == nums[r+1]) r--;
                }else if(sum > target){
                    r--;
                }else{
                    l++;
                }
            }
        }
        return res;
    }
}
