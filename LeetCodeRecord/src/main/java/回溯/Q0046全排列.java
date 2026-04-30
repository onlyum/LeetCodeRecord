package 回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q0046全排列 {
    public static void  main(String[] args){
        int[] nums = {1,2,3};
        System.out.println(permute(nums));
    }
    static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        DFS(ans, new ArrayList<>(), nums, used);
        return ans;
    }
    static void DFS(List<List<Integer>> ans, List<Integer> curPath, int[] nums, boolean[] used){
        //注意List<Integer> curPath是对象引用，一定要new ArrayList<>(curPath)才是把内容加入
        if(curPath.size()==nums.length){
            ans.add(new ArrayList<>(curPath));
            return;
        }
        for(int i=0;i<nums.length;i++){//排列就全找，组合就从start+1找，不需要used[]数组了，不会重复之前已用的
            if(!used[i]){
                used[i]=true;
                curPath.add(nums[i]);

                DFS(ans,curPath,nums,used);

                curPath.remove(curPath.size()-1);
                used[i]=false;
            }
        }
    }

    //全子集
    static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        // 从索引 0 开始搜索
        DFS(ans, new ArrayList<>(), nums, 0);
        return ans;
    }

    static void DFS(List<List<Integer>> ans, List<Integer> curPath, int[] nums, int start) {
        // 1. 核心区别：每进入一次 DFS，当前的路径都是一个子集
        ans.add(new ArrayList<>(curPath));

        // 2. 这里的 i 从 start 开始，保证不回头找前面的元素
        for (int i = start; i < nums.length; i++) {
            curPath.add(nums[i]);

            // 3. 递归时传入 i + 1，确保下一层从当前元素的下一个位置开始
            DFS(ans, curPath, nums, i + 1);

            // 4. 回溯
            curPath.remove(curPath.size() - 1);
        }
    }
}
