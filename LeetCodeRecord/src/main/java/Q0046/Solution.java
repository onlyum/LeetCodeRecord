package Q0046;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums){
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();//记录答案
        List<Integer> path = new ArrayList<>();//记录当前排列
        boolean[] used = new boolean[n];//标记当前位置是否被使用
        backtrack(nums, path, used, res);
        return res;
    }
    public void backtrack(int[] nums, List<Integer> path, boolean[] used, List<List<Integer>> res){
        //1.中止条件
        if(path.size() == nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0; i<nums.length; i++){
            //剪枝
            if(used[i]){
                continue;
            }
            //2.做出选择
            path.add(nums[i]);
            used[i] = true;
            //3.递归
            backtrack(nums, path, used, res);
            //4.撤销选择（回溯）
            path.remove(path.size()-1);
            used[i] = false;
        }
    }
}