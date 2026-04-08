package 网易互娱.网易互娱T1答案;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        String firstLine = br.readLine();
        if (firstLine == null) return;
        int n = Integer.parseInt(firstLine.trim());

        long[] p = new long[n];
        long[] q = new long[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
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
            // 如果新队首的终点已经在他接手位置的左边，他直接离开。
            while (!queue.isEmpty() && q[queue.peek()] <= pos) {
                queue.poll();
            }

            // 如果清理完后队伍又空了，回到循环开头去接下一个新人
            if (queue.isEmpty()) continue;

            // 情况 C：正常推进。
            // 当前拿伞的人是队首
            int head = queue.peek();
            long targetPos = q[head];
            boolean meetNewPerson = false;

            // 找到下一个关键事件点：
            // 事件1：队首到达自己的终点 q[head]
            // 事件2：队伍遇到了下一个新人 p[nextIdx]
            if (nextIdx < n && p[nextIdx] < q[head]) {
                targetPos = p[nextIdx];
                meetNewPerson = true;
            }

            // 移动：计算移动距离并累加给当前队首
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