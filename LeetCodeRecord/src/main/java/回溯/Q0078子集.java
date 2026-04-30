package 回溯;

import java.util.ArrayList;
import java.util.List;

public class Q0078子集 {
    public static void  main(String[] args){
        int[] nums = {1,2,3};
        System.out.println(subsets(nums));
    }
    static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        DFS(res, new ArrayList<>(), nums, 0);
        return res;
    }
    static void DFS(List<List<Integer>> res, ArrayList<Integer> curPath, int[] nums, int start){
        res.add(new ArrayList<>(curPath));

        for(int i = start; i < nums.length; i++){
            curPath.add(nums[i]);
            DFS(res, curPath, nums, i + 1);
            curPath.remove(curPath.size()-1);
        }
    }
}
