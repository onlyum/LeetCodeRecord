package 华为.T20260423;
/**
 描述
 爱丽丝正在为制作新的人偶准备素材。她需要购买 n种不同的素材，每种素材的单价分别为 a1,a2,…,an。为了保证每一种人偶都能顺利完成，爱丽丝制定了一个严格的计划：她必须为每种素材至少购买一个。
 现在爱丽丝手中恰好有 b 元，她希望知道有多少种不同的采购方案，能够恰好耗尽这 b 元预算。需要注意的是，即使两种素材的单价相同，它们也被视为不同的素材种类。

 输入描述：
 输入包含单组测试数据。
 第一行包含一个整数 b代表爱丽丝的总预算。
 第二行包含若干个以空格分隔的整数，代表每种素材的单价 ai

 输出描述：
 输出一个整数，表示恰好耗尽预算的采购方案总数。
 注意：答案可能超过 32 位整数的范围，请使用 64 位整数（如 C++ 中的 `long long`）。
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
public class T3 {
    //DFS+记忆化搜索  超时
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens()) return;
        int b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> prices = new ArrayList<>();
        int sum = 0;
        while (st.hasMoreTokens()) {
            int price = Integer.parseInt(st.nextToken());
            prices.add(price);
            sum += price;
        }

        //不够扣
        if (b < sum) {
            System.out.println(0);
            return;
        }
        //剩余
        int target = b - sum;
        // dp[j] 表示恰好耗尽预算 j 的方案数。必须使用 long 防止溢出。
        long[] dp = new long[target + 1];
        dp[0] = 1L;//表示什么都不买
        /**
         * 每一轮外层循环，都是在原有的方案基础上，向市场“新引入”一种素材，并把“包含这种新素材”的所有新组合，全部叠加（累加）到数组中。
         首轮：当前如果只允许买价格为prices[1]的素材，那么计算凑齐j预算的方案数
         后续循环：新加prices[i]的素材可以购买，那么计算凑齐j预算的方案数dp[j] = 不选新加素材的方案书dp[j] + 选新家素材的方案数dp[j-price]
         */
        for(int price: prices) {
            for(int j = price; j <= target; j++) {
                dp[j] = dp[j] + dp[j - price];
            }
        }

        pw.println(dp[target]);
        pw.flush();
        pw.close();
    }
}
//public class T3 {
//    //DFS+记忆化搜索  超时
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(System.out);
//        StringTokenizer st =  new StringTokenizer(br.readLine().trim());
//        if(!st.hasMoreTokens())  return;
//        int b = Integer.parseInt(st.nextToken());
//        st =  new StringTokenizer(br.readLine().trim());
//        List<Integer> list = new ArrayList<>();
//        while(st.hasMoreTokens()){
//            list.add( Integer.parseInt(st.nextToken()));
//        }
//        int[] a = list.stream().mapToInt(Integer::intValue).toArray();
//        for(int i = 0; i < a.length/2; i++){
//            int temp = a[i];
//            a[i] = a[a.length-i-1];
//            a[a.length-i-1] = temp;
//        }
//
//        Long[][] memo = new Long[a.length][b+1];//记忆化数组
//        /*
//        记录在处理到第 curIndex 种素材时，面对剩余的 remValue 预算，后续“所有可能的合法购买方案总数”
//         */
//        pw.println(dfs(memo, b, 0, a));
//
//        pw.flush();
//        pw.close();
//    }
//    static Long dfs(Long[][] memo, int remValue, int curIndex, int[] a){
//        // 1. 只有当所有种类的素材都分配过了，才去检验预算是否正好为 0
//        if (curIndex == a.length) {
//            return remValue == 0 ? 1L : 0L;
//        }
//        // 2. 剪枝：如果中途预算已经透支（< 0），后面就不用看了，直接失败
//        if (remValue < 0) {
//            return 0L;
//        }
//
//        Long curCount = 0L;
//        int maxTimes = remValue/a[curIndex];
//        for(int i=1;i<=maxTimes;i++){
//            curCount += dfs(memo, remValue-i*a[curIndex], curIndex+1, a);
//        }
//
//        memo[curIndex][remValue] = curCount;
//        return curCount;
//    }
//}
