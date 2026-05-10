package 倍增算法;
/**
 * =========================================================================================
 * 【题目描述】
 * 有 n 家包子店，排成一个环或者可以使用取模来循环访问，编号从 0 到 n-1。
 * 数组 a[i] 表示在第 i 家店每次会购买的包子数量。
 *
 * 购买规则如下：
 * 1. 初始状态：小红手里有 0 个包子。
 * 2. 选店规则：每次去哪家店买，由【当前手里的包子总数 % n】决定。
 *    (因为初始包子数为 0，所以第一家去的店必定是 0 号店)。
 * 3. 购买操作：到达店面后，购买该店对应数量的包子，包子总数增加。
 *
 * 求解目标：给定一个极大的购买次数 m (m <= 2^45)，求经过 m 次购买后，小红手里最终有多少个包子。
 *
 * =========================================================================================
 * 【核心思路与算法设计：数学转化 + 图上倍增 (Binary Lifting)】
 *
 * 1. 核心数学转化 (寻找状态转移的确定性)：
 *    假设小红当前在第 i 家店，说明她此时手里的包子总数 S 满足 S % n = i。
 *    她在第 i 家店买了 a[i] 个包子后，包子总数变为 S' = S + a[i]。
 *    根据模运算的加法分配律，下一家店的编号为：
 *    S' % n = (S + a[i]) % n = ((S % n) + (a[i] % n)) % n = (i + a[i]) % n
 *
 *    结论：下一家去哪家店，【仅与当前所在的店编号 i 有关】，与包子总数无关！
 *    这就把一个累加求和的问题，完美转化为了一个图上固定路径的跳跃问题（每个节点出度为 1）。
 *
 * 2. 算法选择 (为什么用倍增)：
 *    因为 m 最大可达 2^45 (约 3.5 * 10^13)，普通的 for 循环一步步跳绝对会超时 (TLE)。
 *    利用倍增算法，我们可以把 O(m) 的时间复杂度降维打击到 O(n log m) 的预处理和 O(log m) 的查询。
 *
 * 3. 状态定义：
 *    - nextIdx[i][j] : 表示从第 i 号店出发，连续买 2^j 次包子后，最终到达的店面编号。
 *    - curSum[i][j]  : 表示从第 i 号店出发，连续买 2^j 次包子期间，总共买到的包子数量。
 *
 * 4. 边界初始化 (跳 2^0 = 1 步)：
 *    对所有 i (0 到 n-1)：
 *    nextIdx[i][0] = (i + a[i]) % n;
 *    curSum[i][0] = a[i];
 *
 * 5. 状态转移 (DP 预处理)：
 *    利用 2^j = 2^(j-1) + 2^(j-1) 的性质，把 2^j 步拆成两半跳。
 *    对 j 从 1 到 LOG-1：
 *      对 i 从 0 到 n-1：
 *        int midIdx = nextIdx[i][j-1]; // 跳一半到达的中转站
 *        nextIdx[i][j] = nextIdx[midIdx][j-1]; // 从中转站再跳一半
 *        curSum[i][j] = curSum[i][j-1] + curSum[midIdx][j-1]; // 前一半的收益 + 后一半的收益
 *
 * 6. 查询结果 (二进制拆分拼接)：
 *    初始位置 curr = 0，初始收益 res = 0。
 *    将总次数 m 按照二进制位从高到低 (或利用位运算) 进行拆解。
 *    如果 m 的第 j 位是 1（即 (m >> j) & 1 == 1）：
 *        res += curSum[curr][j];     // 累加这 2^j 步的收益
 *        curr = nextIdx[curr][j];    // 位置向前推进 2^j 步
 *    最后返回 res 即可。
 * =========================================================================================
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class T1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens()) return;

        int n = Integer.parseInt(st.nextToken());
        Long m = Long.parseLong(st.nextToken());

        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        //2^45次方以内
        int LOG = 45;
        int[][] nextIdx = new int[n][LOG];
        long[][] curSum = new long[n][LOG];
        //初始化
        for(int i=0;i<n;i++){
            curSum[i][0] = a[i];
            nextIdx[i][0] = (a[i] + i) % n;
        }

        for(int j=1;j<LOG;j++){
            for(int i=0;i<n;i++){
                int curIdx = nextIdx[i][j-1];
                nextIdx[i][j] = nextIdx[curIdx][j-1];
                curSum[i][j] = curSum[i][j-1] + curSum[curIdx][j-1];
            }
        }

        /**
         “老兄，看看总步数 m 的二进制第 j 位是不是 1。
         如果是 1，说明在总路程中，我们需要包含一段长度为 2^j 的连续狂奔。
         那就赶紧把这段路程能捡到的包子加上去，然后把我们的人挪到这段路程的终点去！”
         */
        long res = 0;
        int newIdx = 0;
        for(int j=LOG-1;j>=0;j--){
            if(((m>>j)&1) == 1){
                res += curSum[newIdx][j];
                newIdx = nextIdx[newIdx][j];
            }
        }

        pw.println(res);
        pw.flush();
        pw.close();
    }
}

