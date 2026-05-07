package 回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q0039组合总和 {
    public static void  main(String[] args){
        int[] candidates = {2,3,6,7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
    }
    static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);//注意排序从而剪枝
        DFS(res, new ArrayList<>(), candidates, 0, target);
        return res;
    }
    static void DFS(List<List<Integer>> res,List<Integer> curPath, int[] candidates,int start ,int target){
        if(target==0){//终止条件：如果目标和减到了 0，说明找到了一组解
            res.add(new ArrayList<>(curPath));
        }

        for(int i=start;i<candidates.length;i++){
            if(candidates[i]>target){//候选大于目标，则后续肯定都无效
                break;
            }
            curPath.add(candidates[i]);
            DFS(res,curPath,candidates,i,target-candidates[i]);
            curPath.remove(curPath.size()-1);
        }

    }
}
