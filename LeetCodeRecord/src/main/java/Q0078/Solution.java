package Q0078;

import java.util.ArrayList;
import java.util.List;

class Solution {
    List<List<Integer>> ans = new ArrayList<>();    //存储结果
    List<Integer> path = new ArrayList<>();     //存储当前路径
    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return ans;
    }
    public void dfs(int cur, int[] nums){
        //1.终止条件：指针越过数组边界，说明所有数字都做完决策了
        if(cur == nums.length){
            ans.add(new ArrayList<>(path));
            return;
        }
        //2.当前数字 决策加入
        path.add(nums[cur]);
        dfs(cur+1, nums);
        path.remove(path.size()-1);//3.回溯以进行数字不加入情况
        //4.当前数字 决策不加入
        dfs(cur+1, nums);
    }
}