package 美团.第一次.T2;
// 超级斐波那契数列
import java.util.*;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    static Map<Integer, Integer> map = new HashMap<>(); // index：dp[index]
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        int q = in.nextInt();
        List<Integer> nums = new ArrayList<Integer>();
        // 注意 hasNext 和 hasNextLine 的区别
        for(int i = 0;i<q;i++) { // 注意 while 处理多个 case
            int a = in.nextInt();
            nums.add(a);
        }
        int[] arr = nums.stream().mapToInt(i->i).toArray();
        Arrays.sort(arr);
        int n = arr[arr.length-1];
        int[] dp = new int[n];
        for (int i = 0;i<n;i++) {
            if(i<k){
                dp[i] = 1;
                map.put(i, dp[i]);
            }else if(i==k){
                dp[i] = k;
                map.put(i, dp[i]);
            }
        }
        for(int a:arr){
            dp[a-1] = dg(dp, a-1, k);
            map.put(a-1, dp[a-1]);
        }
        for(Integer num:nums) {
            System.out.println(dp[num-1]+" ");
        }
    }
    public static int dg(int[] dp, int i, int k){
        int a = 0, b = 0;
        if(map.containsKey(i-1)){
            a = map.get(i-1);
        }else{
            a =  dg(dp, i-1, k);
            map.put(i-1, a);
        }
        if(map.containsKey(i-k-1)){
            b = map.get(i-k-1);
        }else{
            b =  dg(dp, i-k-1, k);
            map.put(i-k-1, b);
        }
        return (2 * a - b)%(1000000000+7);
    }
}