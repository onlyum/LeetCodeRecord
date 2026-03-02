package Q0056;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0)   return new int[0][2];
        Arrays.sort(intervals, new Comparator<int[]>(){     //按左边界大小顺序排列
            public int compare(int[] interval1, int[] interval2){
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> res = new ArrayList<>();
        for(int i = 0; i<intervals.length; i++){
            int L = intervals[i][0], R = intervals[i][1];
            if(i==0 || res.get(res.size()-1)[1] < L){   //当前边界的左端在结果边界的右侧了，无重叠则新增
                res.add(new int[]{L, R});
            }else{      //有重叠则更新
                res.get(res.size()-1)[1] = Math.max(res.get(res.size()-1)[1], R);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
