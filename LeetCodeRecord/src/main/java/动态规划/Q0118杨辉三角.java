package 动态规划;

import 链表.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q0118杨辉三角 {
    public static void main(String[] args) {
        System.out.println(generate(5));
    }
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>(Arrays.asList(1)));
        for(int i=1;i<numRows;i++){
            List<Integer> row = new ArrayList<>();
            List<Integer> pre = res.get(i-1);

            for(int j=0;j<=i;j++){
                if(j==0||j==i)  row.add(1);
                else    row.add(pre.get(j-1) + pre.get(j));
            }

            res.add(row);
        }
        return res;
    }
}
