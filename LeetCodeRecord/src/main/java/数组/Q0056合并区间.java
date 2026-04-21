package 数组;//import java.util.Arrays;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Comparator;
//
//public class 数组.Q0056 {
//    public int[][] merge(int[][] intervals) {
//        if(intervals.length == 0)   return new int[0][2];
//        Arrays.sort(intervals, new Comparator<int[]>(){     //按左边界大小顺序排列
//            public int compare(int[] interval1, int[] interval2){
//                return interval1[0] - interval2[0];
//            }
//        });
//        List<int[]> res = new ArrayList<>();
//        for(int i = 0; i<intervals.length; i++){
//            int L = intervals[i][0], R = intervals[i][1];
//            if(i==0 || res.get(res.size()-1)[1] < L){   //当前边界的左端在结果边界的右侧了，无重叠则新增
//                res.add(new int[]{L, R});
//            }else{      //有重叠则更新
//                res.get(res.size()-1)[1] = Math.max(res.get(res.size()-1)[1], R);
//            }
//        }
//        return res.toArray(new int[res.size()][]);
//    }
//}

//20260413
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Q0056合并区间 {
    public static void main(String[] args){
        int[][] in = {{1,3},{2,6},{8,10},{15,18}};
        int[][] out = merge(in);
        for(int i =0;i<out.length;i++){
            System.out.print(Arrays.toString(out[i])+(i==out.length-1?"":" "));
        }

    }
    public static int[][] merge(int[][] intervals) {
        if(intervals.length==0) return new int[0][2];

        //按左值升序排序
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] in1,int[] in2){
                return in1[0] - in2[0];
            }
        });
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        for(int i =1;i<intervals.length;i++){
            int[] cur = intervals[i];
            boolean used = false;
            for (int[] re : res) {
                if (cur[0] <= re[1]) {
                    re[1] = Math.max(re[1], cur[1]);
                    used = true;
                    break;
                }
            }
            if(!used){
                res.add(intervals[i]);
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}
