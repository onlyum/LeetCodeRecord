package 网易互娱.D20260306.T1.答案;
import java.io.*;
import java.util.*;

/*
【网易互娱】2026-3-6-第一题-妖伞传递

时间限制: 1000ms   空间限制: 256M

题目描述
在一条数轴上有 n(1 <= n <= 1000) 个人，第 i 个人的出生点为 p_i，终点为 q_i(1 <= p_i < q_i <= 10^9)。所有人的出生点两两不同，且按从小到大的顺序输入（p_1 < p_2 < ... < p_n）；对于任意 i，都有 p_i < q_i，即所有人都会沿数轴正方向移动。
最开始，第 1 个人持有妖伞，并从自己的出生点 p_1 出发前往终点 q_1。
移动过程中，所有已加入的人会形成一个队伍，并按照以下规则行动：
加入队伍：当当前持有妖伞队伍经过某个人的出生点 p_i 时，如果此人尚未加入过队伍，则该人立即加入到队伍末尾（“经过”包括恰好停在该点的情况）。
离开队伍：当某个人到达自己的终点 q_i 时，该人会立刻离开队伍，并且之后不会再次加入队伍。
妖伞交接：如果离开队伍的人恰好是当前持有妖伞的人，若此时队伍非空，则妖伞交给新的队首；若此时队伍为空，则妖伞会停留在当前位置不动，此后所有尚未加入队伍的人中，出生点距离妖伞当前位置最近的人会先前往该位置取得妖伞，再继续前往自己的终点。若该人在前往取伞途中经过了其他尚未加入队伍的人的出生点，这些人同样会按规则加入队伍末尾。
最近人的唯一性：题目保证所有人的出生点互不相同且严格递增，因此当队伍为空时，距离妖伞最近且尚未加入的人是唯一确定的。
定义第 i 个人的贡献值为该人持有妖伞期间，妖伞实际发生位移的总距离。请输出每个人的贡献值。

输入描述
第一行输入一个整数 n(1 <= n <= 1000)，表示人数。
接下来 n 行，每行输入两个整数 p_i, q_i(1 <= p_i < q_i <= 10^9)，表示第 i 个人的出生点和终点，满足 p_1 < p_2 < ... < p_n。

输出描述
输出一行 n 个整数，第 i 个整数表示第 i 个人持有妖伞期间，妖伞实际发生位移的总距离。

样例1
输入
4
1 10
5 6
9 30
20 25

输出
9 0 20 0
 */

/*
解答：
    栈+事件驱动
    1、栈维护所有队伍人员下标；
    2、只关注队伍下一步位置：队首离队点/新人入队点
 */
public class T1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens())  return;
        int n = Integer.parseInt(st.nextToken());

        long[] p = new long[n];
        long[] q = new long[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            p[i] = Long.parseLong(st.nextToken());
            q[i] = Long.parseLong(st.nextToken());
        }

        long[] ans = new long[n];
        Queue<Integer> queue = new ArrayDeque<>();//队伍，存储下标

        int nextIdx = 0;//下个人的下标
        long pos = p[0];//当前伞的位置

        while (nextIdx < n || !queue.isEmpty()) {

            // 情况 A：队伍空了。下一个人（nextIdx）必须走过去取伞。
            if (queue.isEmpty()) {
                queue.add(nextIdx++);
            }

            // 情况 B：处理“无效接盘侠”。
            // 队首到终点退队
            while (!queue.isEmpty() && q[queue.peek()] <= pos) {
                queue.poll();
            }

            // 如果清理完后队伍又空了，回到循环开头去接下一个新人
            if (queue.isEmpty()) continue;

            // 情况 C：正常推进。
            // 事件1：队首到达自己的终点 q[head]
            // 事件2：队伍遇到了下一个新人 p[nextIdx]
            int head = queue.peek();
            long targetPos = q[head];
            boolean meetNewPerson = false;

            // 遇到新人
            if (nextIdx < n && p[nextIdx] < q[head]) {
                targetPos = p[nextIdx];
                meetNewPerson = true;
            }

            // 移动到targetPos目标位置：计算移动距离并累加给当前队首
            ans[head] += (targetPos - pos);
            pos = targetPos;

            // 根据到达的位置触发事件
            if (meetNewPerson) {
                // 遇到新人，新人从队尾加入
                queue.add(nextIdx++);
            } else {
                // 到达队首终点，队首离开
                queue.poll();
            }
        }

        for (int i = 0; i < n; i++) {
            out.print(ans[i] + (i == n - 1 ? "" : " "));
        }
        out.println();
        out.flush();
        out.close();
    }
}