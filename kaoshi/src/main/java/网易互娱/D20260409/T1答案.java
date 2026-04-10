package 网易互娱.D20260409;
/*
模拟SQL：
SELECT COUNT(*) FROM(
SELECT user_name
FROM user_table
GROUP BY user_name
HAVING COUNT(DISTINCT user_id)>=2
)
 */
import java.io.*;
import java.util.*;

public class T1答案 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(br.readLine());
        if(!st.hasMoreTokens()) return;
        int n = Integer.parseInt(st.nextToken());

        int res = 0;
        // 存 user_name 第一次遇到的 user_id
        HashMap<String, String> firstIdMap = new HashMap<>();
        // 存已经凑齐 2 个不同 user_id，并且已经统计过结果的 user_name
        HashSet<String> countedNames = new HashSet<>();

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            String userId = st.nextToken();
            String userName = st.nextToken();

            // 步骤 1：如果这个名字已经达标并被统计过了，直接无视，看下一条
            if (countedNames.contains(userName)) {
                continue;
            }

            String firstId = firstIdMap.get(userName);

            // 步骤 2：如果之前没见过这个名字，把它的第一个 ID 存下来
            if (firstId == null) {
                firstIdMap.put(userName, userId);
            }
            // 步骤 3：之前存过第一个 ID，且当前的 ID 和第一个 ID 不一样！达标！
            else if (!firstId.equals(userId)) {
                res++;                      // 结果 +1
                countedNames.add(userName); // 拉入“已统计”黑名单，以后再遇到直接走步骤 1
                firstIdMap.remove(userName);// (可选优化) 既然已经达标了，首个ID就没用了，删掉省内存
            }
        }

        pw.print(res);
    }
}